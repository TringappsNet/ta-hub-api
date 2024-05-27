package tahub.sdapitahub.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tahub.sdapitahub.entity.JobRequirement;
import tahub.sdapitahub.repository.mapper.JobRequirementMapper;
import tahub.sdapitahub.repository.query.JobRequirementQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JobRequirementRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<JobRequirement> findAll() {
        return jdbcTemplate.query(JobRequirementQuery.FIND_ALL.getQuery(), new JobRequirementMapper());
    }

    public JobRequirement findByApprovedBy(String email) {
        List<JobRequirement> jobRequirements = jdbcTemplate.query(JobRequirementQuery.FIND_BY_APPROVED_BY.getQuery(), new Object[]{email}, new JobRequirementMapper());
        if (jobRequirements.isEmpty()) {
            return null;
        }
        return jobRequirements.get(0);
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
                jobRequirement.getApprovalStatus(),
                jobRequirement.getApprovalToken(),

                jobRequirement.getCreatedAt(),
                jobRequirement.getLastUpdated()

        );
        return jobRequirement;
    }

    public JobRequirement update(JobRequirement jobRequirement) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE ta_client_requirements SET ");
        List<Object> queryParams = new ArrayList<>();

        // Initialize a flag to track if any fields are updated
        boolean fieldsUpdated = false;

        if (jobRequirement.getClientId() != 0) {
            queryBuilder.append("client_id = ?, ");
            queryParams.add(jobRequirement.getClientId());
            fieldsUpdated = true;
        }
        if (jobRequirement.getRequirementStartDate() != null) {
            queryBuilder.append("requirement_start_date = ?, ");
            queryParams.add(jobRequirement.getRequirementStartDate());
            fieldsUpdated = true;
        }
        if (jobRequirement.getClientName() != null) {
            queryBuilder.append("client_name = ?, ");
            queryParams.add(jobRequirement.getClientName());
            fieldsUpdated = true;
        }
        if (jobRequirement.getClientSpocName() != null) {
            queryBuilder.append("client_spoc_name = ?, ");
            queryParams.add(jobRequirement.getClientSpocName());
            fieldsUpdated = true;
        }
        if (jobRequirement.getClientSpocContact() != null) {
            queryBuilder.append("client_spoc_contact = ?, ");
            queryParams.add(jobRequirement.getClientSpocContact());
            fieldsUpdated = true;
        }
        if (jobRequirement.getAccountManager() != null) {
            queryBuilder.append("account_manager = ?, ");
            queryParams.add(jobRequirement.getAccountManager());
            fieldsUpdated = true;
        }
        if (jobRequirement.getAccountManagerEmail() != null) {
            queryBuilder.append("account_manager_email = ?, ");
            queryParams.add(jobRequirement.getAccountManagerEmail());
            fieldsUpdated = true;
        }
        if (jobRequirement.getTotalNoOfOpenings() != null) {
            queryBuilder.append("total_no_of_openings = ?, ");
            queryParams.add(jobRequirement.getTotalNoOfOpenings());
            fieldsUpdated = true;
        }
        if (jobRequirement.getSalaryBudget() != 0) {
            queryBuilder.append("salary_budget = ?, ");
            queryParams.add(jobRequirement.getSalaryBudget());
            fieldsUpdated = true;
        }
        if (jobRequirement.getModeOfInterviews() != null) {
            queryBuilder.append("mode_of_interviews = ?, ");
            queryParams.add(jobRequirement.getModeOfInterviews());
            fieldsUpdated = true;
        }
        if (jobRequirement.getTentativeStartDate() != null) {
            queryBuilder.append("tentative_start_date = ?, ");
            queryParams.add(jobRequirement.getTentativeStartDate());
            fieldsUpdated = true;
        }
        if (jobRequirement.getTentativeDuration() != null) {
            queryBuilder.append("tentative_duration = ?, ");
            queryParams.add(jobRequirement.getTentativeDuration());
            fieldsUpdated = true;
        }
        if (jobRequirement.getApprovedBy() != null) {
            queryBuilder.append("approved_by = ?, ");
            queryParams.add(jobRequirement.getApprovedBy());
            fieldsUpdated = true;
        }
        if (jobRequirement.getApprovalStatus() != null) {
            queryBuilder.append("approval_status = ?, ");
            queryParams.add(jobRequirement.getApprovalStatus());
            fieldsUpdated = true;
        }
        if (jobRequirement.getApprovalToken() != null) {
            queryBuilder.append("approval_token = ?, ");
            queryParams.add(jobRequirement.getApprovalToken());
            fieldsUpdated = true;
        }
        if (jobRequirement.getLastUpdated() != null) {
            queryBuilder.append("last_updated = ?, ");
            queryParams.add(jobRequirement.getLastUpdated());
            fieldsUpdated = true;
        }

        if (fieldsUpdated) {
            queryBuilder.setLength(queryBuilder.length() - 2);
            queryBuilder.append(" WHERE job_id = ?");
            queryParams.add(jobRequirement.getJobId());

            jdbcTemplate.update(queryBuilder.toString(), queryParams.toArray());
        }

        return jobRequirement;
    }




    public void deleteById(Long id) {
        jdbcTemplate.update(JobRequirementQuery.DELETE_BY_ID.getQuery(), id);
    }
}
