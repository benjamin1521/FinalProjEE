package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.JDBCDaoFactory;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Role;

public class GuestService {
    private DaoFactory daoFactory = JDBCDaoFactory.getInstance();

    public User getUser(String username, String password) {
        return daoFactory.createUserDao().findByUsernameAhdPassword(username, password);
    }

    public boolean createUser(User user, String password) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            User userFromDb = userDao.findById(user.getId());

            if (userFromDb != null) {
                return false;
            }
//            TODO: password encrypt
//            user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
            userDao.create(user.builder()
                    .password(password)
                    .role(Role.Client)
                    .build());
            return true;
        }
    }

}
