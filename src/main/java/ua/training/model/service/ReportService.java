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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ReportService {
    DaoFactory daoFactory = JDBCDaoFactory.getInstance();

    public List<Report> findAll() {
        try (ReportDao dao = daoFactory.createReportDao()) {
            return dao.findAll();
        }
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
                    ? dao.getPage(user.getId(), page, amount)
                    : dao.getPage(user.getId(), page, amount, status));

            result.setFirstNumber(offset + 1);
            result.setLastNumber(offset + limit);

            return result;
        }
    }

    private User randomOne(List<User> list) {
        return list.get(new Random().nextInt(list.size()));
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
            addMod(user, report, comment, Action.Created);
            return true;
        }
    }

    public void updateReport(User user, Report report, Map<String, String> form) {
        report.builder()
                .status(Status.Active)
                .name(form.get("name"))
                .address(form.get("address"))
                .bank_account(form.get("bank_account"))
                .bank_bic(form.get("bank_bic"))
                .bank_account(form.get("bank_account"))
                .bank_name(form.get("bank_name"))
                .code(form.get("code"))
                .inn(form.get("inn"))
                .kpp(form.get("kpp"))
                .name_short(form.get("name_short"))
                .oktmo(form.get("oktmo"))
                .parent_address(form.get("parent_address"))
                .parent_code(form.get("parent_code"))
                .parent_name(form.get("parent_name"))
                .parent_phone(form.get("parent_phone"))
                .payment_name(form.get("payment_name"))
                .phone(form.get("phone"))
                .build()
        ;

        addMod(user, report, form.get("comment"), Action.Updated);
    }

    public void deleteReport(Report report) {
        try (ModDao modDao = daoFactory.createModDao();
             ReportDao reportDao = daoFactory.createReportDao()) {
            modDao.deleteAllByReportsId(report);
            reportDao.delete(report);
        }
    }

    public void addMod(User user, Report report, String comment, Action action) {
        try (ModDao modDao = daoFactory.createModDao()) {
            modDao.create(Mod.newBuilder()
                    .action(action)
                    .comment(comment)
                    .date(new Date())
                    .reportsId(report)
                    .userId(user.getId())
                    .build());
        }
    }

    public void approveReport(Report report) {
        try (ReportDao reportDao = daoFactory.createReportDao()) {
            report.setStatus(Status.Approved);
            reportDao.update(report);
        }
    }

    public void rejectReport(Report report) {
        try (ReportDao reportDao = daoFactory.createReportDao()) {
            report.setStatus(Status.Rejected);
            reportDao.update(report);
        }
    }

    public void changeInspector(Report report) {
        try (UserDao userDao = daoFactory.createUserDao();
             ReportDao reportDao = daoFactory.createReportDao()) {
            report.setInspectorId(randomOne(userDao.findInspectorsNotThisReport(report)));
            reportDao.update(report);
        }
    }
}
