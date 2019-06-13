package ua.training.model.dao;

import ua.training.model.entities.Mod;
import ua.training.model.entities.Report;

public interface ModDao extends GenericDao<Mod> {

    void deleteAllByReportsId(Report id);
}
