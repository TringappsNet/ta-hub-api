package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.entity.Task;
import tahub.sdapitahub.repository.TaskRepository;
import tahub.sdapitahub.dto.TaskDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> createTasks(List<TaskDTO> taskDTOs, int noOfOpenings) {
        List<Task> tasks = new ArrayList<>();
        for (TaskDTO taskDTO : taskDTOs) {
            for (int i = 0; i < noOfOpenings; i++) {
                Task task = new Task();
                task.setJobTitle(taskDTO.getJobTitle());
                task.setRoleType(taskDTO.getRoleType());
                task.setModeOfWork(taskDTO.getModeOfWork());
                task.setWorkLocation(taskDTO.getWorkLocation());
                task.setCreatedAt(LocalDateTime.now());
                tasks.add(taskRepository.save(task));
            }

        }
        return tasks;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setJobTitle(taskDTO.getJobTitle());
        task.setRoleType(taskDTO.getRoleType());
        task.setModeOfWork(taskDTO.getModeOfWork());
        task.setWorkLocation(taskDTO.getWorkLocation());
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, TaskDTO taskDTO) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setJobTitle(taskDTO.getJobTitle());
            task.setRoleType(taskDTO.getRoleType());
            task.setModeOfWork(taskDTO.getModeOfWork());
            task.setWorkLocation(taskDTO.getWorkLocation());
            return taskRepository.save(task);
        } else {
            return null;
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
