package tahub.sdapitahub.repository.mapper.TaskCandidates;

import org.springframework.jdbc.core.RowMapper;
import tahub.sdapitahub.entity.TaskCandidateHistory;

import java.sql.ResultSet;
import java.sql.SQLException;

    public class TaskCandidatesComments implements RowMapper<TaskCandidateHistory> {
        @Override
        public TaskCandidateHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new TaskCandidateHistory.Builder()
                    .taskCandidateComments(rs.getString("task_candidate_comments"))
                    .build();
        }
    }
