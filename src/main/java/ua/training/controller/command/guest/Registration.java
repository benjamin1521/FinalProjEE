package ua.training.controller.command.guest;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandFactory;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.ClientType;
import ua.training.model.entities.enums.Role;
import ua.training.model.service.GuestService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Registration implements Command {
    private static final Logger logger = Logger.getLogger(Registration.class);

    private GuestService guestService = new GuestService();

    @Override
    public String execute(HttpServletRequest request) {
        if (validateRequest(request)) {
            User user = User.newBuilder()
                    .role(Role.Client)
                    .username(request.getParameter("username"))
                    .fullNameEn(request.getParameter("fullNameEn"))
                    .fullNameUa(request.getParameter("fullNameUa"))
                    .clientType(ClientType.Physical)
                    .build();

            if (guestService.createUser(user, request.getParameter("password"))) {
                logger.info(String.format("user %s was created", user.getUsername()));
                return new Login().execute(request);
            } else {
                request.setAttribute("message", "text.exists");
            }

            return CommandFactory.getInstance().getCommandPage(request).execute(request);
        }
        request.setAttribute("message", "text.wrong.registration");
        return CommandFactory.getInstance().getCommandPage(request).execute(request);
    }

    private static final ResourceBundle regexps = ResourceBundle.getBundle("regexps");
    private final List<String> fields = Arrays.asList(
            "username", "fullNameUa", "fullNameEn", "password");

    private boolean validateRequest(HttpServletRequest request) {
        boolean allFieldsValid = true;

        for (String field : fields) {
            String value = request.getParameter(field);
            if (value.matches(regexps.getString(field))) {
                request.setAttribute(field, value);
                continue;
            }
            request.setAttribute("message_" + field, true);
            allFieldsValid = false;
        }
        return allFieldsValid;
    }
}