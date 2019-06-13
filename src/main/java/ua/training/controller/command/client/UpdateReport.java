package ua.training.controller.command.client;

import ua.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class UpdateReport implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "redirect:client/reports";
    }
}
