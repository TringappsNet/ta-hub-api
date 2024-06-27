package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.constants.RoleMsgs;
import tahub.sdapitahub.constants.TaskCandidateMsgs;
import tahub.sdapitahub.dto.TaskCandidates.TaskCandidateDTO;
import tahub.sdapitahub.entity.Role;
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
    public ResponseEntity<String> createTaskCandidate(@RequestBody TaskCandidateDTO taskCandidatePostDTO) {
            TaskCandidate createdTaskCandidate = taskCandidateService.createTaskCandidate(taskCandidatePostDTO);
            if(createdTaskCandidate!=null){
            return new ResponseEntity<String>(TaskCandidateMsgs.TASK_CANDIDATE_CREATED.getMessage(), HttpStatus.OK);
            }else{
            return new ResponseEntity<String>(TaskCandidateMsgs.ERROR_TASK_CANDIDATE_CREATE.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @PutMapping("/task-candidate/{id}")
    public ResponseEntity<String> updateTaskCandidate(@PathVariable Long id, @RequestBody TaskCandidateDTO taskCandidatePostDTO) {
try {
    TaskCandidate updatedTaskCandidate = taskCandidateService.updateTaskCandidate(id, taskCandidatePostDTO);
    if (updatedTaskCandidate != null) {
        return new ResponseEntity<String>(TaskCandidateMsgs.TASK_CANDIDATE_UPDATED.getMessage(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(TaskCandidateMsgs.TASK_CANDIDATE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}catch (Exception e){
    return new ResponseEntity<String>(TaskCandidateMsgs.ERROR_TASK_CANDIDATE_UPDATE.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

}

    }

    @DeleteMapping("/task-candidate/{id}")
    public ResponseEntity<String> deleteTaskCandidate(@PathVariable Long id) {
        try {
            Optional<TaskCandidate> optionalTaskCandidate = taskCandidateService.getTaskCandidateById(id);
            if (optionalTaskCandidate.isPresent()) {
                taskCandidateService.deleteTaskCandidate(id);
                return new ResponseEntity<String>(TaskCandidateMsgs.TASK_CANDIDATE_DELETED.getMessage(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(TaskCandidateMsgs.TASK_CANDIDATE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<String>(TaskCandidateMsgs.ERROR_TASK_CANDIDATE_DELETE.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
