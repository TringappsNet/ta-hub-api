package tahub.sdapitahub.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tahub.sdapitahub.entity.TaskCandidate;
import tahub.sdapitahub.repository.mapper.TaskCandidateMapper;
import tahub.sdapitahub.repository.query.TaskCandidateQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskCandidateRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskCandidateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TaskCandidate> findAll() {
        return jdbcTemplate.query(TaskCandidateQuery.FIND_ALL.getQuery(), new TaskCandidateMapper());
    }

    public Optional<TaskCandidate> findById(Long id) {
        return jdbcTemplate.query(TaskCandidateQuery.FIND_BY_ID.getQuery(), new Object[]{id}, new TaskCandidateMapper())
                .stream().findFirst();
    }

    public TaskCandidate save(TaskCandidate taskCandidate) {
        jdbcTemplate.update(TaskCandidateQuery.SAVE.getQuery(),
                taskCandidate.getTaskId(),
                taskCandidate.getCandidateId(),
                taskCandidate.getTaskCandidateStatus(),
                taskCandidate.getTaskCandidateComments(),
                taskCandidate.getCreatedAt(),
                taskCandidate.getLastUpdated());
        return taskCandidate;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update(TaskCandidateQuery.DELETE_BY_ID.getQuery(), id);
    }

    public TaskCandidate update(TaskCandidate taskCandidate) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE task_candidates SET ");
        List<Object> queryParams = new ArrayList<>();

        // Initialize a flag to track if any fields are updated
        boolean fieldsUpdated = false;

        if (taskCandidate.getTaskCandidateStatus() != null) {
            queryBuilder.append("task_candidate_status = ?, ");
            queryParams.add(taskCandidate.getTaskCandidateStatus());
            fieldsUpdated = true;
        }
        if (taskCandidate.getTaskCandidateComments() != null) {
            queryBuilder.append("task_candidate_comments = ?, ");
            queryParams.add(taskCandidate.getTaskCandidateComments());
            fieldsUpdated = true;
        }

        if (taskCandidate.getLastUpdated() != null) {
            queryBuilder.append("last_updated = ?, ");
            queryParams.add(taskCandidate.getLastUpdated());
            fieldsUpdated = true;
        }

        if (fieldsUpdated) {
            queryBuilder.setLength(queryBuilder.length() - 2);
            queryBuilder.append(" WHERE task_candidates_id = ?");
            queryParams.add(taskCandidate.getTaskCandidatesId());

            jdbcTemplate.update(queryBuilder.toString(), queryParams.toArray());
        }

        return taskCandidate;
    }
}
