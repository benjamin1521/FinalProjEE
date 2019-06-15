package ua.training.model.dao.impl;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ModDao;
import ua.training.model.dao.ReportDao;
import ua.training.model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    @Override
    public ReportDao createReportDao(Connection connection) {
        return new JDBCReportDao(connection);
    }

    @Override
    public UserDao createUserDao(Connection connection) {
        return new JDBCUserDao(connection);
    }

    @Override
    public ModDao createModDao(Connection connection) {
        return new JDBCModDao(connection);
    }

}
