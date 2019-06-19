package ua.training.controller.filters;

import org.apache.log4j.Logger;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Role;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    private static final Logger logger = Logger.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;


        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo()) + "/";
        String pathRole = path.substring(1, path.indexOf("/", 1));
        Role role = (Role) request.getSession().getAttribute("role");

        if (role.toString().toLowerCase().equals(pathRole.toLowerCase())) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + request.getServletPath()
                    + "/" + role.toString().toLowerCase());
        }
    }

    @Override
    public void destroy() {

    }
}
