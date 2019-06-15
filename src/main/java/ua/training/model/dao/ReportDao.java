package ua.training.model.dao;

import ua.training.model.entities.Report;
import ua.training.model.entities.enums.Action;

import java.sql.SQLException;
import java.util.List;

public interface ReportDao extends AutoCloseable {
    void create(Report entity) throws SQLException;

    Report findById(Long id) throws SQLException;

    void updateInspector(Long inspector, Long report) throws SQLException;

    void update(Report entity) throws SQLException;

    boolean updateStatus(Long report, Action action) throws SQLException;

    void delete(Long report) throws SQLException;

    int getCount(Long userId) throws SQLException;

    int getCount(Long userId, String status) throws SQLException;

    List<Report> getPage(Long userId, int offset, int count) throws SQLException;

    List<Report> getPage(Long userId, int offset, int limit, String status) throws SQLException;
}
