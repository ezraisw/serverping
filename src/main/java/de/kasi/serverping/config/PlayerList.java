package de.kasi.serverping.config;

import java.util.List;

public class PlayerList {
    private boolean enabled = false;
    private List<String> names = List.of();

    public boolean isEnabled() {
        return enabled;
    }

    public List<String> getNames() {
        return names;
    }
}
