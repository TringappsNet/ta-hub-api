package tahub.sdapitahub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tahub.sdapitahub.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
