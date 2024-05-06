package tahub.sdapitahub.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.Entity.Candidate;
import tahub.sdapitahub.Repository.CandidateRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    public Candidate createCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(Long id, Candidate candidate) {
        candidate.setCandidateId(id);
        return candidateRepository.save(candidate);
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }
}
