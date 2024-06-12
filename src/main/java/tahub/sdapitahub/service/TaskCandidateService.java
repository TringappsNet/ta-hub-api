package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.dto.TaskCandidatePostDTO;
import tahub.sdapitahub.entity.Task;
import tahub.sdapitahub.entity.TaskCandidate;
import tahub.sdapitahub.entity.TaskCandidateHistory;
import tahub.sdapitahub.repository.TaskCandidateHistoryRepository;
import tahub.sdapitahub.repository.TaskCandidateRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskCandidateService {

    @Autowired
    private TaskCandidateRepository taskCandidateRepository;

    @Autowired
    private TaskCandidateHistoryRepository taskCandidateHistoryRepository;

    @Autowired
    private TaskCandidateHistoryService taskCandidateHistoryService;

    @Autowired
    private TaskService taskService;

    public List<TaskCandidate> getAllTaskCandidates() {
        return taskCandidateRepository.findAll();
    }

    public List<TaskCandidate> getCandidatesByTaskId(Long taskId) {
        return taskCandidateRepository.findCandidatesByTaskId(taskId);
    }

    public Optional<TaskCandidate> getTaskCandidateById(Long id) {
        return taskCandidateRepository.findById(id);
    }

    public TaskCandidate createTaskCandidate(TaskCandidatePostDTO taskCandidatePostDTO) {
        TaskCandidate taskCandidate = new TaskCandidate.Builder()
                .taskId(taskCandidatePostDTO.getTaskId())
                .candidateId(taskCandidatePostDTO.getCandidateId())
                .taskCandidateStatus(taskCandidatePostDTO.getTaskCandidateStatus())
                .taskCandidateComments(taskCandidatePostDTO.getTaskCandidateComments())
                .modifiedBy(taskCandidatePostDTO.getModifiedBy())
                .createdAt(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .build();

        TaskCandidate savedTaskCandidate = taskCandidateRepository.save(taskCandidate);

        saveTaskCandidateStatusHistory(savedTaskCandidate);

        return savedTaskCandidate;
    }

    public TaskCandidate updateTaskCandidate(Long id, TaskCandidate taskCandidate) {
        taskCandidate.setTaskCandidatesId(id);
        taskCandidate.setLastUpdated(LocalDateTime.now());
        TaskCandidate updatedTaskCandidate = taskCandidateRepository.update(taskCandidate);

        saveTaskCandidateStatusHistory(updatedTaskCandidate);

        return updatedTaskCandidate;
    }

    public void deleteTaskCandidate(Long id) {
        taskCandidateRepository.deleteById(id);
    }

    private void saveTaskCandidateStatusHistory(TaskCandidate taskCandidate) {
        Optional<Task> taskOptional = taskService.getTaskById(taskCandidate.getTaskId());
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            TaskCandidateHistory history = TaskCandidateHistory.builder()
                    .taskId(taskCandidate.getTaskId())
                    .candidateId(taskCandidate.getCandidateId())
                    .taskCandidateStatus(taskCandidate.getTaskCandidateStatus())
                    .taskCandidateComments(taskCandidate.getTaskCandidateComments())
                    .taskStatus(task.getTaskStatus())
                    .modifiedBy(taskCandidate.getModifiedBy())
                    .createdAt(LocalDateTime.now())
                    .lastUpdated(LocalDateTime.now())
                    .build();
            taskCandidateHistoryService.createTaskCandidateHistory(history);
        }
    }
}

