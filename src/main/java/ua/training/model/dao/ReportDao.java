package ua.training.model.dao;

import ua.training.model.entities.Report;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Action;
import ua.training.model.entities.enums.Status;

import java.util.List;

public interface ReportDao extends GenericDao<Report> {
    void create(Report entity);

    Report findById(Long id);

    void updateInspector(Long inspector, Long report);

    void update(Report entity);

    void updateStatus(Long report, Action action);

    void delete(Long report);

    int getCount(Long userId);

    int getCount(Long userId, Status status);

    List<Report> getPage(Long userId, int offset, int count);

    List<Report> getPage(Long userId, int offset, int count, Status status);
}
