package de.kasi.serverping.mixin;

import com.mojang.authlib.GameProfile;
import de.kasi.serverping.config.ConfigManager;
import de.kasi.serverping.config.ServerPingConfig;
import net.minecraft.server.ServerMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mixin(ServerMetadata.Players.class)
public class ServerMetadataPlayersMixin {
    @Inject(method = "max()I", at = @At("HEAD"), cancellable = true)
    private void replaceMax(CallbackInfoReturnable<Integer> ci) {
        ServerPingConfig config = ConfigManager.getConfig();
        if (config == null || !config.getPlayerCount().isEnabled()) {
            return;
        }

        ci.setReturnValue(config.getPlayerCount().getMaxCount());
    }

    @Inject(method = "online()I", at = @At("HEAD"), cancellable = true)
    private void replaceOnline(CallbackInfoReturnable<Integer> ci) {
        ServerPingConfig config = ConfigManager.getConfig();
        if (config == null || !config.getPlayerCount().isEnabled()) {
            return;
        }

        ci.setReturnValue(config.getPlayerCount().getCount());
    }

    @Inject(method = "sample()Ljava/util/List;", at = @At("HEAD"), cancellable = true)
    private void replaceSample(CallbackInfoReturnable<List<GameProfile>> ci) {
        ServerPingConfig config = ConfigManager.getConfig();
        if (config == null || !config.getPlayerList().isEnabled()) {
            return;
        }

        List<String> names = config.getPlayerList().getNames();
        ArrayList<GameProfile> profiles = new ArrayList<>(names.size());

        for (String name : names) {
            profiles.add(new GameProfile(UUID.nameUUIDFromBytes(name.getBytes()), name));
        }

        ci.setReturnValue(profiles);
    }
}
