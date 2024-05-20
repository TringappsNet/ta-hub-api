package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Optional<TaskCandidate> getTaskCandidateById(Long id) {
        return taskCandidateRepository.findById(id);
    }

    public TaskCandidate createTaskCandidate(TaskCandidateDTO taskCandidateDTO) {
        TaskCandidate taskCandidate = new TaskCandidate.Builder()
                .taskId(taskCandidateDTO.getTaskId())
                .candidateId(taskCandidateDTO.getCandidateId())
                .taskCandidateStatus(taskCandidateDTO.getTaskCandidateStatus())
                .taskCandidateComments(taskCandidateDTO.getTaskCandidateComments())
                .createdAt(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .build();
        return taskCandidateRepository.save(taskCandidate);
    }

    public TaskCandidate updateTaskCandidate(Long id, TaskCandidateDTO taskCandidateDTO) {
        Optional<TaskCandidate> optionalTaskCandidate = taskCandidateRepository.findById(id);
        if (optionalTaskCandidate.isPresent()) {
            TaskCandidate taskCandidate = optionalTaskCandidate.get();
            taskCandidate.setTaskCandidateStatus(taskCandidateDTO.getTaskCandidateStatus());
            taskCandidate.setTaskCandidateComments(taskCandidateDTO.getTaskCandidateComments());
            taskCandidate.setLastUpdated(LocalDateTime.now());

            return taskCandidateRepository.update(taskCandidate);
        } else {
            return null;
        }
    }

    public void deleteTaskCandidate(Long id) {
        taskCandidateRepository.deleteById(id);
    }
}
