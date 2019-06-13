package ua.training.controller.command.guest;

import ua.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ShowLogin implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "guest/login";
    }
}
