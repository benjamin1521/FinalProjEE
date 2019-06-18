package ua.training.controller.listener;

import ua.training.model.entities.User;
import ua.training.model.entities.enums.Role;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "en";

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("role", Role.Guest);
        httpSessionEvent.getSession().setAttribute("locale", DEFAULT_LOCALE);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");
        User user = (User) httpSessionEvent.getSession().getAttribute("user");
        loggedUsers.remove(user.getUsername());
        httpSessionEvent.getSession().setAttribute("loggedUsers", loggedUsers);
        System.out.println(loggedUsers);

    }
}