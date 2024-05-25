package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.Utils.MailUtil;
import tahub.sdapitahub.Utils.TokenUtil;
import tahub.sdapitahub.dto.JobRequirementDTO;
import tahub.sdapitahub.dto.TaskDTO;
import tahub.sdapitahub.entity.JobRequirement;
import tahub.sdapitahub.entity.TaUser;
import tahub.sdapitahub.repository.JobRequirementRepository;
import tahub.sdapitahub.repository.TaUserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobRequirementService {
    @Autowired
    private JobRequirementRepository jobRequirementRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaUserRepository taUserRepository;



    public void JobApproval(String email) {
        TaUser user = taUserRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String token = TokenUtil.generateRandomString();
        String encryptedToken = TokenUtil.encryptToken(token);
        user.setResetToken(encryptedToken);
        taUserRepository.update(user);

        String subject = "Job Approval Request";
        String text = "To Approve, please visit the following link: " +
                "http://localhost:5173/navbar?token=" + encryptedToken;
        MailUtil.sendMail(user.getEmail(), subject, text);
    }

    public JobRequirement createJobRequirement(JobRequirementDTO jobRequirementDTO) {
        JobRequirement jobRequirement = convertToEntity(jobRequirementDTO);
        jobRequirement.setCreatedAt(LocalDateTime.now());
        jobRequirement.setLastUpdated(LocalDateTime.now());
        return jobRequirementRepository.save(jobRequirement);
    }

    public void deleteJobRequirement(Long id) {
        jobRequirementRepository.deleteById(id);
    }

    public JobRequirement updateJobRequirement(Long id, JobRequirement jobRequirement) {
        jobRequirement.setJobId(id);
        jobRequirement.setLastUpdated(LocalDateTime.now());
        return jobRequirementRepository.update(jobRequirement);
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
                .createdAt(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .build();
    }

}
