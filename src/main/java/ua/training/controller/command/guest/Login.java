package ua.training.controller.command.guest;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandFactory;
import ua.training.model.entities.User;
import ua.training.model.service.GuestService;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command {
    private static final Logger logger = Logger.getLogger(Login.class);

    private GuestService guestService = new GuestService();

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = guestService.getUser(username, password);

        if (user != null) {
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
}


//package ua.training.controller.command.guest;
//
//import ua.training.controller.command.Command;
//import ua.training.model.entities.enums.Role;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class Login implements Command {
//    @Override
//    public String execute(HttpServletRequest request) {
//        String name = request.getParameter("name");
//        String pass = request.getParameter("pass");
//
//        System.out.println(name + " " + pass);
//        if (name == null || name.equals("") || pass == null || pass.equals("")) {
//            return "/guest/login";
//        }
//
//        if(CommandUtility.checkUserIsLogged(request, name)){
//            return "/error";
//        }
//
//        if (name.equals("Inspector")){
//            CommandUtility.setUserRole(request, Role.Inspector, name);
//            return "/inspector/mainpage";
//        } else if(name.equals("Client")) {
//            CommandUtility.setUserRole(request, Role.Client, name);
//            return "/client/mainpage";
//        } else {
//            return "/guest/login";
//        }
//    }
//}
