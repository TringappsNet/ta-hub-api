package tahub.sdapitahub.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import tahub.sdapitahub.entity.TaskCandidate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TaskCandidateMapper implements RowMapper<TaskCandidate> {
    @Override
    public TaskCandidate mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TaskCandidate.Builder()
                .taskCandidatesId(rs.getLong("task_candidates_id"))
                .taskId(rs.getLong("task_id"))
                .candidateId(rs.getLong("candidate_id"))
                .taskCandidateStatus(rs.getString("task_candidate_status"))
                .taskCandidateComments(rs.getString("task_candidate_comments"))
                .createdAt(rs.getObject("created_at", LocalDateTime.class))
                .lastUpdated(rs.getObject("last_updated", LocalDateTime.class))
                .build();
    }
}
