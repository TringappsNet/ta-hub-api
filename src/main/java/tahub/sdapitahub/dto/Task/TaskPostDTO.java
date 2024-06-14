package tahub.sdapitahub.dto.Task;

import javax.validation.constraints.NotNull;

public class TaskPostDTO {

    @NotNull
    private long job_id;
    @NotNull
    private String jobTitle;
    @NotNull
    private String roleType;
    @NotNull
    private String modeOfWork;
    @NotNull
    private String workLocation;
    @NotNull
    private int yearsOfExperienceRequired;
    @NotNull
    private String primarySkillSet;
    @NotNull
    private String secondarySkillSet;
    @NotNull
    private String clientBudget;
    @NotNull
    private String assignedBudget;
    @NotNull
    private String primaryAssignee;
    @NotNull
    private String secondaryAssignee;
    @NotNull
    private String taskStatus;
    @NotNull
    private boolean approvalStatus;
    @NotNull
    private boolean backlogs;
    @NotNull
    private String description;
    @NotNull
    private String clientName;

    // Getters and Setters

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

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
