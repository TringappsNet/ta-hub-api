package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.dto.JobRequirementDTO;
import tahub.sdapitahub.dto.TaskDTO;
import tahub.sdapitahub.entity.JobRequirement;
import tahub.sdapitahub.entity.Task;
import tahub.sdapitahub.repository.JobRequirementRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobRequirementService {
    @Autowired
    private JobRequirementRepository jobRequirementRepository;

    @Autowired
    private TaskService taskService;

    public JobRequirement createJobRequirement(JobRequirementDTO jobRequirementDTO) {
        JobRequirement jobRequirement = convertToEntity(jobRequirementDTO);
        jobRequirement.setCreatedAt(LocalDateTime.now());
        jobRequirement.setLastUpdated(LocalDateTime.now());
        return jobRequirementRepository.save(jobRequirement);
    }


    public List<JobRequirement> getAllJobRequirements() {
        return jobRequirementRepository.findAll();
    }

    public Optional<JobRequirement> getJobRequirementById(Long id) {
        try {
            return jobRequirementRepository.findById(id);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public void createTasksPositions(JobRequirementDTO jobRequirementDTO) {
        List<JobRequirement> jobRequirements = jobRequirementRepository.findByClientName(jobRequirementDTO.getClientName());
        if (!jobRequirements.isEmpty()) {
            for (JobRequirement jobRequirement : jobRequirements) {
                List<TaskDTO> taskDTOs = jobRequirementDTO.getPositions();
                int noOfOpenings = taskDTOs.get(0).getNoOfOpenings();
                taskService.createTasksForJobRequirement(jobRequirement, taskDTOs, noOfOpenings);
            }
        } else {
            // Handle case when no job requirements are found for the specified client
        }
    }




    private JobRequirement convertToEntity(JobRequirementDTO jobRequirementDTO) {
        return new JobRequirement.Builder()
                .requirementStartDate(jobRequirementDTO.getRequirementStartDate())
                .clientName(jobRequirementDTO.getClientName())
                .clientSpocName(jobRequirementDTO.getClientSpocName())
                .clientSpocContact(jobRequirementDTO.getClientSpocContact())
                .accountManager(jobRequirementDTO.getAccountManager())
                .accountManagerEmail(jobRequirementDTO.getAccountManagerEmail())
                .totalNoOfOpenings(jobRequirementDTO.getTotalNoOfOpenings())
                .salaryBudget(jobRequirementDTO.getSalaryBudget())
                .modeOfInterviews(jobRequirementDTO.getModeOfInterviews())
                .tentativeStartDate(jobRequirementDTO.getTentativeStartDate())
                .tentativeDuration(jobRequirementDTO.getTentativeDuration())
                .approvedBy(jobRequirementDTO.getApprovedBy())
                .yearsOfExperienceRequired(jobRequirementDTO.getYearsOfExperienceRequired())
                .primarySkillSet(jobRequirementDTO.getPrimarySkillSet())
                .secondarySkillSet(jobRequirementDTO.getSecondarySkillSet())
                .createdAt(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .build();
    }

}
