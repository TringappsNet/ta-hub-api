package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.entity.TaskCandidateHistory;
import tahub.sdapitahub.repository.TaskCandidateHistoryRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TaskCandidateHistoryService {

    @Autowired
    private TaskCandidateHistoryRepository taskCandidateHistoryRepository;

    public List<TaskCandidateHistory> getAllTaskCandidates() {
        return taskCandidateHistoryRepository.findAll();
    }

    public List<TaskCandidateHistory> getCandidateComments(Long taskId, Long candidateId) {
        return taskCandidateHistoryRepository.findCommentsByTaskAndCandidate(taskId, candidateId);
    }

    public Optional<TaskCandidateHistory> getTaskCandidateById(Long id) {
        return taskCandidateHistoryRepository.findById(id);
    }

    public TaskCandidateHistory createTaskCandidateHistory(TaskCandidateHistory taskCandidateHistory) {
        taskCandidateHistory.setCreatedAt(LocalDateTime.now());
        taskCandidateHistory.setLastUpdated(LocalDateTime.now());
        return taskCandidateHistoryRepository.save(taskCandidateHistory);
    }


    public TaskCandidateHistory updateTaskCandidate(Long id, TaskCandidateHistory taskCandidateHistory) {
        taskCandidateHistory.setStatusId(id);
        taskCandidateHistory.setLastUpdated(LocalDateTime.now());
        return taskCandidateHistoryRepository.update(taskCandidateHistory);
    }

    public void deleteTaskCandidateHistory(Long id) {
        taskCandidateHistoryRepository.deleteById(id);
    }



}
