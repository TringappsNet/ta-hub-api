package tahub.sdapitahub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tahub.sdapitahub.Entity.JobRequirement;

public interface JobRequirementRepository extends JpaRepository<JobRequirement, Long> {
}