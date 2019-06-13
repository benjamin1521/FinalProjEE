package ua.training.controller.command.shared;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Role;

import javax.servlet.http.HttpServletRequest;

public class LogOut implements Command {
    private static final Logger logger = Logger.getLogger(LogOut.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        request.getSession().setAttribute("role", Role.Guest);
        request.getSession().removeAttribute("user");

        logger.info(String.format("user %d logged out", user.getId()));

        return new RedirectHome().execute(request);
    }
}


//package ua.training.controller.command.shared;
//
//import ua.training.controller.command.Command;
//import ua.training.model.entities.enums.Role;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashSet;
//
//public class LogOut implements Command {
//
//    @Override
//    public String execute(HttpServletRequest request) {
//
//        HashSet<String> loggedUsers = (HashSet<String>) request
//                .getSession().getServletContext()
//                .getAttribute("loggedUsers");
//        loggedUsers.remove(request
//                .getServletContext()
//                .getAttribute("userName"));
//        request.getSession().setAttribute("loggedUsers", loggedUsers);
//        request.getSession().setAttribute("role", Role.Guest);
//
//
//        return new RedirectHome().execute(request);
//    }
//}
