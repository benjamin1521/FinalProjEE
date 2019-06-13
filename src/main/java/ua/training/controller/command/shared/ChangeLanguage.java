package ua.training.controller.command.shared;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandFactory;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Role;

import javax.servlet.http.HttpServletRequest;

public class ChangeLanguage implements Command {
    private static final Logger logger = Logger.getLogger(ChangeLanguage.class);

    @Override
    public String execute(HttpServletRequest request) {
        String newLocale = request.getParameter("locale");
        String logUser = request.getSession().getAttribute("role") == Role.Guest
                ? "guest"
                : "user " + ((User) request.getSession().getAttribute("user")).getId();

        if ((newLocale == null) || (!newLocale.equals("ua") && (!newLocale.equals("en")))) {
            logger.warn(logUser + " faked locale value");
            return CommandFactory.getInstance().getCommandPage(request).execute(request);
        }

        request.getSession().setAttribute("locale", newLocale);
        request.setAttribute("locale", newLocale);

        logger.info(String.format("%s changed locale to %s", logUser, newLocale));

        return CommandFactory.getInstance().getCommandPage(request).execute(request);
    }
}
