package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.dto.Task.TaskCreateDTO;
import tahub.sdapitahub.entity.JobRequirement;
import tahub.sdapitahub.entity.Task;
import tahub.sdapitahub.repository.TaskRepository;
import tahub.sdapitahub.dto.Task.TaskDTO;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task>  createTasksForJobRequirement(JobRequirement jobRequirement, List<TaskDTO> taskDTOs, int noOfOpenings) {
        List<Task> tasks = new ArrayList<>();
        for (TaskDTO taskDTO : taskDTOs) {

                Task task = new Task.Builder()
                        .jobId(jobRequirement.getJobId())
                        .jobTitle(taskDTO.getJobTitle())
                        .roleType(taskDTO.getRoleType())
                        .modeOfWork(taskDTO.getModeOfWork())
                        .workLocation(taskDTO.getWorkLocation())
                        .yearsOfExperienceRequired(taskDTO.getYearsOfExperienceRequired())
                        .primarySkillSet(taskDTO.getPrimarySkillSet())
                        .secondarySkillSet(taskDTO.getSecondarySkillSet())
                        .approvalStatus(false)
                        .createdAt(LocalDateTime.now())
                        .lastUpdated(LocalDateTime.now())
                        .build();
                tasks.add(taskRepository.save(task));
            }

        return tasks;
    }


    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> taskViewAll() {
        return taskRepository.taskViewAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }


    public List<Task> getTasksByJobId(Long jobId) {
        return taskRepository.fingTasksByJobId(jobId);
    }


    public Task createTask(TaskCreateDTO taskPostDTO) {
        Task task = new Task.Builder()
                .jobId(taskPostDTO.getJob_id())
                .jobTitle(taskPostDTO.getJobTitle())
                .roleType(taskPostDTO.getRoleType())
                .modeOfWork(taskPostDTO.getModeOfWork())
                .workLocation(taskPostDTO.getWorkLocation())
                .yearsOfExperienceRequired(taskPostDTO.getYearsOfExperienceRequired())
                .primarySkillSet(taskPostDTO.getPrimarySkillSet())
                .secondarySkillSet(taskPostDTO.getSecondarySkillSet())
                .clientBudget(taskPostDTO.getClientBudget())
                .assignedBudget(taskPostDTO.getAssignedBudget())
                .primaryAssignee(taskPostDTO.getPrimaryAssignee())
                .secondaryAssignee(taskPostDTO.getSecondaryAssignee())
                .taskStatus(taskPostDTO.getTaskStatus())
                .approvalStatus(taskPostDTO.isApprovalStatus())
                .backlogs(taskPostDTO.isBacklogs())
                .description(taskPostDTO.getDescription())
                .createdAt(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())

                .clientName(taskPostDTO.getClientName())
                .build();
        return taskRepository.save(task);
    }



    public Task updateTask(Long id, TaskCreateDTO taskPostDTO) {
        Task task = new Task.Builder()
                .taskId(id)

                .jobTitle(taskPostDTO.getJobTitle())
                .roleType(taskPostDTO.getRoleType())
                .modeOfWork(taskPostDTO.getModeOfWork())
                .workLocation(taskPostDTO.getWorkLocation())
                .yearsOfExperienceRequired(taskPostDTO.getYearsOfExperienceRequired())
                .primarySkillSet(taskPostDTO.getPrimarySkillSet())
                .secondarySkillSet(taskPostDTO.getSecondarySkillSet())
                .clientBudget(taskPostDTO.getClientBudget())
                .assignedBudget(taskPostDTO.getAssignedBudget())
                .primaryAssignee(taskPostDTO.getPrimaryAssignee())
                .secondaryAssignee(taskPostDTO.getSecondaryAssignee())
                .taskStatus(taskPostDTO.getTaskStatus())

                .backlogs(taskPostDTO.isBacklogs())
                .description(taskPostDTO.getDescription())
                .createdAt(taskRepository.findById(id).get().getCreatedAt()) // retain original creation date
                .lastUpdated(LocalDateTime.now())
                .clientName(taskPostDTO.getClientName())
                .build();
        return taskRepository.update(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
