package ua.training.model.dao.impl;

import ua.training.model.dao.ModDao;
import ua.training.model.dao.mapper.ModMapper;
import ua.training.model.entities.Mod;
import ua.training.model.exceptions.SQLRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCModDao implements ModDao {
    private Connection connection;
    private ResourceBundle queries = ResourceBundle.getBundle("sql-queries");
    private ModMapper mapper = new ModMapper();

    JDBCModDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Mod entity) throws SQLException {
        String query = queries.getString("create.mod");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getAction().toString());
            ps.setString(2, entity.getComment());
            ps.setObject(3, Timestamp.valueOf(entity.getDate()));
            ps.setLong(4, entity.getReportsId());
            ps.setLong(5, entity.getUserId().getId());
            ps.execute();
        }
    }

    @Override
    public List<Mod> findByReportId(Long id) throws SQLException {
        String query = queries.getString("get.mods.by.report.id");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return mapper.extractAll(rs);
            }
        }
    }

    @Override
    public void deleteAllByReportsId(Long id) throws SQLException {
        String query = queries.getString("delete.mods.by.report.id");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.execute();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

