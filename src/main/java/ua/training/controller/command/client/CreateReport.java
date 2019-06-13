package ua.training.controller.command.client;

import ua.training.controller.command.Command;
import ua.training.model.entities.User;
import ua.training.model.service.ReportService;

import javax.servlet.http.HttpServletRequest;

public class CreateReport implements Command {
    private ReportService reportService = new ReportService();

    @Override
    public String execute(HttpServletRequest request) {
        User client = (User) request.getSession().getAttribute("user");

        reportService.addReport(client,
                request.getParameter("name"),
                request.getParameter("comment"));

        return "redirect:/client/reports";
    }
}
