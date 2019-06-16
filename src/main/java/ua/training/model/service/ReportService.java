package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ModDao;
import ua.training.model.dao.ReportDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.dao.impl.JDBCDaoFactory;
import ua.training.model.dto.PaginationReports;
import ua.training.model.entities.Mod;
import ua.training.model.entities.Report;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Action;
import ua.training.model.entities.enums.Status;
import ua.training.model.exceptions.SQLRuntimeException;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class ReportService {
    private DaoFactory daoFactory = JDBCDaoFactory.getInstance();
    private ConnectionPoolHolder connectionPool = ConnectionPoolHolder.getInstance();

    public PaginationReports getPage(User user, int page, int amount, String status) {
        try (Connection connection = connectionPool.getConnection()) {
            ReportDao dao = daoFactory.createReportDao(connection);
            connection.setAutoCommit(false);

            PaginationReports result = new PaginationReports();

            result.setTotal(status.equals("All")
                    ? dao.getCount(user.getId())
                    : dao.getCount(user.getId(), status));

            if (result.getTotal() <= amount * (page - 1)) {
                connection.commit();
                return result;
            }
            int offset = (page - 1) * amount;
            int limit = Math.min(result.getTotal() - offset, amount);

            result.setList(status.equals("All")
                    ? dao.getPage(user.getId(), offset, limit)
                    : dao.getPage(user.getId(), offset, limit, status));

            result.setFirstNumber(offset + 1);
            result.setLastNumber(offset + limit);

            connection.commit();
            return result;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    public boolean addReport(User user, String name) {
        try (Connection connection = connectionPool.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);
            ReportDao reportDao = daoFactory.createReportDao(connection);
            connection.setAutoCommit(false);

            Report report = Report.newBuilder()
                    .status(Status.Active)
                    .name(name)
                    .clientId(user)
                    .inspectorId(randomOne(userDao.findInspectors()))
                    .build();
            reportDao.create(report);

            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    public Report findReportById(Long id) {
        try (Connection connection = connectionPool.getConnection()) {
            ReportDao reportDao = daoFactory.createReportDao(connection);

            return reportDao.findById(id);

        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    public boolean updateReport(User user, Report report, String comment) {
        try (Connection connection = connectionPool.getConnection()) {
            ReportDao reportDao = daoFactory.createReportDao(connection);
            ModDao modDao = daoFactory.createModDao(connection);
            connection.setAutoCommit(false);

            reportDao.update(report);
            addMod(user, report.getId(), comment, Action.Update, modDao);

            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    public boolean changeStatus(Action action, Long report, User user, String comment) {
        try (Connection connection = connectionPool.getConnection()) {
            ReportDao reportDao = daoFactory.createReportDao(connection);
            ModDao modDao = daoFactory.createModDao(connection);
            connection.setAutoCommit(false);

            if (!reportDao.updateStatus(report, action)) {
                return false;
            }
            addMod(user, report, comment, action, modDao);

            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }

    }

    public boolean deleteReport(Long report) {
        try (Connection connection = connectionPool.getConnection()) {
            ModDao modDao = daoFactory.createModDao(connection);
            ReportDao reportDao = daoFactory.createReportDao(connection);
            connection.setAutoCommit(false);

            modDao.deleteAllByReportsId(report);
            reportDao.delete(report);

            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    public List<Mod> getMods(Long id) {
        try (Connection connection = connectionPool.getConnection()) {
            ModDao modDao = daoFactory.createModDao(connection);

            return modDao.findByReportId(id);

        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    public boolean addMod(User user, Long report, String comment, Action action) {
        try (Connection connection = connectionPool.getConnection()) {
            ModDao modDao = daoFactory.createModDao(connection);

            modDao.create(Mod.newBuilder()
                    .action(action)
                    .comment(comment)
                    .date(LocalDateTime.now())
                    .reportsId(report)
                    .userId(user)
                    .build());

            return true;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    private void addMod(User user, Long report, String comment, Action action, ModDao modDao) throws SQLException {
        modDao.create(Mod.newBuilder()
                .action(action)
                .comment(comment)
                .date(LocalDateTime.now())
                .reportsId(report)
                .userId(user)
                .build());
    }

    public boolean changeInspector(Long report, Long inspector) {
        try (Connection connection = connectionPool.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);
            ReportDao reportDao = daoFactory.createReportDao(connection);
            connection.setAutoCommit(false);

            reportDao.updateInspector(randomOne(userDao.findInspectorsNotThis(inspector)).getId(), report);

            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    private User randomOne(List<User> list) {
        return list.get(new Random().nextInt(list.size()));
    }

}
