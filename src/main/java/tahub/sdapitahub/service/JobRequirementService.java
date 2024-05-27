package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.Utils.MailUtil;
import tahub.sdapitahub.Utils.TokenUtil;
import tahub.sdapitahub.dto.JobRequirementDTO;
import tahub.sdapitahub.dto.TaskDTO;
import tahub.sdapitahub.entity.JobRequirement;
import tahub.sdapitahub.entity.Task;
import tahub.sdapitahub.repository.JobRequirementRepository;
import tahub.sdapitahub.repository.TaUserRepository;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobRequirementService {
    @Autowired
    private JobRequirementRepository jobRequirementRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaUserRepository taUserRepository;




    public void jobApproval(String email, String clientName, LocalDate requirementStartDate, List<TaskDTO> positions) {
        JobRequirement jobRequirement = jobRequirementRepository.findByApprovedBy(email);
        if (jobRequirement == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String token = TokenUtil.generateRandomString();
        String encryptedToken = TokenUtil.encryptToken(token);
        jobRequirement.setApprovalToken(encryptedToken);
        jobRequirementRepository.update(jobRequirement);

        String subject = "Job Approval Request";
        String text = "Hey " + ", you have a client requirement to approve, for the client " + clientName +
                ", which starts from " + requirementStartDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                " with " + positions.stream()
                .map(p -> p.getNoOfOpenings() + " openings on the " + p.getJobTitle())
                .collect(Collectors.joining(" and ")) + " To Approve, please visit the following link: " +
        "http://localhost:5173/approval-req?token=" + encryptedToken;

        MailUtil.sendMail(jobRequirement.getApprovedBy(), subject, text);
    }


    public void approveRequirement(String token) {
        List<JobRequirement> jobRequirements = jobRequirementRepository.findByApprovalToken(token);
        if (jobRequirements.isEmpty()) {
            throw new ValidationException("Job requirement not found with approval token: " + token);
        }

        JobRequirement jobRequirement = jobRequirements.get(0);

        if (!TokenUtil.isApprovalTokenValid(jobRequirement, token)) {
            throw new ValidationException("Invalid token");
        }

        // Update job requirement approval status
        jobRequirement.setApprovalStatus(true);
        jobRequirement.setLastUpdated(LocalDateTime.now());
        jobRequirementRepository.update(jobRequirement);

        // Update associated tasks' approval status
        List<Task> tasks = taskService.getTasksByJobId(jobRequirement.getJobId());
        for (Task task : tasks) {
            task.setApprovalStatus(true);
            task.setLastUpdated(LocalDateTime.now());
            taskService.updateTask(task.getTaskId(), task);
        }
    }


    public JobRequirement createJobRequirement(JobRequirementDTO jobRequirementDTO) {
        JobRequirement jobRequirement = convertToEntity(jobRequirementDTO);
        jobRequirement.setApprovalStatus(false);
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
                .approvalStatus(jobRequirementDTO.getApprovalStatus())
                .approvalToken(jobRequirementDTO.getApprovalToken())

                .createdAt(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .build();
    }

}
