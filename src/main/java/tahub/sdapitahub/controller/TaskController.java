package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.constants.BoardContMsgs;
import tahub.sdapitahub.constants.TaskMsgs;
import tahub.sdapitahub.dto.Task.TaskCreateDTO;
import tahub.sdapitahub.entity.Task;
import tahub.sdapitahub.service.TaskService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@Tag(name = "Task", description = "Operations related to Task")
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/task/view")
    public ResponseEntity<List<Task>> getTaskViewAll() {
        List<Task> tasks = taskService.taskViewAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/task")
    public ResponseEntity<String> createTask(@RequestBody TaskCreateDTO taskPostDTO) {
            Task createdTask = taskService.createTask(taskPostDTO);
            if (createdTask != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(TaskMsgs.TASK_CREATED.getMessage());
            }
            else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(TaskMsgs.ERROR_TASK_CREATE.getMessage());

            }
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody TaskCreateDTO taskPostDTO) {
        try{
        Task updatedTask = taskService.updateTask(id, taskPostDTO);
        if (updatedTask != null) {
            return new ResponseEntity<>(TaskMsgs.TASK_UPDATED.getMessage(), HttpStatus.OK);
        }
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(TaskMsgs.TASK_NOT_FOUND.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(TaskMsgs.ERROR_TASK_UPDATE.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        if (taskService.getTaskById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TaskMsgs.TASK_NOT_FOUND.getMessage());
        } else {
            taskService.deleteTask(id);
            return ResponseEntity.status(HttpStatus.OK).body(TaskMsgs.TASK_DELETED.getMessage());
        }
    }
}
