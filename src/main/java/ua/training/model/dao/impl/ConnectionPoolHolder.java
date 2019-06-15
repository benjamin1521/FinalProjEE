package ua.training.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPoolHolder {
    private static final ConnectionPoolHolder instance = new ConnectionPoolHolder();
    private DataSource dataSource;

    public static ConnectionPoolHolder getInstance() {
        return instance;
    }

    private ConnectionPoolHolder(){
        ResourceBundle bundle = ResourceBundle.getBundle("mysql-connect");
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(bundle.getString("url"));
        ds.setUsername(bundle.getString("username"));
        ds.setPassword(bundle.getString("password"));
        ds.setMinIdle(Integer.valueOf(bundle.getString("min.idle")));
        ds.setMaxIdle(Integer.valueOf(bundle.getString("max.idle")));
        ds.setMaxOpenPreparedStatements(Integer.valueOf(bundle.getString("max.open.prepared.statements")));
        dataSource = ds;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
