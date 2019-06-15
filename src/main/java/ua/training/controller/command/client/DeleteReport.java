package ua.training.controller.command.client;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.entities.User;
import ua.training.model.service.ReportService;

import javax.servlet.http.HttpServletRequest;

public class DeleteReport implements Command {
    private static final Logger logger = Logger.getLogger(DeleteReport.class);

    private ReportService reportService = new ReportService();

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Long report = Long.parseLong(request.getParameter("id"));
        if (reportService.deleteReport(report)) {
            logger.info(String.format("client %d deleted report %d", user.getId(), report));
        }

        return "redirect:/client/reports?page=1";
    }
}
