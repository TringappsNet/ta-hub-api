package tahub.sdapitahub.dto.Task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskCreateDTO {


    private long job_id;

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotBlank(message = "Role type is required")
    private String roleType;

    @NotBlank(message = "Mode of work is required")
    private String modeOfWork;

    @NotBlank(message = "Work location is required")
    private String workLocation;

    @NotNull(message = "Years of experience required is required")
    private int yearsOfExperienceRequired;

    @NotBlank(message = "Primary skill set is required")
    private String primarySkillSet;

    @NotBlank(message = "Secondary skill set is required")
    private String secondarySkillSet;

    @NotBlank(message = "Client budget is required")
    private String clientBudget;

    @NotBlank(message = "Assigned budget is required")
    private String assignedBudget;

    @NotBlank(message = "Primary assignee is required")
    private String primaryAssignee;

    @NotBlank(message = "Secondary assignee is required")
    private String secondaryAssignee;

    @NotBlank(message = "Task status is required")
    private String taskStatus;

    @NotNull(message = "approvalStatus is required")
    private boolean approvalStatus;

    @NotNull(message = "backlogs is required")
    private boolean backlogs;

    @NotBlank(message = "Description is required")
    private String description;



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
