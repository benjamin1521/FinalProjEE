package ua.training.controller.command.guest;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandFactory;
import ua.training.model.entities.User;
import ua.training.model.service.GuestService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public class Login implements Command {
    private static final Logger logger = Logger.getLogger(Login.class);

    private GuestService guestService = new GuestService();

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = guestService.getUser(username, password);

        if (user != null) {
            if (checkUserIsLogged(request, username)) {
                logger.info(String.format("user %d already logged in", user.getId()));

                request.setAttribute("message", "text.already.logged");
                return CommandFactory.getInstance().getCommandPage(request).execute(request);
            }
            logger.info(String.format("user %d logged in", user.getId()));

            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("role", user.getRole());
            return "redirect:/" + user.getRole().toString().toLowerCase();
        } else {
            logger.info("unsuccessful attempt to login as " + username);

            request.setAttribute("message", "text.wrong.login");
            return CommandFactory.getInstance().getCommandPage(request).execute(request);
        }
    }

    static public boolean checkUserIsLogged(HttpServletRequest request, String username) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        if (loggedUsers.stream().anyMatch(username::equals)) {
            return true;
        }
        loggedUsers.add(username);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }
}

