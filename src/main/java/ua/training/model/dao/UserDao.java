package ua.training.model.dao;

import ua.training.model.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends AutoCloseable {
    void create(User entity) throws SQLException;

    User findById(Long id) throws SQLException;

    User findByUsernameAhdPassword(String username, String password) throws SQLException;

    List<User> findInspectors() throws SQLException;

    List<User> findInspectorsNotThis(Long id) throws SQLException;

}
