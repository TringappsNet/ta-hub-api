
    package tahub.sdapitahub.repository;

import tahub.sdapitahub.entity.Role;
import tahub.sdapitahub.repository.mapper.RoleMapper;
import tahub.sdapitahub.repository.query.RoleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

    @Repository
    public class RoleRepository {

        private final JdbcTemplate jdbcTemplate;

        @Autowired
        public RoleRepository(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        public List<Role> findAll() {
            return jdbcTemplate.query(RoleQuery.FIND_ALL.getQuery(), new RoleMapper());
        }

        public Role findById(Long id) {
            return jdbcTemplate.queryForObject(RoleQuery.FIND_BY_ID.getQuery(), new Object[]{id}, new RoleMapper());
        }

        public Role save(Role role) {
            jdbcTemplate.update(RoleQuery.SAVE.getQuery(),
                    role.getRole());
            return role;
        }

        public Role update(Role role) {
            StringBuilder queryBuilder = new StringBuilder("UPDATE ta_roles SET ");
            List<Object> queryParams = new ArrayList<>();

            // Initialize a flag to track if any fields are updated
            boolean fieldsUpdated = false;

            if (role.getRole() != null) {
                queryBuilder.append("role = ?, ");
                queryParams.add(role.getRole());
                fieldsUpdated = true;
            }

            if (fieldsUpdated) {
                queryBuilder.setLength(queryBuilder.length() - 2);
                queryBuilder.append(" WHERE role_id = ?");
                queryParams.add(role.getRoleId());

                jdbcTemplate.update(queryBuilder.toString(), queryParams.toArray());
            }
            return role;
        }


        public void deleteById(Long id) {
            jdbcTemplate.update(RoleQuery.DELETE_BY_ID.getQuery(), id);
        }
    }


