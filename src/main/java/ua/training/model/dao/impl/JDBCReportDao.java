package ua.training.model.dao.impl;

import ua.training.model.dao.ReportDao;
import ua.training.model.dao.mapper.ReportMapper;
import ua.training.model.entities.Report;
import ua.training.model.entities.enums.Action;
import ua.training.model.exceptions.SQLRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCReportDao implements ReportDao {
    private Connection connection;
    private ResourceBundle queries = ResourceBundle.getBundle("sql-queries");
    private ReportMapper mapper = new ReportMapper();

    JDBCReportDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Report entity) throws SQLException {
        String query = queries.getString("create.report");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getStatus().toString());
            ps.setLong(3, entity.getClientId().getId());
            ps.setLong(4, entity.getInspectorId().getId());
            ps.execute();
        }
    }

    @Override
    public Report findById(Long id) throws SQLException {
        String query = queries.getString("get.report.by.id");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return mapper.extractOne(rs);
            }
        }
    }

    @Override
    public void update(Report entity) throws SQLException {
        String query = queries.getString("update.report");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getAddress());
            ps.setString(2, entity.getBank_account());
            ps.setString(3, entity.getBank_bic());
            ps.setString(4, entity.getBank_name());
            ps.setString(5, entity.getCode());
            ps.setString(6, entity.getInn());
            ps.setString(7, entity.getKpp());
            ps.setString(8, entity.getName_short());
            ps.setString(9, entity.getOktmo());
            ps.setString(10, entity.getParent_address());
            ps.setString(11, entity.getParent_code());
            ps.setString(12, entity.getParent_name());
            ps.setString(13, entity.getParent_phone());
            ps.setString(14, entity.getPayment_name());
            ps.setString(15, entity.getPhone());
            ps.setString(16, entity.getName());
            ps.setString(17, entity.getStatus().toString());
            ps.setLong(18, entity.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public boolean updateStatus(Long report, Action action) throws SQLException {
        String query = queries.getString("update.status");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, action.getStatus().toString());
            ps.setLong(2, report);
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public void updateInspector(Long inspector, Long report) throws SQLException {
        String query = queries.getString("update.inspector");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, inspector);
            ps.setLong(2, report);
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Long report) throws SQLException {
        String query = queries.getString("delete.report");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, report);
            ps.execute();
        }
    }


    @Override
    public int getCount(Long userId) throws SQLException {
        String query = queries.getString("get.reports.count");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, userId);
            ps.setLong(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    @Override
    public int getCount(Long userId, String status) throws SQLException {
        String query = queries.getString("get.reports.count.by.status");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, userId);
            ps.setLong(2, userId);
            ps.setString(3, status);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    @Override
    public List<Report> getPage(Long userId, int offset, int limit) throws SQLException {
        String query = queries.getString("get.reports.page");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, userId);
            ps.setLong(2, userId);
            ps.setInt(3, offset);
            ps.setInt(4, limit);
            try (ResultSet rs = ps.executeQuery()) {
                return mapper.extractAllParts(rs);
            }
        }
    }

    @Override
    public List<Report> getPage(Long userId, int offset, int limit, String status) throws SQLException {
        String query = queries.getString("get.reports.page.by.status");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, userId);
            ps.setLong(2, userId);
            ps.setString(3, status);
            ps.setInt(4, offset);
            ps.setInt(5, limit);
            try (ResultSet rs = ps.executeQuery()) {
                return mapper.extractAllParts(rs);
            }
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }
}
