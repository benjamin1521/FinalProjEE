package ua.training.model.dao.mapper;

import ua.training.model.entities.Mod;
import ua.training.model.entities.Report;
import ua.training.model.entities.enums.Action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ModMapper implements ObjectMapper<Mod> {
    @Override
    public Mod extractOne(ResultSet rs) throws SQLException {
        return Mod.newBuilder()
                .id(rs.getLong("id"))
                .action(Action.valueOf(rs.getString("action")))
                .comment(rs.getString("comment"))
                .date(rs.getDate("date"))
                .reportsId(Report.newBuilder()
                        .id(rs.getLong("reports_id"))
                        .build())
                .userId(rs.getLong("user_id"))
                .build();
    }

    @Override
    public List<Mod> extractAll(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Mod makeUnique(Map<Long, Mod> cache, Mod subject) {
        cache.putIfAbsent(subject.getId(), subject);
        return cache.get(subject.getId());
    }
}
