package tahub.sdapitahub.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import tahub.sdapitahub.entity.TaUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TaUserMapper implements RowMapper<TaUser> {
    @Override
    public TaUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TaUser.Builder()
                .userId(rs.getLong("user_id"))
                .roleId(rs.getInt("role_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .username(rs.getString("username"))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .resetToken(rs.getString("reset_token"))
                .password(rs.getString("password_hash"))
                .isActive(rs.getBoolean("is_active"))
                .currentSessionId(rs.getString("current_session_id"))
                .lastLoginTime(getLocalDateTimeOrNull( rs,"last_login_time"))
                .createdAt(rs.getObject("created_at",LocalDateTime.class))
                .lastUpdated(rs.getObject("last_updated", LocalDateTime.class))
                .build();
    }

        private LocalDateTime getLocalDateTimeOrNull(ResultSet rs, String columnName) throws SQLException {
                java.sql.Timestamp timestamp = rs.getTimestamp(columnName);
                return timestamp != null ? timestamp.toLocalDateTime() : null;
            }
}
