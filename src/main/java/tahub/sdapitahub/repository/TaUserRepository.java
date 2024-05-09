package tahub.sdapitahub.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tahub.sdapitahub.entity.TaUser;
import tahub.sdapitahub.repository.mapper.TaUserMapper;
import tahub.sdapitahub.repository.query.User;

import java.util.List;
import java.util.Optional;

@Repository
public class TaUserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TaUser> findAll() {
        return jdbcTemplate.query(User.FIND_ALL.getQuery(), new TaUserMapper());
    }

    public Optional<TaUser> findById(Long id) {
        return jdbcTemplate.query(User.FIND_BY_ID.getQuery(), new Object[]{id}, new TaUserMapper()).stream().findFirst();
    }

    public TaUser save(TaUser user) {
        jdbcTemplate.update(User.SAVE.getQuery(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPhone(),
                user.getResetToken(), user.getPassword(), user.getIsActive(), user.getCurrentSessionId(), user.getLastLoginTime(), user.getCreatedAt());
        return user;
    }

    public TaUser update(TaUser user) {
        jdbcTemplate.update(User.UPDATE.getQuery(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPhone(),
                user.getResetToken(), user.getPassword(), user.getIsActive(), user.getCurrentSessionId(), user.getLastLoginTime(), user.getCreatedAt(), user.getUserId());
        return user;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update(User.DELETE_BY_ID.getQuery(), id);
    }

    public TaUser findByEmail(String email) {
        List<TaUser> users = jdbcTemplate.query(User.FIND_BY_EMAIL.getQuery(), new Object[]{email}, new TaUserMapper());
        return users.isEmpty() ? null : users.get(0);
    }

    public TaUser findByResetToken(String resetToken) {
        List<TaUser> users = jdbcTemplate.query(User.FIND_BY_RESET_TOKEN.getQuery(), new Object[]{resetToken}, new TaUserMapper());
        return users.isEmpty() ? null : users.get(0);
    }
}
