package ua.training.controller.command.client;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Action;
import ua.training.model.service.ReportService;

import javax.servlet.http.HttpServletRequest;

public class ChangeInspector implements Command {
    private static final Logger logger = Logger.getLogger(ChangeInspector.class);
    private ReportService reportService = new ReportService();

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Long report = Long.parseLong(request.getParameter("id"));
        Long inspector = Long.parseLong(request.getParameter("inspectorId"));
        String comment = request.getParameter("comment");

        if (reportService.changeInspector(report, inspector)) {
            logger.info(String.format("user %d changes inspector on report %d",
                    user.getId(), report));
            reportService.addMod(user, report, comment, Action.Change);
        }

        return "redirect:/client/reports?page=1";
    }
}
