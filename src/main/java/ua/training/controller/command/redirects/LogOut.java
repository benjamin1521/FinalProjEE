package ua.training.controller.command.redirects;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public class LogOut implements Command {
    private static final Logger logger = Logger.getLogger(LogOut.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        request.getSession().setAttribute("role", Role.Guest);
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");
        loggedUsers.remove(user.getUsername());
        request.getSession().getServletContext().setAttribute("loggedUsers",loggedUsers);
        request.getSession().removeAttribute("user");

        logger.info(String.format("user %d logged out", user.getId()));

        return new RedirectHome().execute(request);
    }
}
