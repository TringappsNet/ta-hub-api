package tahub.sdapitahub.controller;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.constants.JobMessages;
import tahub.sdapitahub.dto.Job.JobDTO;
import tahub.sdapitahub.dto.Job.JobRequirementDTO;
import tahub.sdapitahub.dto.Job.JobRequirementUpdateDTO;
import tahub.sdapitahub.dto.Job.JobTaskDTO;
import tahub.sdapitahub.entity.JobRequirement;
import tahub.sdapitahub.repository.JobRequirementRepository;
import tahub.sdapitahub.service.JobRequirementService;
import tahub.sdapitahub.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Job Requirement", description = "Operations related to job requirements")
@RequestMapping("/api")
public class JobRequirementController {
    @Autowired
    private JobRequirementRepository jobRequirementRepository;
    @Autowired
    private JobRequirementService jobRequirementService;
    @Autowired
    private AuthService authService;

    @PostMapping("/requirement")
    public ResponseEntity<?> createJobRequirements(@RequestBody List<JobRequirementDTO> jobRequirementDTOList) {
        try {
            List<JobRequirement> createdRequirements = new ArrayList<>();
            for (JobRequirementDTO jobRequirementDTO : jobRequirementDTOList) {
                JobRequirement createdRequirement = jobRequirementService.createJobRequirement(jobRequirementDTO);
                createdRequirements.add(createdRequirement);
                jobRequirementService.createTasksPositions(jobRequirementDTO);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRequirements);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (ServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating job requirements.");
        }
    }

    @GetMapping("/requirements")
    public ResponseEntity<List<JobRequirement>> getAllJobRequirements() {
        try {
            List<JobRequirement> jobRequirements = jobRequirementService.getAllJobRequirements();
            if (jobRequirements.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(200).body(jobRequirements);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        } catch (ServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping("/requirement/{id}")
    public ResponseEntity<?> getJobRequirementById(@PathVariable Long id) {
        Optional<JobRequirement> jobRequirement = jobRequirementService.getJobRequirementById(id);
        if (jobRequirement.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(jobRequirement.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job requirement not found with ID: " + id);
        }
    }

    @PutMapping("/requirement/{id}")

    public ResponseEntity<JobRequirement> updateJobRequirement(@PathVariable Long id, @RequestBody JobRequirementUpdateDTO jobRequirementPostDTO) {
        try {
            JobRequirement updatedJobRequirement = jobRequirementService.updateJobRequirement(id, jobRequirementPostDTO);
            return new ResponseEntity<>(updatedJobRequirement, HttpStatus.OK);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/requirement/{id}")
    public ResponseEntity<String> deleteJobRequirement(@PathVariable Long id) {
        jobRequirementService.deleteJobRequirement(id);
        return ResponseEntity.status(HttpStatus.OK).body(JobMessages.JOB_REQ_DELETED.getMessage());
    }

    @PostMapping("/job-approval")
    public ResponseEntity<Object> jobApproval(@RequestBody JobDTO jobDTO) {
        String email = jobDTO.getApprovedBy();
        String clientName = jobDTO.getClientName();
        LocalDate requirementStartDate = jobDTO.getRequirementStartDate();
        List<JobTaskDTO> position = jobDTO.getPosition();

        jobRequirementService.jobApproval(email, clientName, requirementStartDate, position);
        return ResponseEntity.status(HttpStatus.OK).body("Job approval request sent!");
    }

    @PostMapping("/approve-requirement")
    public ResponseEntity<?> validateTokenAndApprove(@RequestParam String token) {
        try {
            jobRequirementService.approveRequirement(token);
            return ResponseEntity.status(HttpStatus.OK).body(JobMessages.JOB_APPROVAL_SUCCESS.getMessage());
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (ServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JobMessages.JOB_ERROR.getMessage());
        }
    }
}
