package ua.training.controller.command.shared;

import ua.training.controller.command.Command;
import ua.training.model.entities.Mod;
import ua.training.model.entities.Report;
import ua.training.model.entities.User;
import ua.training.model.service.ReportService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowDetails implements Command {
    private ReportService reportService = new ReportService();

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String urlRole = user.getRole().toString().toLowerCase();

        List<Mod> mods = reportService.getMods(Long.parseLong(request.getParameter("id")));
        Report report = reportService.findReportById(Long.parseLong(request.getParameter("id")));

        request.setAttribute("user", user);
        request.setAttribute("mods", mods);
        request.setAttribute("report", report);
        return String.format("/%s/details", urlRole);
    }
}
