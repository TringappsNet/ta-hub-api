package tahub.sdapitahub.Controller;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.DTO.JobRequirementDTO;
import tahub.sdapitahub.Entity.JobRequirement;
import tahub.sdapitahub.Repository.JobRequirementRepository;
import tahub.sdapitahub.Service.JobRequirementService;
import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JobRequirementController {
    @Autowired
    private JobRequirementRepository jobRequirementRepository;
    @Autowired
    private JobRequirementService jobRequirementService;

    @GetMapping("/hello")
    public String sayHelloWorld() {
        return "Hello World!";
    }

    @PostMapping("/requirement")
    public ResponseEntity<?> createJobRequirement(@RequestBody JobRequirementDTO jobRequirementDTO) {
        try {
            JobRequirement createdRequirement = jobRequirementService.createJobRequirement(jobRequirementDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRequirement);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (ServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating job requirement.");
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
}
