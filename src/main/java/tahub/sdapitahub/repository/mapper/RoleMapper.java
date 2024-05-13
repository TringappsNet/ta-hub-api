package tahub.sdapitahub.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import tahub.sdapitahub.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;


    public class RoleMapper implements RowMapper<Role> {
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role.Builder()
                    .roleId(rs.getLong("role_id"))
                    .role(rs.getString("role"))
                    .build();
            return role;
        }
    }
