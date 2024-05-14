package tahub.sdapitahub.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import tahub.sdapitahub.entity.Candidate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CandidateMapper implements RowMapper<Candidate> {
    @Override
    public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Candidate.Builder()
                .candidateId(rs.getLong("candidate_id"))
                .candidateName(rs.getString("candidate_name"))
                .candidateEmail(rs.getString("candidate_email"))
                .candidateContact(rs.getString("candidate_contact"))
                .technology(rs.getString("technology"))
                .totalExperience(rs.getString("total_experience"))
                .currentCtc(rs.getString("current_ctc"))
                .expectedCtc(rs.getString("expected_ctc"))
                .noticePeriod(rs.getString("notice_period"))
                .modeOfWork(rs.getString("mode_of_work"))
                .currentLocation(rs.getString("current_location"))
                .candidateStatus(rs.getString("candidate_status"))
                .comments(rs.getString("comments"))
                .remarks(rs.getString("remarks"))
                .recruiter(rs.getString("recruiter"))
                .recruitedSource(rs.getString("recruited_source"))
                .createdDate(rs.getObject( "created_date", LocalDateTime.class))
                .lastUpdated(rs.getObject( "last_updated", LocalDateTime.class))
                .build();
    }

}
