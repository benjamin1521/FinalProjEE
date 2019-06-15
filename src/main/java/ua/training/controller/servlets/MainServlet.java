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

    private static final String REDIRECT = "redirect:";
    private static final String FORMAT = "/view/%s.jsp";

    private CommandFactory commandFactory = CommandFactory.getInstance();

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
        System.out.println("Servlet, path: " + path);

        if (path.startsWith(REDIRECT)) {
            response.sendRedirect(request.getContextPath() + request.getServletPath()
                    + path.substring(REDIRECT.length()));
        } else {
            request.getRequestDispatcher(String.format(FORMAT, path)).forward(request, response);
        }
    }
}
