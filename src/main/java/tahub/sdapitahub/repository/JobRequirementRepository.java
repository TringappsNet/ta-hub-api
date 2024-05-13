package tahub.sdapitahub.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tahub.sdapitahub.entity.JobRequirement;
import tahub.sdapitahub.repository.mapper.JobRequirementMapper;
import tahub.sdapitahub.repository.query.JobRequirementQuery;

import java.util.List;
import java.util.Optional;

@Repository
public class JobRequirementRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<JobRequirement> findAll() {
        return jdbcTemplate.query(JobRequirementQuery.FIND_ALL.getQuery(), new JobRequirementMapper());
    }

    public Optional<JobRequirement> findById(Long id) {
        return jdbcTemplate.query(JobRequirementQuery.FIND_BY_ID.getQuery(), new Object[]{id}, new JobRequirementMapper())
                .stream()
                .findFirst();
    }
    public List<JobRequirement> findByClientName(String clientName) {
        return jdbcTemplate.query(JobRequirementQuery.FIND_BY_CLIENT_NAME.getQuery(), new Object[]{clientName}, new JobRequirementMapper());
    }


    public JobRequirement save(JobRequirement jobRequirement) {
        jdbcTemplate.update(
                JobRequirementQuery.SAVE.getQuery(),
                jobRequirement.getRequirementStartDate(),
                jobRequirement.getClientName(),
                jobRequirement.getClientSpocName(),
                jobRequirement.getClientSpocContact(),
                jobRequirement.getAccountManager(),
                jobRequirement.getAccountManagerEmail(),
                jobRequirement.getTotalNoOfOpenings(),
                jobRequirement.getSalaryBudget(),
                jobRequirement.getModeOfInterviews(),
                jobRequirement.getTentativeStartDate(),
                jobRequirement.getTentativeDuration(),
                jobRequirement.getApprovedBy(),
                jobRequirement.getYearsOfExperienceRequired(),
                jobRequirement.getPrimarySkillSet(),
                jobRequirement.getSecondarySkillSet(),
                jobRequirement.getCreatedAt(),
                jobRequirement.getLastUpdated()

        );
        return jobRequirement;
    }


}
