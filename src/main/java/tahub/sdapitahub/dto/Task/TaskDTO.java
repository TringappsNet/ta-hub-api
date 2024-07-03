package tahub.sdapitahub.dto.Task;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Min;

public class TaskDTO {



    @NotBlank(message = "Job title must not be blank")
    private String jobTitle;

    @Min(value = 1, message = "Number of openings must be at least 1")
    private int noOfOpenings;

    @NotBlank(message = "Role type must not be blank")
    private String roleType;

    @NotBlank(message = "Mode of work must not be blank")
    private String modeOfWork;

    @NotBlank(message = "Work location must not be blank")
    private String workLocation;



    @Min(value = 0, message = "Years of experience required must be at least 0")
    private int yearsOfExperienceRequired;

    @NotBlank(message = "Primary skill set must not be blank")
    private String primarySkillSet;

    @NotBlank(message = "Secondary skill set must not be blank")
    private String secondarySkillSet;



    // Getters and Setters...



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