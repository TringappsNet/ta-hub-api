package tahub.sdapitahub.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDateTime;

@JsonDeserialize(builder = TaskCandidateHistory.Builder.class)
public class TaskCandidateHistory {
    private Long statusId;
    private Long taskId;
    private Long candidateId;
    private String taskCandidateStatus;
    private String taskCandidateComments;
    private String taskStatus;
    private Long modifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    // Private constructor to enforce builder usage
    private TaskCandidateHistory(Builder builder) {
        this.statusId = builder.statusId;
        this.taskId = builder.taskId;
        this.candidateId = builder.candidateId;
        this.taskCandidateStatus = builder.taskCandidateStatus;
        this.taskCandidateComments = builder.taskCandidateComments;
        this.taskStatus = builder.taskStatus;
        this.modifiedBy = builder.modifiedBy;
        this.createdAt = builder.createdAt;
        this.lastUpdated = builder.lastUpdated;
    }

    // Getters and Setters

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
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

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
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

    // Static method to get the builder instance
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private Long statusId;
        private Long taskId;
        private Long candidateId;
        private String taskCandidateStatus;
        private String taskCandidateComments;
        private String taskStatus;
        private Long modifiedBy;
        private LocalDateTime createdAt;
        private LocalDateTime lastUpdated;

        public Builder statusId(Long statusId) {
            this.statusId = statusId;
            return this;
        }

        public Builder taskId(Long taskId) {
            this.taskId = taskId;
            return this;
        }

        public Builder candidateId(Long candidateId) {
            this.candidateId = candidateId;
            return this;
        }

        public Builder taskCandidateStatus(String taskCandidateStatus) {
            this.taskCandidateStatus = taskCandidateStatus;
            return this;
        }

        public Builder taskCandidateComments(String taskCandidateComments) {
            this.taskCandidateComments = taskCandidateComments;
            return this;
        }

        public Builder taskStatus(String taskStatus) {
            this.taskStatus = taskStatus;
            return this;
        }

        public Builder modifiedBy(Long modifiedBy) {
            this.modifiedBy = modifiedBy;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder lastUpdated(LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public TaskCandidateHistory build() {
            return new TaskCandidateHistory(this);
        }
    }
}
