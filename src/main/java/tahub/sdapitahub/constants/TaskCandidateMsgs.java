package tahub.sdapitahub.constants;

public enum TaskCandidateMsgs  {
    TASK_CANDIDATE_CREATED("Task Candidate created successfully"),
   ERROR_TASK_CANDIDATE_CREATE("Failed to create task candidate"),
TASK_CANDIDATE_UPDATED("Task Candidate updated successfully"),
ERROR_TASK_CANDIDATE_UPDATE("Failed to update task candidate"),
TASK_CANDIDATE_NOT_FOUND("Task Candidate ID not found"),
TASK_CANDIDATE_DELETED("Task Candidate Deleted successfully"),
ERROR_TASK_CANDIDATE_DELETE("Failed to delete task candidate");







private final String message;

TaskCandidateMsgs(String message){
    this.message=message;
}
public String getMessage(){
    return message;
}
}
