package tahub.sdapitahub.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tahub.sdapitahub.entity.TaUser;
import tahub.sdapitahub.repository.mapper.TaUserMapper;
import tahub.sdapitahub.repository.query.UserQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaUserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TaUser> findAll() {
        return jdbcTemplate.query(UserQuery.FIND_ALL.getQuery(), new TaUserMapper());
    }

    public Optional<TaUser> findById(Long id) {
        return jdbcTemplate.query(UserQuery.FIND_BY_ID.getQuery(), new Object[]{id}, new TaUserMapper()).stream().findFirst();
    }

    public TaUser save(TaUser user) {
        jdbcTemplate.update(UserQuery.SAVE.getQuery(),user.getRoleId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPhone(),
                user.getResetToken(), user.getPassword(), user.getIsActive(), user.getgAccessToken(), user.getgRefreshToken(), user.getCurrentSessionId(), user.getLastLoginTime(), user.getCreatedAt(), user.getLastUpdated());
        return user;
    }

    public TaUser update(TaUser user) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE ta_users SET ");
        List<Object> queryParams = new ArrayList<>();

        // Initialize a flag to track if any fields are updated
        boolean fieldsUpdated = false;

        if (user.getFirstName() != null) {
            queryBuilder.append("first_name = ?, ");
            queryParams.add(user.getFirstName());
            fieldsUpdated = true;
        }
        if (user.getLastName() != null) {
            queryBuilder.append("last_name = ?, ");
            queryParams.add(user.getLastName());
            fieldsUpdated = true;
        }
        if (user.getUsername() != null) {
            queryBuilder.append("username = ?, ");
            queryParams.add(user.getUsername());
            fieldsUpdated = true;
        }
        if (user.getEmail() != null) {
            queryBuilder.append("email = ?, ");
            queryParams.add(user.getEmail());
            fieldsUpdated = true;
        }
        if (user.getPhone() != null) {
            queryBuilder.append("phone = ?, ");
            queryParams.add(user.getPhone());
            fieldsUpdated = true;
        }
        if (user.getResetToken() != null) {
            queryBuilder.append("reset_token = ?, ");
            queryParams.add(user.getResetToken());
            fieldsUpdated = true;
        }
        if (user.getPassword() != null) {
            queryBuilder.append("password_hash = ?, ");
            queryParams.add(user.getPassword());
            fieldsUpdated = true;
        }
        if (user.getIsActive()) {
            queryBuilder.append("is_active = ?, ");
            queryParams.add(user.getIsActive());
            fieldsUpdated = true;
        }
        if (user.getgAccessToken() != null) {
            queryBuilder.append("g_access_token = ?, ");
            queryParams.add(user.getgAccessToken());
            fieldsUpdated = true;
        }
        if (user.getgRefreshToken() != null) {
            queryBuilder.append("g_refresh_token = ?, ");
            queryParams.add(user.getgRefreshToken());
            fieldsUpdated = true;
        }
        if (user.getCurrentSessionId() != null) {
            queryBuilder.append("current_session_id = ?, ");
            queryParams.add(user.getCurrentSessionId());
            fieldsUpdated = true;
        }
        if (user.getLastLoginTime() != null) {
            queryBuilder.append("last_login_time = ?, ");
            queryParams.add(user.getLastLoginTime());
            fieldsUpdated = true;
        }

        if (user.getLastUpdated() != null) {
            queryBuilder.append("last_updated = ?, ");
            queryParams.add(user.getLastUpdated());
            fieldsUpdated = true;
        }

        if (fieldsUpdated) {
            queryBuilder.setLength(queryBuilder.length() - 2);
            queryBuilder.append(" WHERE user_id = ?");
            queryParams.add(user.getUserId());

            jdbcTemplate.update(queryBuilder.toString(), queryParams.toArray());
        }

        return user;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update(UserQuery.DELETE_BY_ID.getQuery(), id);
    }

    public TaUser findByEmail(String email) {
        List<TaUser> users = jdbcTemplate.query(UserQuery.FIND_BY_EMAIL.getQuery(), new Object[]{email}, new TaUserMapper());
        return users.isEmpty() ? null : users.get(0);
    }

    public TaUser findByResetToken(String resetToken) {
        List<TaUser> users = jdbcTemplate.query(UserQuery.FIND_BY_RESET_TOKEN.getQuery(), new Object[]{resetToken}, new TaUserMapper());
        return users.isEmpty() ? null : users.get(0);
    }
}
