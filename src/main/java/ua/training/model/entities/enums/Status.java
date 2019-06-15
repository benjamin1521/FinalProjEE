package ua.training.model.entities.enums;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    Active, Rejected, Approved, Shifted;

    private static final Map<String, Status> statuses = new HashMap<>();

    public static Status getOrNull(String test) {
        return statuses.getOrDefault(test, null);
    }

    static {
        for (Status status : Status.values()) {
            statuses.put(status.name(), status);
        }
    }
}
