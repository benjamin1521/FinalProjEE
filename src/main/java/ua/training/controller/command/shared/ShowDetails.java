package ua.training.controller.command.shared;

import ua.training.controller.command.Command;
import ua.training.model.entities.User;

import javax.servlet.http.HttpServletRequest;

public class ShowDetails implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String urlRole = user.getRole().toString().toLowerCase();
        //TODO:report by id
        Long id = (Long) request.getAttribute("reportId");
        request.setAttribute("user", user);
        return urlRole + "/details/" + id;
    }
}
