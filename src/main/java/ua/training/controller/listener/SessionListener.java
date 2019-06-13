package ua.training.controller.listener;

import ua.training.model.entities.enums.Role;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "en";

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("role", Role.Guest);
        httpSessionEvent.getSession().setAttribute("locale", DEFAULT_LOCALE);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}




//package ua.training.controller.listener;
//
//import ua.training.model.entities.enums.Role;
//
//import javax.servlet.http.HttpSessionEvent;
//import javax.servlet.http.HttpSessionListener;
//import java.util.HashSet;
//
//
//public class SessionListener implements HttpSessionListener {
//    @Override
//    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
//        httpSessionEvent.getSession().setAttribute("role", Role.Guest);
//    }
//
//    @Override
//    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
//        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
//                .getSession().getServletContext()
//                .getAttribute("loggedUsers");
//        String userName = (String) httpSessionEvent.getSession()
//                .getAttribute("userName");
//
//        loggedUsers.remove(userName);
//        httpSessionEvent.getSession().setAttribute("loggedUsers", loggedUsers);
//    }
//}
