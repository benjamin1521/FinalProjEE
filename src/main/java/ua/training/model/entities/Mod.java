package ua.training.model.entities;

import ua.training.model.entities.enums.Action;

import java.time.LocalDateTime;
import java.util.Date;

public class Mod {
    private Long id;
    private Action action;
    private String comment;
    private LocalDateTime date;
    private Long reportsId;
    private User userId;

    public Long getId() {
        return id;
    }

    public Action getAction() {
        return action;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public User getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }

    public Long getReportsId() {
        return reportsId;
    }


    public static Builder newBuilder() {
        return new Mod().new Builder();
    }

    public Builder builder() {
        return new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(Long id) {
            Mod.this.id = id;
            return this;
        }

        public Builder userId(User userId) {
            Mod.this.userId = userId;
            return this;
        }

        public Builder reportsId(Long reportsId) {
            Mod.this.reportsId = reportsId;
            return this;
        }

        public Builder date(LocalDateTime date) {
            Mod.this.date = date;
            return this;
        }

        public Builder comment(String comment) {
            Mod.this.comment = comment;
            return this;
        }

        public Builder action(Action action) {
            Mod.this.action = action;
            return this;
        }

        public Mod build() {
            return Mod.this;
        }
    }
}

