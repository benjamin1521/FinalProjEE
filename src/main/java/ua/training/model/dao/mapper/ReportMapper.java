package ua.training.model.dao.mapper;

import ua.training.model.entities.Report;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportMapper implements ObjectMapper<Report> {
    @Override
    public Report extractOne(ResultSet rs) throws SQLException {
        return Report.newBuilder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .status(Status.valueOf(rs.getString("status")))
                .clientId(User.newBuilder()
                        .id(rs.getLong("client_id"))
                        .fullNameEn(rs.getString("full_name_en"))
                        .fullNameUa(rs.getString("full_name_ua"))
                        .build())
                .inspectorId(User.newBuilder()
                        .id(rs.getLong("inspector_id"))
                        .fullNameEn(rs.getString("full_name_en"))
                        .fullNameUa(rs.getString("full_name_ua"))
                        .build())
                .address(rs.getString("address"))
                .bank_account(rs.getString("bank_account"))
                .bank_bic(rs.getString("bank_bic"))
                .bank_name(rs.getString("bank_name"))
                .code(rs.getString("code"))
                .inn(rs.getString("inn"))
                .kpp(rs.getString("kpp"))
                .name_short(rs.getString("name_short"))
                .oktmo(rs.getString("oktmo"))
                .parent_address(rs.getString("parent_address"))
                .parent_code(rs.getString("parent_code"))
                .parent_name(rs.getString("parent_name"))
                .parent_phone(rs.getString("parent_phone"))
                .payment_name(rs.getString("payment_name"))
                .phone(rs.getString("phone"))
                .build();
    }

    public Report extractPart(ResultSet rs) throws SQLException {
        return Report.newBuilder()
                .id(rs.getLong("reports.id"))
                .status(Status.valueOf(rs.getString("status")))
                .build();
    }

    @Override
    public List<Report> extractAll(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Report makeUnique(Map<Long, Report> cache, Report subject) {
        cache.putIfAbsent(subject.getId(), subject);
        return cache.get(subject.getId());
    }

    public List<Report> paginationExtractTaxReturns(ResultSet rs) throws SQLException {
        List<Report> paginationList = new ArrayList<>();

        while (rs.next()) {
            paginationList.add(extractPart(rs));
        }

        return paginationList;
    }
}
