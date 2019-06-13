package ua.training.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
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
            }
        }
        return dataSource;
    }
}
