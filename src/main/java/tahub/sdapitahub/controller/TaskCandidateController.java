package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.dto.TaskCandidateDTO;
import tahub.sdapitahub.entity.TaskCandidate;
import tahub.sdapitahub.service.TaskCandidateService;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "TaskCandidate", description = "Operations related to Task Candidates")
@RequestMapping("/api/task-candidates")
public class TaskCandidateController {

    @Autowired
    private TaskCandidateService taskCandidateService;

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

    @PostMapping("/task-candidate")
    public ResponseEntity<TaskCandidate> createTaskCandidate(@RequestBody TaskCandidateDTO taskCandidateDTO) {
        TaskCandidate createdTaskCandidate = taskCandidateService.createTaskCandidate(taskCandidateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskCandidate);
    }

    @PutMapping("/task-candidate/{id}")
    public ResponseEntity<TaskCandidate> updateTaskCandidate(@PathVariable Long id, @RequestBody TaskCandidateDTO taskCandidateDTO) {
        TaskCandidate updatedTaskCandidate = taskCandidateService.updateTaskCandidate(id, taskCandidateDTO);
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
