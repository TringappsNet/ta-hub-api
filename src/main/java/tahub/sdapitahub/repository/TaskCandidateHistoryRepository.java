package tahub.sdapitahub.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tahub.sdapitahub.entity.TaskCandidateHistory;
import tahub.sdapitahub.repository.mapper.TaskCandidateHistoryMapper;
import tahub.sdapitahub.repository.query.TaskCandidateHistoryQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskCandidateHistoryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskCandidateHistoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TaskCandidateHistory> findAll() {
        return jdbcTemplate.query(TaskCandidateHistoryQuery.FIND_ALL.getQuery(), new TaskCandidateHistoryMapper());
    }


    public Optional<TaskCandidateHistory> findById(Long id) {
        return jdbcTemplate.query(TaskCandidateHistoryQuery.FIND_BY_ID.getQuery(), new Object[]{id}, new TaskCandidateHistoryMapper())
                .stream().findFirst();
    }

    public TaskCandidateHistory save(TaskCandidateHistory taskCandidateHistory) {
        jdbcTemplate.update(TaskCandidateHistoryQuery.SAVE.getQuery(),
                taskCandidateHistory.getTaskId(),
                taskCandidateHistory.getCandidateId(),
                taskCandidateHistory.getTaskCandidateStatus(),
                taskCandidateHistory.getTaskCandidateComments(),
                taskCandidateHistory.getTaskStatus(),
                taskCandidateHistory.getModifiedBy(),
                taskCandidateHistory.getCreatedAt(),
                taskCandidateHistory.getLastUpdated());
        return taskCandidateHistory;
    }


    public void deleteById(Long id) {
        jdbcTemplate.update(TaskCandidateHistoryQuery.DELETE_BY_ID.getQuery(), id);
    }

    public TaskCandidateHistory update(TaskCandidateHistory taskCandidateHistory) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE ta_task_candidates SET ");
        List<Object> queryParams = new ArrayList<>();

        // Initialize a flag to track if any fields are updated
        boolean fieldsUpdated = false;

        if (taskCandidateHistory.getTaskId() != null) {
            queryBuilder.append("task_id = ?, ");
            queryParams.add(taskCandidateHistory.getTaskId());
            fieldsUpdated = true;
        }
        if (taskCandidateHistory.getCandidateId() != null) {
            queryBuilder.append("candidate_id = ?, ");
            queryParams.add(taskCandidateHistory.getCandidateId());
            fieldsUpdated = true;
        }
        if (taskCandidateHistory.getTaskCandidateStatus() != null) {
            queryBuilder.append("task_candidate_status = ?, ");
            queryParams.add(taskCandidateHistory.getTaskCandidateStatus());
            fieldsUpdated = true;
        }
        if (taskCandidateHistory.getTaskCandidateComments() != null) {
            queryBuilder.append("task_candidate_comments = ?, ");
            queryParams.add(taskCandidateHistory.getTaskCandidateComments());
            fieldsUpdated = true;
        }
        if (taskCandidateHistory.getTaskStatus() != null) {
            queryBuilder.append("task_status = ?, ");
            queryParams.add(taskCandidateHistory.getTaskStatus());
            fieldsUpdated = true;
        }
        if (taskCandidateHistory.getModifiedBy() != null) {
            queryBuilder.append("modified_by = ?, ");
            queryParams.add(taskCandidateHistory.getModifiedBy());
            fieldsUpdated = true;
        }
        if (taskCandidateHistory.getCreatedAt() != null) {
            queryBuilder.append("created_at = ?, ");
            queryParams.add(taskCandidateHistory.getCreatedAt());
            fieldsUpdated = true;
        }
        if (taskCandidateHistory.getLastUpdated() != null) {
            queryBuilder.append("last_updated = ?, ");
            queryParams.add(taskCandidateHistory.getLastUpdated());
            fieldsUpdated = true;
        }

        if (fieldsUpdated) {
            queryBuilder.setLength(queryBuilder.length() - 2);
            queryBuilder.append(" WHERE status_id = ?");
            queryParams.add(taskCandidateHistory.getStatusId());

            jdbcTemplate.update(queryBuilder.toString(), queryParams.toArray());
        }

        return taskCandidateHistory;
    }
}
