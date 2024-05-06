package tahub.sdapitahub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tahub.sdapitahub.Entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
