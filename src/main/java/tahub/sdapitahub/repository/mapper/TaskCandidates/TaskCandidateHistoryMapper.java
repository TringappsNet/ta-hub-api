package tahub.sdapitahub.repository.mapper.TaskCandidates;

import org.springframework.jdbc.core.RowMapper;
import tahub.sdapitahub.entity.TaskCandidateHistory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TaskCandidateHistoryMapper implements RowMapper<TaskCandidateHistory> {
    @Override
    public TaskCandidateHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TaskCandidateHistory.Builder()
                .statusId(rs.getLong("status_id"))
                .taskId(rs.getLong("task_id"))
                .candidateId(rs.getLong("candidate_id"))
                .taskCandidateStatus(rs.getString("task_candidate_status"))
                .taskCandidateComments(rs.getString("task_candidate_comments"))
                .taskStatus(rs.getString("task_status"))
                .modifiedBy(rs.getLong("modified_by"))
                .createdAt(rs.getObject("created_at", LocalDateTime.class))
                .lastUpdated(rs.getObject("last_updated", LocalDateTime.class))
                .build();
    }
}
