package tahub.sdapitahub.repository.Candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tahub.sdapitahub.entity.Candidate;
import tahub.sdapitahub.repository.mapper.Candidate.CandidateMapper;
import tahub.sdapitahub.repository.mapper.Candidate.CandidateViewMapper;
import tahub.sdapitahub.repository.query.CandidateQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CandidateRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Candidate> findAll() {
        return jdbcTemplate.query(CandidateQuery.FIND_ALL.getQuery(), new CandidateMapper());
    }

    public List<Candidate> candidatesViewAll() {
        return jdbcTemplate.query(CandidateQuery.CANDIDATE_STATUS.getQuery(), new CandidateViewMapper());
    }

    public Optional<Candidate> findById(Long id) {
        return jdbcTemplate.query(CandidateQuery.FIND_BY_ID.getQuery(), new Object[]{id}, new CandidateMapper()).stream().findFirst();
    }

    public Candidate save(Candidate candidate) {
        jdbcTemplate.update(CandidateQuery.SAVE.getQuery(), candidate.getCandidateName(), candidate.getCandidateEmail(), candidate.getCandidateContact(),
                candidate.getTechnology(), candidate.getTotalExperience(), candidate.getCurrentCtc(), candidate.getExpectedCtc(), candidate.getNoticePeriod(),
                candidate.getModeOfWork(), candidate.getCurrentLocation(), candidate.getCandidateStatus(), candidate.getComments(), candidate.getRemarks(),
                candidate.getRecruiter(), candidate.getRecruitedSource(), candidate.getCreatedDate(), candidate.getLastUpdated());
        return candidate;
    }

    public Candidate update(Candidate candidate) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE ta_candidate_details SET ");
        List<Object> queryParams = new ArrayList<>();

        // Initialize a flag to track if any fields are updated
        boolean fieldsUpdated = false;

        if (candidate.getCandidateName() != null) {
            queryBuilder.append("candidate_name = ?, ");
            queryParams.add(candidate.getCandidateName());
            fieldsUpdated = true;
        }
        if (candidate.getCandidateEmail() != null) {
            queryBuilder.append("candidate_email = ?, ");
            queryParams.add(candidate.getCandidateEmail());
            fieldsUpdated = true;
        }
        if (candidate.getCandidateContact() != null) {
            queryBuilder.append("candidate_contact = ?, ");
            queryParams.add(candidate.getCandidateContact());
            fieldsUpdated = true;
        }
        if (candidate.getTechnology() != null) {
            queryBuilder.append("technology = ?, ");
            queryParams.add(candidate.getTechnology());
            fieldsUpdated = true;
        }
        if (candidate.getTotalExperience() != null) {
            queryBuilder.append("total_experience = ?, ");
            queryParams.add(candidate.getTotalExperience());
            fieldsUpdated = true;
        }
        if (candidate.getCurrentCtc() != null) {
            queryBuilder.append("current_ctc = ?, ");
            queryParams.add(candidate.getCurrentCtc());
            fieldsUpdated = true;
        }
        if (candidate.getExpectedCtc() != null) {
            queryBuilder.append("expected_ctc = ?, ");
            queryParams.add(candidate.getExpectedCtc());
            fieldsUpdated = true;
        }
        if (candidate.getNoticePeriod() != null) {
            queryBuilder.append("notice_period = ?, ");
            queryParams.add(candidate.getNoticePeriod());
            fieldsUpdated = true;
        }
        if (candidate.getModeOfWork() != null) {
            queryBuilder.append("mode_of_work = ?, ");
            queryParams.add(candidate.getModeOfWork());
            fieldsUpdated = true;
        }
        if (candidate.getCurrentLocation() != null) {
            queryBuilder.append("current_location = ?, ");
            queryParams.add(candidate.getCurrentLocation());
            fieldsUpdated = true;
        }
        if (candidate.getCandidateStatus() != null) {
            queryBuilder.append("candidate_status = ?, ");
            queryParams.add(candidate.getCandidateStatus());
            fieldsUpdated = true;
        }
        if (candidate.getComments() != null) {
            queryBuilder.append("comments = ?, ");
            queryParams.add(candidate.getComments());
            fieldsUpdated = true;
        }
        if (candidate.getRemarks() != null) {
            queryBuilder.append("remarks = ?, ");
            queryParams.add(candidate.getRemarks());
            fieldsUpdated = true;
        }
        if (candidate.getRecruiter() != null) {
            queryBuilder.append("recruiter = ?, ");
            queryParams.add(candidate.getRecruiter());
            fieldsUpdated = true;
        }
        if (candidate.getRecruitedSource() != null) {
            queryBuilder.append("recruited_source = ?, ");
            queryParams.add(candidate.getRecruitedSource());
            fieldsUpdated = true;
        }
        if (candidate.getCreatedDate() != null) {
            queryBuilder.append("created_date = ?, ");
            queryParams.add(candidate.getCreatedDate());
            fieldsUpdated = true;
        }
        if (candidate.getLastUpdated() != null) {
            queryBuilder.append("last_updated = ?, ");
            queryParams.add(candidate.getLastUpdated());
            fieldsUpdated = true;
        }

        if (fieldsUpdated) {
            queryBuilder.setLength(queryBuilder.length() - 2);
            queryBuilder.append(" WHERE candidate_id = ?");
            queryParams.add(candidate.getCandidateId());

            jdbcTemplate.update(queryBuilder.toString(), queryParams.toArray());
        }

        return candidate;
    }


    public void deleteById(Long id) {
        jdbcTemplate.update(CandidateQuery.DELETE_BY_ID.getQuery(), id);
    }
}
