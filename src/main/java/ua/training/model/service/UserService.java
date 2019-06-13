package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.JDBCDaoFactory;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Role;

import java.util.List;
import java.util.Map;


public class UserService {
    DaoFactory daoFactory = JDBCDaoFactory.getInstance();

    public List<User> findAll() {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        }
    }



    public User findById(Long id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findById(id);
        }
    }

    public void updateUser(User user, String username, Map<String, String> form) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.update(user.builder()
                    .username(username)
                    .role(Role.valueOf(form.get("role")))
                    .build());
        }
    }
}
