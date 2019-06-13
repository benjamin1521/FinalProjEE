package ua.training.controller.command.shared;

import ua.training.controller.command.Command;
import ua.training.model.dto.PaginationReports;
import ua.training.model.entities.Report;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Status;
import ua.training.model.service.ReportService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowReports implements Command {
    private ReportService reportService = new ReportService();

    private static final int REPORTS_PER_PAGE = 10;

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String urlRole = user.getRole().toString().toLowerCase();


        int page = Integer.valueOf(request.getParameter("page"));
        Status status = Status.valueOf((String) request.getAttribute("status"));
        if (page < 1) page = 1;

        PaginationReports result = reportService.getPage(user, page, REPORTS_PER_PAGE, status);

//        request.setAttribute("user", user);
        request.setAttribute("paginationReports", result);

        return String.format("/%s/reports", urlRole);
    }


}
