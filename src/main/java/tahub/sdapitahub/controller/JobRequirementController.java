package tahub.sdapitahub.controller;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.constants.JobreqMsgs;
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
    public ResponseEntity<String> createJobRequirements(@RequestBody List<JobRequirementDTO> jobRequirementDTOList) {
        try {
            List<JobRequirement> createdRequirements = new ArrayList<>();
            for (JobRequirementDTO jobRequirementDTO : jobRequirementDTOList) {
                JobRequirement createdRequirement = jobRequirementService.createJobRequirement(jobRequirementDTO);
                createdRequirements.add(createdRequirement);
                jobRequirementService.createTasksPositions(jobRequirementDTO);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(JobreqMsgs.JOB_REQ_CREATED.getMessage());
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (ServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JobreqMsgs.ERROR_JOB_REQ_CREATE.getMessage());
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
    public ResponseEntity<String> updateJobRequirement(@PathVariable Long id, @RequestBody JobRequirementUpdateDTO jobRequirementPostDTO) {
        try {
            JobRequirement updatedJobRequirement = jobRequirementService.updateJobRequirement(id, jobRequirementPostDTO);
            return new ResponseEntity<>(JobreqMsgs.JOB_REQ_UPDATED.getMessage(), HttpStatus.OK);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(JobreqMsgs.JOB_REQ_NOT_FOUND.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/requirement/{id}")
    public ResponseEntity<String> deleteJobRequirement(@PathVariable Long id) {
        JobRequirement existingRequirement = jobRequirementRepository.findById(id)
                .orElse(null);

        if (existingRequirement == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(JobreqMsgs.JOB_REQ_NOT_FOUND.getMessage());
        }

        jobRequirementService.deleteJobRequirement(id);
        return ResponseEntity.status(HttpStatus.OK).body(JobreqMsgs.JOB_REQ_DELETE.getMessage());
    }

    @PostMapping("/job-approval")
    public ResponseEntity<Object> jobApproval(@RequestBody JobDTO jobDTO) {
        try {
            String email = jobDTO.getApprovedBy();
            String clientName = jobDTO.getClientName();
            LocalDate requirementStartDate = jobDTO.getRequirementStartDate();
            List<JobTaskDTO> position = jobDTO.getPosition();

            jobRequirementService.jobApproval(email, clientName, requirementStartDate, position);
            return ResponseEntity.status(HttpStatus.OK).body(JobreqMsgs.JOB_APPROVAL.getMessage());
        } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(JobreqMsgs.JOB_APPROVAL_UNAUTH.getMessage());
        }
    }

    @PostMapping("/approve-requirement")
    public ResponseEntity<?> validateTokenAndApprove(@RequestParam String token) {
        try {
            jobRequirementService.approveRequirement(token);
            return ResponseEntity.status(HttpStatus.OK).body(JobreqMsgs.APPROVE_REQ.getMessage());
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (ServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JobreqMsgs.APPROVE_REQ_ERR.getMessage());
        }
    }
}
