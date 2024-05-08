package tahub.sdapitahub.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.DTO.JobRequirementDTO;
import tahub.sdapitahub.DTO.TaskDTO;
import tahub.sdapitahub.Entity.JobRequirement;
import tahub.sdapitahub.Repository.JobRequirementRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class JobRequirementService {
    @Autowired
    private JobRequirementRepository jobRequirementRepository;

    @Autowired
    private TaskService taskService;

    public JobRequirement createJobRequirement(JobRequirementDTO jobRequirementDTO) {
        JobRequirement jobRequirement = convertToEntity(jobRequirementDTO);
        jobRequirement.setCreatedAt(LocalDateTime.now());
        return jobRequirementRepository.save(jobRequirement);
    }

    public List<JobRequirement> getAllJobRequirements() {
        List<JobRequirement> jobRequirement = jobRequirementRepository.findAll();
        if (jobRequirement.isEmpty()) {
            return Collections.singletonList(new JobRequirement(/* default values */));
        }
        return jobRequirement;
    }

    public void createTasksPositions(JobRequirementDTO jobRequirementDTO) {
        List<TaskDTO> taskDTOs = jobRequirementDTO.getPositions();
        taskService.createTasks(taskDTOs);
    }


    private JobRequirement convertToEntity(JobRequirementDTO jobRequirementDTO) {
        JobRequirement jobRequirement = new JobRequirement();
        jobRequirement.setRequirementStartDate(jobRequirementDTO.getRequirementStartDate());
        jobRequirement.setClientName(jobRequirementDTO.getClientName());
        jobRequirement.setClientSpocName(jobRequirementDTO.getClientSpocName());
        jobRequirement.setClientSpocContact(jobRequirementDTO.getClientSpocContact());
        jobRequirement.setAccountManager(jobRequirementDTO.getAccountManager());
        jobRequirement.setAccountManagerEmail(jobRequirementDTO.getAccountManagerEmail());
        jobRequirement.setJobTitle(jobRequirementDTO.getJobTitle());
        jobRequirement.setNoOfOpenings(jobRequirementDTO.getNoOfOpenings());
        jobRequirement.setRoleType(jobRequirementDTO.getRoleType());
        jobRequirement.setModeOfWork(jobRequirementDTO.getModeOfWork());
        jobRequirement.setWorkLocation(jobRequirementDTO.getWorkLocation());
        jobRequirement.setSalaryBudget(jobRequirementDTO.getSalaryBudget());
        jobRequirement.setModeOfInterviews(jobRequirementDTO.getModeOfInterviews());
        jobRequirement.setTentativeStartDate(jobRequirementDTO.getTentativeStartDate());
        jobRequirement.setTentativeDuration(jobRequirementDTO.getTentativeDuration());
        jobRequirement.setApprovedBy(jobRequirementDTO.getApprovedBy());
        jobRequirement.setYearsOfExperienceRequired(jobRequirementDTO.getYearsOfExperienceRequired());
        jobRequirement.setPrimarySkillSet(jobRequirementDTO.getPrimarySkillSet());
        jobRequirement.setSecondarySkillSet(jobRequirementDTO.getSecondarySkillSet());
        return jobRequirement;
    }

}
