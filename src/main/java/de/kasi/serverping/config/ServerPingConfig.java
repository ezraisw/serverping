package de.kasi.serverping.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "serverping")
public class ServerPingConfig implements ConfigData {
    public static final ServerPingConfig DEFAULT = new ServerPingConfig();

    private PlayerCount playerCount = new PlayerCount();
    private PlayerList playerList = new PlayerList();

    public PlayerCount getPlayerCount() {
        return playerCount;
    }

    public PlayerList getPlayerList() {
        return playerList;
    }
}
