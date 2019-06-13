package ua.training.model.entities;

import ua.training.model.entities.enums.ClientType;
import ua.training.model.entities.enums.Role;

import java.util.Objects;

public class User {
    private Long id;
    private String username;
    private String password;
    private String fullNameEn;
    private String fullNameUa;
    private Role role;
    private ClientType clientType;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public String getFullNameEn() {
        return fullNameEn;
    }

    public String getFullNameUa() {
        return fullNameUa;
    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public Builder builder() {
        return new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(Long id) {
            User.this.id = id;
            return this;
        }

        public Builder role(Role role) {
            User.this.role = role;
            return this;
        }

        public Builder clientType(ClientType clientType) {
            User.this.clientType = clientType;
            return this;
        }

        public Builder username(String username) {
            User.this.username = username;
            return this;
        }

        public Builder password(String password) {
            User.this.password = password;
            return this;
        }

        public Builder fullNameEn(String fullName) {
            User.this.fullNameEn = fullName;
            return this;
        }

        public Builder fullNameUa(String fullName) {
            User.this.fullNameUa = fullName;
            return this;
        }

        public User build() {
            return User.this;
        }
    }

}
