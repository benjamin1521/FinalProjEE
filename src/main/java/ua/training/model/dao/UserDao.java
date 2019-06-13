package ua.training.model.dao;

import ua.training.model.entities.Report;
import ua.training.model.entities.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    List<User> findInspectors();

    List<User> findInspectorsNotThisReport(Report id);

    User findByUsernameAhdPassword(String username, String password);
}
