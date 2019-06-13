package ua.training.controller.command;

import ua.training.controller.command.client.ChangeInspector;
import ua.training.controller.command.client.CreateReport;
import ua.training.controller.command.client.DeleteReport;
import ua.training.controller.command.client.UpdateReport;
import ua.training.controller.command.guest.Login;
import ua.training.controller.command.guest.Registration;
import ua.training.controller.command.guest.ShowLogin;
import ua.training.controller.command.guest.ShowRegistration;
import ua.training.controller.command.inspector.ChangeStatus;
import ua.training.controller.command.shared.ChangeLanguage;
import ua.training.controller.command.shared.CreateMod;
import ua.training.controller.command.shared.LogOut;
import ua.training.controller.command.shared.RedirectHome;
import ua.training.controller.command.shared.ShowDetails;
import ua.training.controller.command.shared.ShowMainPage;
import ua.training.controller.command.shared.ShowReports;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();

    public static CommandFactory getInstance() {
        return instance;
    }

    private Map<String, Command> commands = new HashMap<>();
    private Command defaultCommand = new RedirectHome();

    private CommandFactory() {
        //guest
        commands.put("/guest", new ShowLogin());
        commands.put("/guest/registration", new ShowRegistration());


        commands.put("login", new Login());
        commands.put("registration", new Registration());

        //client
        commands.put("/client", new ShowMainPage());
        commands.put("/client/reports", new ShowReports());
        commands.put("/client/details", new ShowDetails());

        commands.put("createReport", new CreateReport());
        commands.put("updateReport", new UpdateReport());
        commands.put("deleteReport", new DeleteReport());
        commands.put("changeInspector", new ChangeInspector());

        //inspector
        commands.put("/inspector", new ShowMainPage());
        commands.put("/inspector/reports", new ShowReports());
        //TODO: reports by id
        commands.put("/inspector/details", new ShowDetails());

        commands.put("changeStatus", new ChangeStatus());

        //common
        commands.put("createMod", new CreateMod());
        commands.put("logout", new LogOut());
        commands.put("changeLanguage", new ChangeLanguage());

//        commands.put("exception", new Exception());
    }

    public Command getCommandPage(HttpServletRequest request) {
        String path = request.getPathInfo() == null ? "/" : request.getPathInfo();
        System.out.println("GCP: " + path);
        return commands.getOrDefault(path, defaultCommand);
    }

    public Command getCommand(HttpServletRequest request) {
        String command = request.getParameter("command");

        System.out.println("GC: " + command);
        if (command == null) {
            return getCommandPage(request);
        }

        String path = request.getPathInfo() == null ? "/" : request.getPathInfo();
        return commands.getOrDefault(command, commands.getOrDefault(command + ":" + path, defaultCommand));
    }
}
