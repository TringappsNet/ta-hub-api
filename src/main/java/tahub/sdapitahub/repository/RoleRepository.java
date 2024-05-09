package tahub.sdapitahub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tahub.sdapitahub.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
