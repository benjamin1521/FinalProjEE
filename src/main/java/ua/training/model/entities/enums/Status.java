package ua.training.model.entities.enums;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    Active, Rejected, Approved, Shifted;

    private static final Map<String, Status> statuses = new HashMap<>();

    public static String getOrNull(String test) {
        Status result = statuses.getOrDefault(test, null);

        return result == null ? "All" : result.toString();

    }

    static {
        for (Status status : Status.values()) {
            statuses.put(status.name(), status);
        }
    }
}
