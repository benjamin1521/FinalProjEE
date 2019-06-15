package ua.training.model.dao.impl;

import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entities.User;
import ua.training.model.exceptions.SQLRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCUserDao implements UserDao {
    private Connection connection;
    private ResourceBundle queries = ResourceBundle.getBundle("sql-queries");
    private UserMapper mapper = new UserMapper();

    JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {

        String query = queries.getString("create.user");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getRole().toString());
            ps.setString(2, entity.getClientType().toString());
            ps.setString(3, entity.getUsername());
            ps.setString(4, entity.getPassword());
            ps.setString(5, entity.getFullNameEn());
            ps.setString(6, entity.getFullNameUa());
            ps.execute();
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public User findById(Long id) {
        String query = queries.getString("get.user.by.id");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapper.extractOne(rs) : null;

            }
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }


    @Override
    public List<User> findInspectors() {
        String query = queries.getString("get.inspectors");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                return mapper.extractAll(rs);
            }
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public List<User> findInspectorsNotThis(Long id) {
        String query = queries.getString("get.inspectors.not.this");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return mapper.extractAll(rs);
            }
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public User findByUsernameAhdPassword(String username, String password) {
        String query = queries.getString("get.user.by.username.and.password");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapper.extractOne(rs) : null;
            }
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
