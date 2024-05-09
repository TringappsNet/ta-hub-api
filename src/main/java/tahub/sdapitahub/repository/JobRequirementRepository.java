package tahub.sdapitahub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tahub.sdapitahub.entity.JobRequirement;

public interface JobRequirementRepository extends JpaRepository<JobRequirement, Long> {
}