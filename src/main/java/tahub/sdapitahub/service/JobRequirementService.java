package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.Utils.MailUtil;
import tahub.sdapitahub.Utils.TokenUtil;
import tahub.sdapitahub.dto.Job.JobRequirementDTO;
import tahub.sdapitahub.dto.Job.JobRequirementUpdateDTO;
import tahub.sdapitahub.dto.Job.JobApprovalTaskDTO;
import tahub.sdapitahub.dto.Task.TaskDTO;
import tahub.sdapitahub.entity.JobRequirement;
import tahub.sdapitahub.repository.JobRequirementRepository;
import tahub.sdapitahub.repository.TaUserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void jobApproval(String email, String clientName, LocalDate requirementStartDate, List<JobApprovalTaskDTO> positions) {
        JobRequirement jobRequirement = jobRequirementRepository.findByApprovedBy(email);
        if (jobRequirement == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String token = TokenUtil.generateRandomString();
        jobRequirement.setApprovalToken(token);
        jobRequirementRepository.update(jobRequirement);

        String subject = "Job Approval Request";
        String text = "Hey " + ", you have a client requirement to approve, for the client " + clientName +
                ", which starts from " + requirementStartDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                " with " + positions.stream()
                .map(p -> p.getNoOfOpenings() + " openings on the " + p.getJobTitle())
                .collect(Collectors.joining(" and ")) + " To Approve, please visit the following link: " +
                MailUtil.BASE_URL + "/approval-req?token=" + token;

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
        jobRequirement.setApprovalStatus(true);
        jobRequirement.setLastUpdated(LocalDateTime.now());
        jobRequirementRepository.update(jobRequirement);



    }

    public JobRequirement createJobRequirement(JobRequirementDTO jobRequirementDTO) {
        JobRequirement jobRequirement = convertToEntity(jobRequirementDTO);
        jobRequirement.setApprovalStatus(false);
        jobRequirement.setCreatedAt(LocalDateTime.now());
        jobRequirement.setLastUpdated(LocalDateTime.now());
        return jobRequirementRepository.save(jobRequirement);
    }

    public void deleteJobRequirement(Long id) {

        jdbcTemplate.update("DELETE FROM ta_tasks WHERE job_id = ?", id);
        jobRequirementRepository.deleteById(id);
    }

    public JobRequirement updateJobRequirement(Long id, JobRequirementUpdateDTO jobRequirementPostDTO) {
        Optional<JobRequirement> existingRequirementOpt = jobRequirementRepository.findById(id);
        if (!existingRequirementOpt.isPresent()) {
            throw new ValidationException("Job requirement not found with ID: " + id);
        }
        JobRequirement existingRequirement = existingRequirementOpt.get();
        existingRequirement.setClientName(jobRequirementPostDTO.getClientName());
        existingRequirement.setClientSpocName(jobRequirementPostDTO.getClientSpocName());
        existingRequirement.setClientSpocContact(jobRequirementPostDTO.getClientSpocContact());
        existingRequirement.setAccountManager(jobRequirementPostDTO.getAccountManager());
        existingRequirement.setAccountManagerEmail(jobRequirementPostDTO.getAccountManagerEmail());
        existingRequirement.setTotalNoOfOpenings(jobRequirementPostDTO.getTotalNoOfOpenings());
        existingRequirement.setSalaryBudget(jobRequirementPostDTO.getSalaryBudget());
        existingRequirement.setModeOfInterviews(jobRequirementPostDTO.getModeOfInterviews());
        existingRequirement.setTentativeStartDate(jobRequirementPostDTO.getTentativeStartDate());
        existingRequirement.setTentativeDuration(jobRequirementPostDTO.getTentativeDuration());
        existingRequirement.setApprovedBy(jobRequirementPostDTO.getApprovedBy());
        existingRequirement.setLastUpdated(LocalDateTime.now());
        return jobRequirementRepository.update(existingRequirement);
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
        }

    }
    private JobRequirement convertToEntity(JobRequirementDTO jobRequirementDTO) {
        return new JobRequirement.Builder()
                .requirementStartDate(jobRequirementDTO.getRequirementStartDate())
                .clientId(jobRequirementDTO.getClientId())
                .clientName(jobRequirementDTO.getClientName())
                .clientSpocName(jobRequirementDTO.getClientSpocName())
                .clientSpocContact(jobRequirementDTO.getClientSpocContact())
                .accountManager(jobRequirementDTO.getAccountManager())
                .accountManagerEmail(jobRequirementDTO.getAccountManagerEmail())
                .totalNoOfOpenings(jobRequirementDTO.getTotalNoOfOpenings())
                .salaryBudget(jobRequirementDTO.getSalaryBudget())
                .modeOfInterviews(jobRequirementDTO.getModeOfInterviews())

                .tentativeDuration(jobRequirementDTO.getTentativeDuration())
                .approvedBy(jobRequirementDTO.getApprovedBy())
                .createdAt(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .build();
    }
}



