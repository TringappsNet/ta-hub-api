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
    private String workLocation;
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

    public void setTask_id(long task_id) {
        this.task_id = task_id;
    }

    public long getJob_id() {
        return job_id;
    }

    public void setJob_id(long job_id) {
        this.job_id = job_id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getNoOfOpenings() {
        return noOfOpenings;
    }

    public void setNoOfOpenings(int noOfOpenings) {
        this.noOfOpenings = noOfOpenings;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getModeOfWork() {
        return modeOfWork;
    }

    public void setModeOfWork(String modeOfWork) {
        this.modeOfWork = modeOfWork;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getClientBudget() {
        return clientBudget;
    }

    public void setClientBudget(String clientBudget) {
        this.clientBudget = clientBudget;
    }

    public String getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(String assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public String getPrimaryAssignee() {
        return primaryAssignee;
    }

    public void setPrimaryAssignee(String primaryAssignee) {
        this.primaryAssignee = primaryAssignee;
    }

    public String getSecondaryAssignee() {
        return secondaryAssignee;
    }

    public void setSecondaryAssignee(String secondaryAssignee) {
        this.secondaryAssignee = secondaryAssignee;
    }

    public int getYearsOfExperienceRequired() {
        return yearsOfExperienceRequired;
    }

    public void setYearsOfExperienceRequired(int yearsOfExperienceRequired) {
        this.yearsOfExperienceRequired = yearsOfExperienceRequired;
    }

    public String getPrimarySkillSet() {
        return primarySkillSet;
    }

    public void setPrimarySkillSet(String primarySkillSet) {
        this.primarySkillSet = primarySkillSet;
    }

    public String getSecondarySkillSet() {
        return secondarySkillSet;
    }

    public void setSecondarySkillSet(String secondarySkillSet) {
        this.secondarySkillSet = secondarySkillSet;
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

    public void setBacklogs(boolean backlogs) {
        this.backlogs = backlogs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
