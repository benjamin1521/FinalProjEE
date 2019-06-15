package ua.training.model.dao;

import ua.training.model.entities.Mod;
import ua.training.model.entities.Report;

import java.sql.SQLException;
import java.util.List;

public interface ModDao extends AutoCloseable {

    void create(Mod entity) throws SQLException;

    List<Mod> findByReportId(Long id) throws SQLException;

    void deleteAllByReportsId(Long id) throws SQLException;
}
