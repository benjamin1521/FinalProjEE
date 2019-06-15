package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.dao.impl.JDBCDaoFactory;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Role;
import ua.training.model.exceptions.SQLRuntimeException;

import java.sql.Connection;
import java.sql.SQLException;

public class GuestService {
    private DaoFactory daoFactory = JDBCDaoFactory.getInstance();
    private ConnectionPoolHolder connectionPool = ConnectionPoolHolder.getInstance();

    public User getUser(String username, String password) {
        try (Connection connection = connectionPool.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);

            return userDao.findByUsernameAhdPassword(username, password);

        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }

    }

    public boolean createUser(User user, String password) {
        try (Connection connection = connectionPool.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);
            connection.setAutoCommit(false);

            User userFromDb = userDao.findById(user.getId());
            if (userFromDb != null) {
                return false;
            }
            userDao.create(user.builder()
                    .password(password)
                    .role(Role.Client)
                    .build());

            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }
}
