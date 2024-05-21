package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.entity.JobRequirement;
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

    public List<Task> createTasksForJobRequirement(JobRequirement jobRequirement, List<TaskDTO> taskDTOs, int noOfOpenings) {
        List<Task> tasks = new ArrayList<>();
        for (TaskDTO taskDTO : taskDTOs) {
            for (int i = 0; i < noOfOpenings; i++) {
                Task task = new Task.Builder()
                        .jobId(jobRequirement.getJobId())
                        .jobTitle(taskDTO.getJobTitle())
                        .roleType(taskDTO.getRoleType())
                        .modeOfWork(taskDTO.getModeOfWork())
                        .workLocation(taskDTO.getWorkLocation())
                        .createdAt(LocalDateTime.now())
                        .lastUpdated(LocalDateTime.now())
                        .build();
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
        Task task = new Task.Builder()
                .jobTitle(taskDTO.getJobTitle())
                .roleType(taskDTO.getRoleType())
                .modeOfWork(taskDTO.getModeOfWork())
                .workLocation(taskDTO.getWorkLocation())
                .createdAt(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .build();
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        task.setTaskId(id);
        task.setLastUpdated(LocalDateTime.now());
        return taskRepository.update(task);
    }
//    public Task updateTask(Long id, TaskDTO taskDTO) {
//        Optional<Task> optionalTask = taskRepository.findById(id);
//        if (optionalTask.isPresent()) {
//            Task task = optionalTask.get();
//            task.setJobTitle(taskDTO.getJobTitle());
//            task.setRoleType(taskDTO.getRoleType());
//            task.setModeOfWork(taskDTO.getModeOfWork());
//            task.setWorkLocation(taskDTO.getWorkLocation());
//            task.setTaskStatus(taskDTO.getTaskStatus());
//            task.setLastUpdated(LocalDateTime.now());
//
//            return taskRepository.update(task);
//        } else {
//            return null;
//        }
//    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
