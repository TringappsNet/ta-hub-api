package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.dto.Candidate.CandidateCreateDTO;
import tahub.sdapitahub.dto.Candidate.CandidateUpdateDTO;
import tahub.sdapitahub.entity.Candidate;
import tahub.sdapitahub.repository.CandidateRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public List<Candidate> candidatesViewAll() {
        return candidateRepository.findAll(); // Replace with the actual query method if needed
    }

    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    public Candidate createCandidate(CandidateCreateDTO candidatePostDTO) {
        Candidate candidate = new Candidate.Builder()
                .candidateName(candidatePostDTO.getCandidateName())
                .candidateEmail(candidatePostDTO.getCandidateEmail())
                .candidateContact(candidatePostDTO.getCandidateContact())
                .technology(candidatePostDTO.getTechnology())
                .totalExperience(candidatePostDTO.getTotalExperience())
                .currentCtc(candidatePostDTO.getCurrentCtc())
                .expectedCtc(candidatePostDTO.getExpectedCtc())
                .noticePeriod(candidatePostDTO.getNoticePeriod())
                .modeOfWork(candidatePostDTO.getModeOfWork())
                .currentLocation(candidatePostDTO.getCurrentLocation())
                .candidateStatus(candidatePostDTO.getCandidateStatus())
                .comments(candidatePostDTO.getComments())
                .remarks(candidatePostDTO.getRemarks())
                .recruiter(candidatePostDTO.getRecruiter())
                .recruitedSource(candidatePostDTO.getRecruitedSource())
                .clientName(candidatePostDTO.getClientName())
                .taskCandidateStatus(candidatePostDTO.getTaskCandidateStatus())
                .createdDate(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .build();

        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(Long id, CandidateUpdateDTO candidatePutDTO) {
        Optional<Candidate> existingCandidate = candidateRepository.findById(id);

        if (existingCandidate.isPresent()) {
            Candidate candidate = new Candidate.Builder()
                    .candidateId(id)
                    .candidateName(candidatePutDTO.getCandidateName())
                    .candidateEmail(candidatePutDTO.getCandidateEmail())
                    .candidateContact(candidatePutDTO.getCandidateContact())
                    .technology(candidatePutDTO.getTechnology())
                    .totalExperience(candidatePutDTO.getTotalExperience())
                    .currentCtc(candidatePutDTO.getCurrentCtc())
                    .expectedCtc(candidatePutDTO.getExpectedCtc())
                    .noticePeriod(candidatePutDTO.getNoticePeriod())
                    .modeOfWork(candidatePutDTO.getModeOfWork())
                    .currentLocation(candidatePutDTO.getCurrentLocation())
                    .candidateStatus(candidatePutDTO.getCandidateStatus())
                    .comments(candidatePutDTO.getComments())
                    .remarks(candidatePutDTO.getRemarks())
                    .recruiter(candidatePutDTO.getRecruiter())
                    .recruitedSource(candidatePutDTO.getRecruitedSource())
                    .clientName(candidatePutDTO.getClientName())
                    .taskCandidateStatus(candidatePutDTO.getTaskCandidateStatus())
                    .createdDate(existingCandidate.get().getCreatedDate())
                    .lastUpdated(LocalDateTime.now())
                    .build();

            return candidateRepository.update(candidate);
        } else {
            throw new RuntimeException("Candidate not found with id " + id);
        }
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }
}
