package ua.training.model.dao.impl;

import ua.training.model.dao.ReportDao;
import ua.training.model.dao.mapper.ReportMapper;
import ua.training.model.entities.Report;
import ua.training.model.entities.enums.Status;
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

    public JDBCReportDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Report entity) {

        String query = queries.getString("create.report");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getStatus().toString());
            ps.setLong(3, entity.getClientId().getId());
            ps.setLong(4, entity.getInspectorId().getId());
            ps.execute();
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public Report findById(Long id) {
        return null;
    }

    @Override
    public List<Report> findAll() {
        return null;
    }

    @Override
    public void update(Report entity) {

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
    public int getCount(Long userId) {
        String query = queries.getString("get.tax.returns.count");

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, userId);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }

    @Override
    public int getCount(Long userId, Status status) {
        String query = queries.getString("get.tax.returns.count.by.status");

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, userId);
            ps.setString(3, status.toString());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }

    @Override
    public List<Report> getPage(Long userId, int offset, int count) {
        String query = queries.getString("get.reports.page");

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, userId);
            ps.setLong(2, userId);
            ps.setInt(3, offset);
            ps.setInt(4, count);

            try (ResultSet rs = ps.executeQuery()) {
                return mapper.paginationExtractTaxReturns(rs);
            }
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }

    @Override
    public List<Report> getPage(Long userId, int offset, int count, Status status) {
        String query = queries.getString("get.reports.page.by.status");

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, userId);
            ps.setLong(2, userId);
            ps.setString(3, status.toString());
            ps.setInt(4, offset);
            ps.setInt(5, count);

            try (ResultSet rs = ps.executeQuery()) {
                return mapper.paginationExtractTaxReturns(rs);
            }
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }

}
