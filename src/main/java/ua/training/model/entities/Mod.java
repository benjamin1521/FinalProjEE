package ua.training.model.entities;

import ua.training.model.entities.enums.Action;

import java.util.Date;

public class Mod {
    private Long id;
    private Action action;
    private String comment;
    private Date date;
    private Report reportsId;
    private Long userId;

    public Long getId() {
        return id;
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

        public Builder userId(Long userId) {
            Mod.this.userId = userId;
            return this;
        }

        public Builder reportsId(Report reportsId) {
            Mod.this.reportsId = reportsId;
            return this;
        }

        public Builder date(Date date) {
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

