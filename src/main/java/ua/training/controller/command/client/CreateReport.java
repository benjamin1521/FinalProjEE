package ua.training.controller.command.client;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.entities.User;
import ua.training.model.service.ReportService;

import javax.servlet.http.HttpServletRequest;


public class CreateReport implements Command {
    private static final Logger logger = Logger.getLogger(CreateReport.class);
    private ReportService reportService = new ReportService();

    @Override
    public String execute(HttpServletRequest request) {
        User client = (User) request.getSession().getAttribute("user");

        if (reportService.addReport(client,
                request.getParameter("name"))) {
            logger.info(String.format("client %d created report", client.getId()));
        }

        return "redirect:/client/reports?page=1";
    }
}
