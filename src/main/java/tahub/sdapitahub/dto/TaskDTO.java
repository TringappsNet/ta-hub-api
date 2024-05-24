package tahub.sdapitahub.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TaskDTO {

    @NotNull
    private long task_id;
    @NotNull
    private long job_id;
    @NotNull
    private String jobTitle;
    @NotNull
    private int noOfOpenings;
    @NotNull
    private String roleType;
    @NotNull
    private String modeOfWork;
    @NotNull
    private String clientBudget;
    @NotNull
    private String assignedBudget;
    @NotNull
    private String primaryAssignee;
    @NotNull
    private String secondaryAssignee;
    @NotNull
    private int yearsOfExperienceRequired;
    @NotNull
    private String primarySkillSet;
    @NotNull
    private String secondarySkillSet;
    @NotNull
    private String taskStatus;
    @NotNull
    private boolean backlogs;
    @NotNull
    private String description;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    private LocalDateTime lastUpdated;

    //Getters and Setters

    public long getTask_id() {
        return task_id;
    }

    public long getJob_id() {
        return job_id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public int getNoOfOpenings() {
        return noOfOpenings;
    }

    public String getRoleType() {
        return roleType;
    }

    public String getModeOfWork() {
        return modeOfWork;
    }

    public String getWorkLocation() {
        return description;
    }

    public int getYearsOfExperienceRequired() {
        return yearsOfExperienceRequired;
    }

    public String getPrimarySkillSet() {
        return primarySkillSet;
    }

    public String getSecondarySkillSet() {
        return secondarySkillSet;
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

    public String getPrimaryAssignee() {
        return primaryAssignee;
    }

    public String getSecondaryAssignee() {
        return secondaryAssignee;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public boolean isBacklogs() {
        return backlogs;
    }

    public String getDescription() {
        return description;
    }
}
