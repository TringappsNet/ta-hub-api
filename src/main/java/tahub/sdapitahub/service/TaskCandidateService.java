package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.entity.Task;
import tahub.sdapitahub.entity.TaskCandidate;
import tahub.sdapitahub.dto.TaskCandidateDTO;
import tahub.sdapitahub.repository.TaskCandidateRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskCandidateService {

    @Autowired
    private TaskCandidateRepository taskCandidateRepository;

    public List<TaskCandidate> getAllTaskCandidates() {
        return taskCandidateRepository.findAll();
    }

    public List<TaskCandidate> getCandidatesByTaskId(Long taskId) {
        return taskCandidateRepository.findCandidatesByTaskId(taskId);
    }

    public Optional<TaskCandidate> getTaskCandidateById(Long id) {
        return taskCandidateRepository.findById(id);
    }

    public TaskCandidate createTaskCandidate(TaskCandidate taskCandidate) {
        taskCandidate.setCreatedAt(LocalDateTime.now());
        taskCandidate.setLastUpdated(LocalDateTime.now());
        return taskCandidateRepository.save(taskCandidate);
    }

    public TaskCandidate updateTaskCandidate(Long id, TaskCandidate taskCandidate) {
        taskCandidate.setTaskId(id);
        taskCandidate.setLastUpdated(LocalDateTime.now());
        return taskCandidateRepository.update(taskCandidate);
    }


    public void deleteTaskCandidate(Long id) {
        taskCandidateRepository.deleteById(id);
    }
}
