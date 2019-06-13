//package ua.training.controller.filters;
//
//import org.apache.log4j.Logger;
//import ua.training.model.entities.User;
//import ua.training.model.entities.enums.Role;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class AuthFilter implements Filter {
//    private static final Logger logger = Logger.getLogger(AuthFilter.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//            throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo()) + "/";
//        String pathRole = path.substring(1, path.indexOf("/", 1));
//        Role role = (Role) request.getSession().getAttribute("role");
//
//        if (role.toString().toLowerCase().equals(pathRole)) {
//            filterChain.doFilter(request, response);
//        } else {
//            response.sendRedirect(request.getContextPath() + request.getServletPath()
//                    + "/" + role.toString().toLowerCase());
//
//            logger.info(String.format("redirected %s from %s",
//                    role == Role.Guest ? "guest"
//                            : "user " + ((User) request.getSession().getAttribute("user")).getId(),
//                    path));
//        }
//    }
//
//}


package ua.training.controller.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
//        ServletContext context = request.getServletContext();
//        System.out.println(session);
//        System.out.println(session.getAttribute("role"));
//        System.out.println(context.getAttribute("loggedUsers"));


        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
