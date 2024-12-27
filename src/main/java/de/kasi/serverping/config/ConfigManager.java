package de.kasi.serverping.config;

import org.jetbrains.annotations.Nullable;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class ConfigManager {
    private static ConfigHolder<ServerPingConfig> holder;

    public static void registerAutoConfig() {
        if (holder != null) {
            throw new IllegalStateException("Configuration already registered");
        }

        holder = AutoConfig.register(ServerPingConfig.class, GsonConfigSerializer::new);
    }

    @Nullable
    public static ServerPingConfig getConfig() {
        if (holder == null) {
            return ServerPingConfig.DEFAULT;
        }

        return holder.getConfig();
    }
}
