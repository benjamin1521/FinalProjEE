package ua.training.model.dao.mapper;

import ua.training.model.entities.User;
import ua.training.model.entities.enums.ClientType;
import ua.training.model.entities.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractOne(ResultSet rs) throws SQLException {
        return User.newBuilder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .fullNameEn(rs.getString("full_name_en"))
                .fullNameUa(rs.getString("full_name_ua"))
                .role(Role.valueOf(rs.getString("role")))
                .clientType(ClientType.valueOf(rs.getString("client_type")))
                .build();
    }

    @Override
    public List<User> extractAll(ResultSet rs) throws SQLException {
        List<User> result = new ArrayList<>();
        while (rs.next()) {
            result.add(extractOne(rs));
        }
        return result;
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User subject) {
        cache.putIfAbsent(subject.getId(), subject);
        return cache.get(subject.getId());
    }
}
