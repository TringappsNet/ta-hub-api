package tahub.sdapitahub.DTO;

import javax.validation.constraints.NotNull;

public class TaskDTO {
    @NotNull
    private String jobTitle;
    @NotNull
    private String roleType;
    @NotNull
    private String modeOfWork;
    @NotNull
    private String workLocation;

    //Getters and Setters

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

}
