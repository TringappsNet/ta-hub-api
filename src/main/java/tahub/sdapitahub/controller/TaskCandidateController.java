package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.dto.TaskCandidates.TaskCandidateDTO;
import tahub.sdapitahub.entity.TaskCandidate;
import tahub.sdapitahub.entity.TaskCandidateHistory;
import tahub.sdapitahub.service.TaskCandidateHistoryService;
import tahub.sdapitahub.service.TaskCandidateService;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "TaskCandidate", description = "Operations related to Task Candidates")
@RequestMapping("/api/task-candidates")
public class TaskCandidateController {

    @Autowired
    private TaskCandidateService taskCandidateService;

    @Autowired
    private TaskCandidateHistoryService taskCandidateHistoryService;

    @GetMapping("/")
    public ResponseEntity<List<TaskCandidate>> getAllTaskCandidates() {
        List<TaskCandidate> taskCandidates = taskCandidateService.getAllTaskCandidates();
        return ResponseEntity.ok(taskCandidates);
    }

    @GetMapping("/task-candidate/{id}")
    public ResponseEntity<TaskCandidate> getTaskCandidateById(@PathVariable Long id) {
        Optional<TaskCandidate> taskCandidate = taskCandidateService.getTaskCandidateById(id);
        return taskCandidate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/task/{taskId}/candidates")
    public ResponseEntity<List<TaskCandidate>> getCandidatesByTaskId(@PathVariable Long taskId) {
        List<TaskCandidate> candidates = taskCandidateService.getCandidatesByTaskId(taskId);
        if (candidates.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(candidates);
        }
    }

    @GetMapping("/task/{taskId}/{candidateId}/comments")
    public ResponseEntity<List<TaskCandidateHistory>> getCommentsByTaskIdAndCandidateId(
            @PathVariable Long taskId,
            @PathVariable Long candidateId) {
        List<TaskCandidateHistory> comments = taskCandidateHistoryService.getCandidateComments(taskId, candidateId);
        if (comments.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(comments);
        }
    }

    @PostMapping("/task-candidate")
    public ResponseEntity<TaskCandidate> createTaskCandidate(@RequestBody TaskCandidateDTO taskCandidatePostDTO) {
        TaskCandidate createdTaskCandidate = taskCandidateService.createTaskCandidate(taskCandidatePostDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskCandidate);
    }

    @PutMapping("/task-candidate/{id}")
    public ResponseEntity<TaskCandidate> updateTaskCandidate(@PathVariable Long id, @RequestBody TaskCandidateDTO taskCandidatePostDTO) {
        TaskCandidate updatedTaskCandidate = taskCandidateService.updateTaskCandidate(id, taskCandidatePostDTO);
        if (updatedTaskCandidate != null) {
            return ResponseEntity.ok(updatedTaskCandidate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/task-candidate/{id}")
    public ResponseEntity<Void> deleteTaskCandidate(@PathVariable Long id) {
        taskCandidateService.deleteTaskCandidate(id);
        return ResponseEntity.noContent().build();
    }
}
