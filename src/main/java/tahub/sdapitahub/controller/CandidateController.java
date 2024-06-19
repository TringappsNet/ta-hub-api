package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.constants.BoardContMsgs;
import tahub.sdapitahub.constants.ClientMsgs;
import tahub.sdapitahub.dto.Candidate.CandidateCreateDTO;
import tahub.sdapitahub.dto.Candidate.CandidateUpdateDTO;
import tahub.sdapitahub.entity.Candidate;
import tahub.sdapitahub.entity.Client;
import tahub.sdapitahub.service.CandidateService;

import java.util.List;
import java.util.Optional;
import tahub.sdapitahub.constants.CandidateMsgs;


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
    public ResponseEntity<Candidate> getCandidateById(@PathVariable("id") Long id) {
        Optional<Candidate> candidateOptional = candidateService.getCandidateById(id);
        if (candidateOptional.isPresent()) {
            return new ResponseEntity<>(candidateOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   @PostMapping("/candidate")
    public ResponseEntity<Candidate> createCandidate(@RequestBody CandidateCreateDTO candidatePostDTO) {
    Candidate createdCandidate = candidateService.createCandidate(candidatePostDTO);
   if( createdCandidate!=null) {
//        return new ResponseEntity<>(createdCandidate, HttpStatus.CREATED);
            return ResponseEntity.status(200).body(CandidateMsgs.CANDIDATE_CREATED.getMessage());
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CandidateMsgs.CANDIDATE_NOT_CREATED.getMessage());

        }
    }

    @PutMapping("/candidate/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable("id") Long id, @RequestBody CandidateUpdateDTO candidatePutDTO) {
        try {
            Candidate updatedCandidate = candidateService.updateCandidate(id, candidatePutDTO);
            return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/candidate/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable("id") Long id) {
            candidateService.deleteCandidate(id);
            return ResponseEntity.status(HttpStatus.OK).body(CandidateMsgs.CANDIDATE_DELETED.getMessage());

    }
}
