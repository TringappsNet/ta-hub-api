package tahub.sdapitahub.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tahub.sdapitahub.entity.Task;
import tahub.sdapitahub.repository.mapper.TaskMapper;
import tahub.sdapitahub.repository.query.TaskQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> findAll() {
        return jdbcTemplate.query(TaskQuery.FIND_ALL.getQuery(), new TaskMapper());
    }

    public Optional<Task> findById(Long id) {
        return jdbcTemplate.query(TaskQuery.FIND_BY_ID.getQuery(), new Object[]{id}, new TaskMapper())
                .stream().findFirst();
    }

    public Task save(Task task) {
        jdbcTemplate.update(TaskQuery.SAVE.getQuery(),
                task.getJobId(),
                task.getJobTitle(),
                task.getRoleType(),
                task.getModeOfWork(),
                task.getWorkLocation(),
                task.getClientBudget(),
                task.getAssignedBudget(),
                task.getPrimaryAssignee(),
                task.getSecondaryAssignee(),
                task.getTaskStatus(),
                task.isBacklogs(),
                task.getDescription(),
                task.getCreatedAt(),
                task.getLastUpdated());
        return task;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update(TaskQuery.DELETE_BY_ID.getQuery(), id);
    }

    public Task update(Task task) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE tasks SET ");
        List<Object> queryParams = new ArrayList<>();

        // Initialize a flag to track if any fields are updated
        boolean fieldsUpdated = false;

        if (task.getJobTitle() != null) {
            queryBuilder.append("job_title = ?, ");
            queryParams.add(task.getJobTitle());
            fieldsUpdated = true;
        }
        if (task.getRoleType() != null) {
            queryBuilder.append("role_type = ?, ");
            queryParams.add(task.getRoleType());
            fieldsUpdated = true;
        }
        if (task.getModeOfWork() != null) {
            queryBuilder.append("mode_of_work = ?, ");
            queryParams.add(task.getModeOfWork());
            fieldsUpdated = true;
        }
        if (task.getWorkLocation() != null) {
            queryBuilder.append("work_location = ?, ");
            queryParams.add(task.getWorkLocation());
            fieldsUpdated = true;
        }
        if (task.getPrimaryAssignee() != null) {
            queryBuilder.append("primary_assignee = ?, ");
            queryParams.add(task.getPrimaryAssignee());
            fieldsUpdated = true;
        }
        if (task.getSecondaryAssignee() != null) {
            queryBuilder.append("secondary_assignee = ?, ");
            queryParams.add(task.getSecondaryAssignee());
            fieldsUpdated = true;
        }
        if (task.getTaskStatus() != null) {
            queryBuilder.append("task_status = ?, ");
            queryParams.add(task.getTaskStatus());
            fieldsUpdated = true;
        }
        if (task.isBacklogs()) {
            queryBuilder.append("backlogs = ?, ");
            queryParams.add(task.isBacklogs());
            fieldsUpdated = true;
        }
        if (task.getDescription() != null) {
            queryBuilder.append("description = ?, ");
            queryParams.add(task.getDescription());
            fieldsUpdated = true;
        }

        if (task.getLastUpdated() != null) {
            queryBuilder.append("last_updated = ?, ");
            queryParams.add(task.getLastUpdated());
            fieldsUpdated = true;
        }

        if (fieldsUpdated) {
            queryBuilder.setLength(queryBuilder.length() - 2);
            queryBuilder.append(" WHERE task_id = ?");
            queryParams.add(task.getTaskId());

            jdbcTemplate.update(queryBuilder.toString(), queryParams.toArray());
        }

        return task;
    }

}
