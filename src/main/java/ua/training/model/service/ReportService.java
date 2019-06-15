package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ModDao;
import ua.training.model.dao.ReportDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.JDBCDaoFactory;
import ua.training.model.dto.PaginationReports;
import ua.training.model.entities.Mod;
import ua.training.model.entities.Report;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Action;
import ua.training.model.entities.enums.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class ReportService {
    private DaoFactory daoFactory = JDBCDaoFactory.getInstance();

    private User randomOne(List<User> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    public PaginationReports getPage(User user, int page, int amount, Status status) {
        try (ReportDao dao = daoFactory.createReportDao()) {
            PaginationReports result = new PaginationReports();
            //TODO:transaction
            result.setTotal(status == null
                    ? dao.getCount(user.getId())
                    : dao.getCount(user.getId(), status));

            int offset = (page - 1) * amount;
            int limit = Math.min(result.getTotal() - offset, amount);

            result.setList(status == null
                    ? dao.getPage(user.getId(), offset, limit)
                    : dao.getPage(user.getId(), offset, limit, status));

            result.setFirstNumber(offset + 1);
            result.setLastNumber(offset + limit);


            return result;
        }
    }

    public boolean addReport(User user, String name, String comment) {
        try (UserDao userDao = daoFactory.createUserDao();
             ReportDao reportDao = daoFactory.createReportDao()) {
            Report report = Report.newBuilder()
                    .status(Status.Active)
                    .name(name)
                    .clientId(user)
                    .inspectorId(randomOne(userDao.findInspectors()))
                    .build();

            reportDao.create(report);

            return true;
        }
    }

    public Report findReportById(Long id) {
        try (ReportDao reportDao = daoFactory.createReportDao()) {
            return reportDao.findById(id);
        }
    }

    public void updateReport(User user, Report report, String comment) {
        try (ReportDao reportDao = daoFactory.createReportDao()) {
            reportDao.update(report);
        }
        addMod(user, report.getId(), comment, Action.Update);
    }

    public void changeStatus(Action action, Long report, User user, String comment) {
        try (ReportDao reportDao = daoFactory.createReportDao()) {
            reportDao.updateStatus(report, action);
        }
        addMod(user, report, comment, action);
    }

    public void deleteReport(Long report) {
        try (ModDao modDao = daoFactory.createModDao();
             ReportDao reportDao = daoFactory.createReportDao()) {
            modDao.deleteAllByReportsId(report);
            reportDao.delete(report);
        }
    }

    public List<Mod> getMods(Long id) {
        try (ModDao modDao = daoFactory.createModDao()) {
            return modDao.findByReportId(id);
        }
    }

    public void addMod(User user, Long report, String comment, Action action) {
        try (ModDao modDao = daoFactory.createModDao()) {
            modDao.create(Mod.newBuilder()
                    .action(action)
                    .comment(comment)
                    .date(LocalDateTime.now())
                    .reportsId(report)
                    .userId(user)
                    .build());
        }
    }

//    public void approveReport(Report report) {
//        try (ReportDao reportDao = daoFactory.createReportDao()) {
//            report.setStatus(Status.Approved);
//            reportDao.updateStatus(report);
//        }
//    }
//
//    public void rejectReport(Report report) {
//        try (ReportDao reportDao = daoFactory.createReportDao()) {
//            report.setStatus(Status.Rejected);
//            reportDao.updateStatus(report);
//        }
//    }

    public void changeInspector(Long report, Long inspector) {
        try (UserDao userDao = daoFactory.createUserDao();
             ReportDao reportDao = daoFactory.createReportDao()) {
            reportDao.updateInspector(randomOne(userDao.findInspectorsNotThis(inspector)).getId(), report);
        }
    }

}
