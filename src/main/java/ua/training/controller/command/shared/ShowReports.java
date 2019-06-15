package ua.training.controller.command.shared;

import ua.training.controller.command.Command;
import ua.training.model.dto.PaginationReports;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Status;
import ua.training.model.service.ReportService;

import javax.servlet.http.HttpServletRequest;

public class ShowReports implements Command {
    private ReportService reportService = new ReportService();

    private static final int REPORTS_PER_PAGE = 10;

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String urlRole = user.getRole().toString().toLowerCase();
        int page = Integer.parseInt(request.getParameter("page"));
        String status = Status.getOrNull(request.getParameter("status"));

        if (page < 1) {
            return String.format("redirect:/%s/reports?page=1&status=%s", urlRole, status);
        }

        PaginationReports result = reportService.getPage(user, page, REPORTS_PER_PAGE, status);

        if (result.getTotal() == 0) {
            request.setAttribute("noReports", true);
            return String.format("/%s/reports", urlRole);
        }

        if (result.getTotal() <= REPORTS_PER_PAGE * (page - 1)) {
            return String.format("redirect:/%s/reports?page=%s&status=%s",
                    urlRole,
                    (int) Math.ceil((double) result.getTotal() / REPORTS_PER_PAGE),
                    status);
        }

        request.setAttribute("paginationReports", result);

        return String.format("/%s/reports", urlRole);
    }
}


