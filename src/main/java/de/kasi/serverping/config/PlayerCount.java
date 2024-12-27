package de.kasi.serverping.config;

public class PlayerCount {
    private boolean enabled = false;
    private int maxCount = 20;
    private int count = 0;

    public boolean isEnabled() {
        return enabled;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public int getCount() {
        return count;
    }
}
