package ua.training.controller.command.redirects;

import ua.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class RedirectHome implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "redirect:/" + request.getSession().getAttribute("role").toString().toLowerCase();
    }
}

