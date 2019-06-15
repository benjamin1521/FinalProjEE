package ua.training.controller.command.client;

import ua.training.controller.command.Command;
import ua.training.model.entities.Report;
import ua.training.model.service.ReportService;

import javax.servlet.http.HttpServletRequest;

public class ChangeInspector implements Command {
    private ReportService reportService = new ReportService();

    @Override
    public String execute(HttpServletRequest request) {
        reportService.changeInspector(Long.parseLong(request.getParameter("id")),Long.parseLong(request.getParameter("inspectorId")));
        return "redirect:/client/reports?page=1";
    }
}
