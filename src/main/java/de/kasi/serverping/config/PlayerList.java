package de.kasi.serverping.config;

import java.util.Collections;
import java.util.List;

public class PlayerList {
    public static enum SpoofingType {
        DISABLED,
        REPLACE,
        ANONYMIZE,
    }

    private SpoofingType spoofingType = SpoofingType.DISABLED;
    private List<String> names = List.of();

    public SpoofingType getSpoofingType() {
        return spoofingType;
    }

    public List<String> getNames() {
        return Collections.unmodifiableList(names);
    }
}
