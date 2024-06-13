package tahub.sdapitahub.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TaskDTO {


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

    //Getters and Setters






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




}