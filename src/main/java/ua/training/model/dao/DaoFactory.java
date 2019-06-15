package ua.training.model.dao;

import ua.training.model.dao.impl.JDBCDaoFactory;

import java.sql.Connection;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract ReportDao createReportDao(Connection connection);

    public abstract UserDao createUserDao(Connection connection);

    public abstract ModDao createModDao(Connection connection);

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }

}
