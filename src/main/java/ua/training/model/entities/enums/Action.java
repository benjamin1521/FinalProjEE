package ua.training.model.entities.enums;

import java.util.HashMap;
import java.util.Map;

public enum Action {
    Update(Status.Active),
    Approve(Status.Approved),
    Comment(Status.Active),
    Reject(Status.Rejected),
    Shift(Status.Shifted),
    Change(Status.Active);

    private static final Map<String, Action> actions = new HashMap<>();
    private Status status;

    Action(Status status) {
        this.status=status;
    }

    public Status getStatus() {
        return status;
    }

    public static Action getOrNull(String test) {
        return actions.getOrDefault(test, null);
    }

    static {
        for (Action action : Action.values()) {
            actions.put(action.name(), action);
        }
    }
}
