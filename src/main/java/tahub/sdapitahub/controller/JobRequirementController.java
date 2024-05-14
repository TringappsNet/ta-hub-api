package tahub.sdapitahub.controller;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.dto.JobRequirementDTO;
import tahub.sdapitahub.dto.TaUserDTO;
import tahub.sdapitahub.entity.JobRequirement;
import tahub.sdapitahub.entity.TaUser;
import tahub.sdapitahub.repository.JobRequirementRepository;
import tahub.sdapitahub.service.JobRequirementService;
import tahub.sdapitahub.service.AuthService;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class JobRequirementController {
    @Autowired
    private JobRequirementRepository jobRequirementRepository;
    @Autowired
    private JobRequirementService jobRequirementService;
    @Autowired
    private AuthService authService;

    @GetMapping("/hello")
    public String sayHelloWorld() {
        return "Hello World!";
    }

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



    @GetMapping("/requirement")
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

    @PostMapping("/register")
    public ResponseEntity<TaUser> register(@RequestBody TaUser user) {
        TaUser registeredUser = authService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }
}
