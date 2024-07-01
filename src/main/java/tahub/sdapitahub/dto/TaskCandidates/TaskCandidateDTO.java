package tahub.sdapitahub.dto.TaskCandidates;
import jakarta.validation.constraints.NotBlank;


public class TaskCandidateDTO {


    private Long taskId;


    private Long candidateId;

    @NotBlank(message = "Task candidate status is required")
    private String taskCandidateStatus;

    @NotBlank(message = "Task candidate comments are required")
    private String taskCandidateComments;


    private Long modifiedBy;

    // Getters and Setters

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getTaskCandidateStatus() {
        return taskCandidateStatus;
    }

    public void setTaskCandidateStatus(String taskCandidateStatus) {
        this.taskCandidateStatus = taskCandidateStatus;
    }

    public String getTaskCandidateComments() {
        return taskCandidateComments;
    }

    public void setTaskCandidateComments(String taskCandidateComments) {
        this.taskCandidateComments = taskCandidateComments;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
