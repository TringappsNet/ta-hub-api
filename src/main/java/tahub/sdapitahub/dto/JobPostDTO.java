package tahub.sdapitahub.dto;

import javax.validation.constraints.NotNull;

public class JobPostDTO {
    @NotNull
    private long taskId;
    @NotNull
    private long candidateId;
    @NotNull
    private String taskCandidateStatus;
    @NotNull
    private String taskCandidateComments;
    @NotNull
    private long modifiedBy;

    // Getters and Setters

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(long candidateId) {
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

    public long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
