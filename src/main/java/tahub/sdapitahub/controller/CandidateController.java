////package tahub.sdapitahub.controller;
////
////import io.swagger.v3.oas.annotations.tags.Tag;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////import tahub.sdapitahub.entity.Candidate;
////import tahub.sdapitahub.service.CandidateService;
////
////import java.util.List;
////import java.util.Optional;
////
////@RestController
////@Tag(name = "Candidates", description = "Operations related to Candidates")
////@RequestMapping("/api/candidates")
////public class CandidateController {
////
////    @Autowired
////    private CandidateService candidateService;
////
////    @GetMapping("/")
////    public ResponseEntity<List<Candidate>> getAllCandidates() {
////        List<Candidate> candidates = candidateService.getAllCandidates();
////        return new ResponseEntity<>(candidates, HttpStatus.OK);
////    }
////
////    @GetMapping("/status")
////    public ResponseEntity<List<Candidate>> getAllCandidatesStatus() {
////        List<Candidate> candidates = candidateService.candidatesViewAll();
////        return new ResponseEntity<>(candidates, HttpStatus.OK);
////    }
////
////    @GetMapping("/candidate/{id}")
////    public ResponseEntity<Candidate> getCandidateById(@PathVariable("id") Long id) {
////        Optional<Candidate> candidateOptional = candidateService.getCandidateById(id);
////        if (candidateOptional.isPresent()) {
////            return new ResponseEntity<>(candidateOptional.get(), HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////    }
////
////    @PostMapping("/candidate")
////    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) {
////        Candidate createdCandidate = candidateService.createCandidate(candidate);
////        return new ResponseEntity<>(createdCandidate, HttpStatus.CREATED);
////    }
////
////    @PutMapping("/candidate/{id}")
////    public ResponseEntity<Candidate> updateCandidate(@PathVariable("id") Long id, @RequestBody Candidate candidate) {
////        Candidate updatedCandidate = candidateService.updateCandidate(id, candidate);
////        return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
////    }
////
////    @DeleteMapping("/candidate/{id}")
////    public ResponseEntity<Void> deleteCandidate(@PathVariable("id") Long id) {
////        candidateService.deleteCandidate(id);
////        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////    }
////}
//
//
//package tahub.sdapitahub.controller;
//
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import tahub.sdapitahub.dto.CandidatePostDTO;
//import tahub.sdapitahub.entity.Candidate;
//import tahub.sdapitahub.service.CandidateService;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@Tag(name = "Candidates", description = "Operations related to Candidates")
//@RequestMapping("/api/candidates")
//public class CandidateController {
//
//    @Autowired
//    private CandidateService candidateService;
//
//    @GetMapping("/")
//    public ResponseEntity<List<Candidate>> getAllCandidates() {
//        List<Candidate> candidates = candidateService.getAllCandidates();
//        return new ResponseEntity<>(candidates, HttpStatus.OK);
//    }
//
//    @GetMapping("/status")
//    public ResponseEntity<List<Candidate>> getAllCandidatesStatus() {
//        List<Candidate> candidates = candidateService.candidatesViewAll();
//        return new ResponseEntity<>(candidates, HttpStatus.OK);
//    }
//
//    @GetMapping("/candidate/{id}")
//    public ResponseEntity<Candidate> getCandidateById(@PathVariable("id") Long id) {
//        Optional<Candidate> candidateOptional = candidateService.getCandidateById(id);
//        if (candidateOptional.isPresent()) {
//            return new ResponseEntity<>(candidateOptional.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping("/candidate")
//    public ResponseEntity<Candidate> createCandidate(@RequestBody CandidatePostDTO candidatePostDTO) {
//        Candidate createdCandidate = candidateService.createCandidate(candidatePostDTO);
//        return new ResponseEntity<>(createdCandidate, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/candidate/{id}")
//    public ResponseEntity<Candidate> updateCandidate(@PathVariable("id") Long id, @RequestBody Candidate candidate) {
//        Candidate updatedCandidate = candidateService.updateCandidate(id, candidate);
//        return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/candidate/{id}")
//    public ResponseEntity<Void> deleteCandidate(@PathVariable("id") Long id) {
//        candidateService.deleteCandidate(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}


package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.dto.CandidatePostDTO;
import tahub.sdapitahub.dto.Candidate.CandidatePutDTO;
import tahub.sdapitahub.entity.Candidate;
import tahub.sdapitahub.service.CandidateService;

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
    public ResponseEntity<Candidate> getCandidateById(@PathVariable("id") Long id) {
        Optional<Candidate> candidateOptional = candidateService.getCandidateById(id);
        if (candidateOptional.isPresent()) {
            return new ResponseEntity<>(candidateOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/candidate")
    public ResponseEntity<Candidate> createCandidate(@RequestBody CandidatePostDTO candidatePostDTO) {
        Candidate createdCandidate = candidateService.createCandidate(candidatePostDTO);
        return new ResponseEntity<>(createdCandidate, HttpStatus.CREATED);
    }

    @PutMapping("/candidate/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable("id") Long id, @RequestBody CandidatePutDTO candidatePutDTO) {
        try {
            Candidate updatedCandidate = candidateService.updateCandidate(id, candidatePutDTO);
            return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/candidate/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable("id") Long id) {
        candidateService.deleteCandidate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
