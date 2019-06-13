package ua.training.model.dao;

import ua.training.model.entities.Report;
import ua.training.model.entities.enums.Status;

import java.util.List;

public interface ReportDao extends GenericDao<Report> {
    int getCount(Long userId);

    int getCount(Long userId, Status status);


    List<Report> getPage(Long userId, int offset, int count);

    List<Report> getPage(Long userId, int offset, int count, Status status);
}
