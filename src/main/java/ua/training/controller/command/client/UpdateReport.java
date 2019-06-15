package ua.training.controller.command.client;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.entities.Report;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Status;
import ua.training.model.service.ReportService;

import javax.servlet.http.HttpServletRequest;

public class UpdateReport implements Command {
    private static final Logger logger = Logger.getLogger(UpdateReport.class);
    private ReportService reportService = new ReportService();

    @Override
    public String execute(HttpServletRequest request) {
        User client = (User) request.getSession().getAttribute("user");
        Report report = Report.newBuilder()
                .id(Long.parseLong(request.getParameter("id")))
                .status(Status.Active)
                .name(request.getParameter("name"))
                .address(request.getParameter("address"))
                .bank_account(request.getParameter("bank_account"))
                .bank_bic(request.getParameter("bank_bic"))
                .bank_account(request.getParameter("bank_account"))
                .bank_name(request.getParameter("bank_name"))
                .code(request.getParameter("code"))
                .inn(request.getParameter("inn"))
                .kpp(request.getParameter("kpp"))
                .name_short(request.getParameter("name_short"))
                .oktmo(request.getParameter("oktmo"))
                .parent_address(request.getParameter("parent_address"))
                .parent_code(request.getParameter("parent_code"))
                .parent_name(request.getParameter("parent_name"))
                .parent_phone(request.getParameter("parent_phone"))
                .payment_name(request.getParameter("payment_name"))
                .phone(request.getParameter("phone"))
                .build();
        if(reportService.updateReport(client, report, request.getParameter("comment"))){
            logger.info(String.format("client %d updated report %d",client.getId(),report.getId()));
        }

        return "redirect:/client/reports?page=1";
    }
}
