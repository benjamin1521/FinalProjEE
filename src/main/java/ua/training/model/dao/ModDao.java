package ua.training.model.dao;

import ua.training.model.entities.Mod;
import ua.training.model.entities.Report;

import java.util.List;

public interface ModDao extends GenericDao<Mod> {

    void create(Mod entity);

    List<Mod> findByReportId(Long id);

    void deleteAllByReportsId(Long id);
}
