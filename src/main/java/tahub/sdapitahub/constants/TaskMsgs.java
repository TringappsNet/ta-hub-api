package tahub.sdapitahub.constants;

public enum TaskMsgs {
    TASK_CREATED("Task created successfully"),
    ERROR_TASK_CREATE("Failed to create user"),
    TASK_UPDATED("Task updated successfully"),
    ERROR_TASK_UPDATE("Failed to update task"),
    TASK_NOT_FOUND("Task not found"),
    TASK_DELETED("Task Deleted successfully"),
    ERROR_USER_DELETE("Failed to delete task");
    private final String message;

    TaskMsgs(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
