package ua.training.model.dao.impl;

import ua.training.model.dao.ModDao;
import ua.training.model.entities.Mod;
import ua.training.model.entities.Report;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCModDao implements ModDao {
    private Connection connection;

    public JDBCModDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Mod entity) {

    }

    @Override
    public Mod findById(Long id) {
        return null;
    }

    @Override
    public List<Mod> findAll() {
        return null;
    }

    @Override
    public void update(Mod entity) {

    }

    @Override
    public void delete(Report id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAllByReportsId(Report id) {

    }
}
