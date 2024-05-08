package tahub.sdapitahub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tahub.sdapitahub.Entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
