package tahub.sdapitahub.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDateTime;

@JsonDeserialize(builder = TaskCandidate.Builder.class)
public class TaskCandidate {
    private Long taskCandidatesId;
    private Long taskId;
    private Long candidateId;
    private String taskCandidateStatus;
    private String taskCandidateComments;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    // Private constructor to enforce builder usage
    private TaskCandidate(Builder builder) {
        this.taskCandidatesId = builder.taskCandidatesId;
        this.taskId = builder.taskId;
        this.candidateId = builder.candidateId;
        this.taskCandidateStatus = builder.taskCandidateStatus;
        this.taskCandidateComments = builder.taskCandidateComments;
        this.createdAt = builder.createdAt;
        this.lastUpdated = builder.lastUpdated;
    }

    // Getters and Setters

    public Long getTaskCandidatesId() {
        return taskCandidatesId;
    }

    public void setTaskCandidatesId(Long taskCandidatesId) {
        this.taskCandidatesId = taskCandidatesId;
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

    // Builder class
    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private Long taskCandidatesId;
        private Long taskId;
        private Long candidateId;
        private String taskCandidateStatus;
        private String taskCandidateComments;
        private LocalDateTime createdAt;
        private LocalDateTime lastUpdated;

        public Builder taskCandidatesId(Long taskCandidatesId) {
            this.taskCandidatesId = taskCandidatesId;
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

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder lastUpdated(LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public TaskCandidate build() {
            return new TaskCandidate(this);
        }
    }
}
