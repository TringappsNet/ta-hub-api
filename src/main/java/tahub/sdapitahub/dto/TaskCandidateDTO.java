package tahub.sdapitahub.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TaskCandidateDTO {

    @NotNull
    private Long taskCandidatesId;
    @NotNull
    private Long taskId;
    @NotNull
    private Long candidateId;
    @NotNull
    private String taskCandidateStatus;
    @NotNull
    private String taskCandidateComments;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    private LocalDateTime lastUpdated;

    // Getters and Setters

    public Long getTaskCandidatesId() {
        return taskCandidatesId;
    }

    public void setTaskCandidatesId(Long taskCandidatesId) {
        this.taskCandidatesId = this.taskCandidatesId;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
