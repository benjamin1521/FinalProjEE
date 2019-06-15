package ua.training.controller.command.shared;

import ua.training.controller.command.Command;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Action;
import ua.training.model.service.ReportService;

import javax.servlet.http.HttpServletRequest;

public class CreateMod implements Command {
    private ReportService reportService = new ReportService();

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Long report = Long.parseLong(request.getParameter("id"));
        String comment = request.getParameter("comment");
        Action action = Action.getOrNull(request.getParameter("command"));

        reportService.addMod(user, report, comment, action);

        return "redirect:/inspector/reports?page=1";
    }
}
