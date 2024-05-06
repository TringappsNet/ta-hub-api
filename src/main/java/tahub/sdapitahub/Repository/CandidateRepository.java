package tahub.sdapitahub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tahub.sdapitahub.Entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    // Define custom query methods if needed
}
