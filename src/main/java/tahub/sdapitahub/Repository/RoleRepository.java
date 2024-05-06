package tahub.sdapitahub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tahub.sdapitahub.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Add custom queries if needed
}
