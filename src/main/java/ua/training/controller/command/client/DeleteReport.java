package ua.training.controller.command.client;

import ua.training.controller.command.Command;
import ua.training.model.service.ReportService;

import javax.servlet.http.HttpServletRequest;

public class DeleteReport implements Command {
    private ReportService reportService = new ReportService();

    @Override
    public String execute(HttpServletRequest request) {
        reportService.deleteReport(Long.parseLong(request.getParameter("id")));

        return "redirect:/client/reports?page=1";
    }
}
