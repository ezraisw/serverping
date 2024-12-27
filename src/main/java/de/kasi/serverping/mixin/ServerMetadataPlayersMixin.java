package de.kasi.serverping.mixin;

import com.mojang.authlib.GameProfile;
import de.kasi.serverping.config.ConfigManager;
import de.kasi.serverping.config.ServerPingConfig;
import net.minecraft.server.ServerMetadata;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.List;
import java.util.UUID;

@Mixin(ServerMetadata.Players.class)
public class ServerMetadataPlayersMixin {
    private static final UUID ZERO_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @Shadow
    @Final
    private List<GameProfile> sample;

    @Inject(method = "max()I", at = @At("HEAD"), cancellable = true)
    private void replaceMax(CallbackInfoReturnable<Integer> cir) {
        ServerPingConfig config = ConfigManager.getConfig();
        if (config == null || !config.getPlayerCount().isEnabled()) {
            return;
        }

        cir.setReturnValue(config.getPlayerCount().getMaxCount());
    }

    @Inject(method = "online()I", at = @At("HEAD"), cancellable = true)
    private void replaceOnline(CallbackInfoReturnable<Integer> cir) {
        ServerPingConfig config = ConfigManager.getConfig();
        if (config == null || !config.getPlayerCount().isEnabled()) {
            return;
        }

        cir.setReturnValue(config.getPlayerCount().getCount());
    }

    @Inject(method = "sample()Ljava/util/List;", at = @At("HEAD"), cancellable = true)
    private void replaceSample(CallbackInfoReturnable<List<GameProfile>> cir) {
        ServerPingConfig config = ConfigManager.getConfig();
        if (config == null) {
            return;
        }

        List<GameProfile> profiles;

        switch (config.getPlayerList().getSpoofingType()) {
            case ANONYMIZE:
                if (sample.isEmpty()) {
                    // Sample is already empty so just return and let it return the original.
                    return;
                }

                profiles = sample.stream()
                        .map((profile) -> new GameProfile(ZERO_UUID, "Anonymous Player"))
                        .toList();
                break;
            case REPLACE:
                List<String> names = config.getPlayerList().getNames();
                // Optimization when using empty player lists.
                // Prevents allocation of new empty list.
                if (names.isEmpty()) {
                    profiles = List.of();
                    break;
                }

                profiles = names.stream()
                        .map((name) -> new GameProfile(UUID.nameUUIDFromBytes(name.getBytes()), name))
                        .toList();
                break;
            default:
                return;
        }

        cir.setReturnValue(profiles);
    }
}
