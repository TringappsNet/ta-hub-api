package tahub.sdapitahub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tahub.sdapitahub.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
