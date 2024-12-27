package de.kasi.serverping;

import de.kasi.serverping.config.ConfigManager;
import net.fabricmc.api.DedicatedServerModInitializer;

public class ServerPing implements DedicatedServerModInitializer {
    public void onInitializeServer() {
        ConfigManager.registerAutoConfig();
    }
}
