package ua.training.model.dao;

import ua.training.model.entities.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    void create(User entity);

    User findById(Long id);

    User findByUsernameAhdPassword(String username, String password);

    List<User> findInspectors();

    List<User> findInspectorsNotThis(Long id);

}
