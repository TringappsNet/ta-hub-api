package tahub.sdapitahub.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import tahub.sdapitahub.entity.JobRequirement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JobRequirementMapper implements RowMapper<JobRequirement> {

    @Override
    public JobRequirement mapRow(ResultSet resultSet, int i) throws SQLException {
        JobRequirement.Builder builder = new JobRequirement.Builder()
                .jobId(resultSet.getLong("job_id"))
                .clientId(resultSet.getLong("client_id"))
                .requirementStartDate(resultSet.getObject("requirement_start_date", LocalDate.class))
                .clientName(resultSet.getString("client_name"))
                .clientSpocName(resultSet.getString("client_spoc_name"))
                .clientSpocContact(resultSet.getString("client_spoc_contact"))
                .accountManager(resultSet.getString("account_manager"))
                .accountManagerEmail(resultSet.getString("account_manager_email"))
                .totalNoOfOpenings(resultSet.getString("total_no_of_openings"))
                .salaryBudget(resultSet.getFloat("salary_budget"))
                .modeOfInterviews(resultSet.getString("mode_of_interviews"))
                .tentativeStartDate(resultSet.getObject("tentative_start_date", LocalDate.class))
                .tentativeDuration(resultSet.getString("tentative_duration"))
                .approvedBy(resultSet.getString("approved_by"))
                .yearsOfExperienceRequired(resultSet.getInt("years_of_experience_required"))
                .primarySkillSet(resultSet.getString("primary_skill_set"))
                .secondarySkillSet(resultSet.getString("secondary_skill_set"))
                .createdAt(resultSet.getObject("created_at", LocalDateTime.class))
                .lastUpdated(resultSet.getObject("last_updated", LocalDateTime.class));

        return builder.build();
    }
}
