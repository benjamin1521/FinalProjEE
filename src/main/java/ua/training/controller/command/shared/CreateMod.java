package ua.training.controller.command.shared;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Action;
import ua.training.model.service.ReportService;

import javax.servlet.http.HttpServletRequest;

public class CreateMod implements Command {
    private static final Logger logger = Logger.getLogger(CreateMod.class);
    private ReportService reportService = new ReportService();

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Long report = Long.parseLong(request.getParameter("id"));
        Action action = Action.getOrNull(request.getParameter("command"));
        String comment = request.getParameter("comment");


        if (reportService.addMod(user, report, comment, action)) {
            logger.info(String.format("user %d commented report %d", user.getId(), report));
        }

        return "redirect:/inspector/reports?page=1";
    }
}
