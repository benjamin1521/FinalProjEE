package ua.training.model.dao.mapper;

import ua.training.model.entities.Mod;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModMapper implements ObjectMapper<Mod> {
    @Override
    public Mod extractOne(ResultSet rs) throws SQLException {
        return Mod.newBuilder()
                .id(rs.getLong("id"))
                .action(Action.valueOf(rs.getString("action")))
                .comment(rs.getString("comment"))
                .date(rs.getTimestamp("date").toLocalDateTime())
                .reportsId(rs.getLong("reports_id"))
                .userId(User.newBuilder()
                        .id(rs.getLong("user_id"))
                        .fullNameUa(rs.getString("full_name_ua"))
                        .fullNameEn(rs.getString("full_name_en"))
                        .build())
                .build();
    }

    @Override
    public List<Mod> extractAll(ResultSet rs) throws SQLException {
        List<Mod> result = new ArrayList<>();
        while (rs.next()) {
            result.add(extractOne(rs));
        }
        return result;
    }
}
