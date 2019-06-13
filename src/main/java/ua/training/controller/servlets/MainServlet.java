package ua.training.controller.servlets;

import ua.training.controller.command.Command;
import ua.training.controller.command.CommandFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

public class MainServlet extends HttpServlet {

    public static final String REDIRECT = "redirect:";
    public static final String FORMAT = "/view/%s.jsp";

    private CommandFactory commandFactory = CommandFactory.getInstance();

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Command command = commandFactory.getCommand(request);
        String path = command.execute(request);
        System.out.println("SE path: " + path);

        if (path.startsWith(REDIRECT)) {
            response.sendRedirect(request.getContextPath() + request.getServletPath()
                    + path.substring(REDIRECT.length()));
        } else {
            request.getRequestDispatcher(String.format(FORMAT, path)).forward(request, response);
        }
    }
}


//package ua.training.controller.servlets;
//
//import ua.training.controller.command.Command;
//import ua.training.controller.command.guest.Login;
//import ua.training.controller.command.guest.Registration;
//import ua.training.controller.command.shared.Exception;
//import ua.training.controller.command.shared.LogOut;
//import ua.training.controller.command.shared.RedirectHome;
//import ua.training.controller.command.shared.ShowMainPage;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//
//public class MainServlet extends HttpServlet {
//    private Map<String, Command> commands = new HashMap<>();
//    private Command defaultCommand = new RedirectHome();
//
//    public static final String REDIRECT = "redirect:";
//    public static final String FORMAT = "/view/%s.jsp";
//
//    public void init(ServletConfig servletConfig) {
//        servletConfig.getServletContext()
//                .setAttribute("loggedUsers", new HashSet<String>());
//        commands.put("logout", new LogOut());
//        commands.put("login", new Login());
//        commands.put("registration", new Registration());
//        commands.put("exception", new Exception());
//        commands.put("guest", new ShowMainPage());
//        commands.put("client", new ShowMainPage());
//        commands.put("inspector", new ShowMainPage());
//    }
//
//    public void doGet(HttpServletRequest request,
//                      HttpServletResponse response)
//            throws IOException, ServletException {
//        processRequest(request, response);
//    }
//
//    public void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//        processRequest(request, response);
//    }
//
//    private void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String path = request.getRequestURI();
//        path = path.replaceAll(".*/app/", "");
////        System.out.println(path);
//
//        Command command = commands.getOrDefault(path, defaultCommand);
//        String page = command.execute(request);
//        if (page.contains(REDIRECT)) {
//            response.sendRedirect(request.getContextPath() + request.getServletPath() + page.substring(REDIRECT.length()));
//        } else {
//            request.getRequestDispatcher(String.format(FORMAT, page)).forward(request, response);
//        }
//
//
//    }
//}