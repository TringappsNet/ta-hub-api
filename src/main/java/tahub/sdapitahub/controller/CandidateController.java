package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.dto.Candidate.CandidateCreateDTO;
import tahub.sdapitahub.dto.Candidate.CandidateUpdateDTO;
import tahub.sdapitahub.entity.Candidate;
import tahub.sdapitahub.service.CandidateService;
import tahub.sdapitahub.constants.CandidateMsgs;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Candidates", description = "Operations related to Candidates")
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateService.getAllCandidates();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Candidate>> getAllCandidatesStatus() {
        List<Candidate> candidates = candidateService.candidatesViewAll();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @GetMapping("/candidate/{id}")
    public ResponseEntity<?> getCandidateById(@PathVariable("id") Long id) {
        Optional<Candidate> candidateOptional = candidateService.getCandidateById(id);
        if (candidateOptional.isPresent()) {
            return new ResponseEntity<>(candidateOptional.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CandidateMsgs.CANDIDATE_NOT_FOUND.getMessage());
        }
    }




    @PostMapping("/candidate")
    public ResponseEntity<String > createCandidate(@RequestBody CandidateCreateDTO candidatePostDTO) {
        Candidate createdCandidate = candidateService.createCandidate(candidatePostDTO);
        if (createdCandidate != null) {
            return ResponseEntity.status(HttpStatus.OK).body(CandidateMsgs.CANDIDATE_CREATED.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CandidateMsgs.ERROR_CREATE.getMessage());
        }
    }

    @PutMapping("/candidate/{id}")
    public ResponseEntity<String> updateCandidate(@PathVariable("id") Long id, @RequestBody CandidateUpdateDTO candidatePutDTO) {
        Optional<Candidate> existingCandidate = candidateService.getCandidateById(id);
        if (!existingCandidate.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CandidateMsgs.CANDIDATE_NOT_FOUND.getMessage());
        }
            Candidate updatedCandidate = candidateService.updateCandidate(id, candidatePutDTO);
        if (updatedCandidate != null) {
            return ResponseEntity.status(HttpStatus.OK).body(CandidateMsgs.CANDIDATE_UPDATED.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CandidateMsgs.ERROR_UPDATE.getMessage());
        }
    }

    @DeleteMapping("/candidate/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable("id") Long id) {
        Optional<Candidate> existingCandidate = candidateService.getCandidateById(id);

        if (!existingCandidate.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CandidateMsgs.CANDIDATE_NOT_FOUND.getMessage());
        } else {

            candidateService.deleteCandidate(id);
            return ResponseEntity.status(HttpStatus.OK).body(CandidateMsgs.CANDIDATE_DELETED.getMessage());
        }
    }
}
