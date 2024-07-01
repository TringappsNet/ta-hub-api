package tahub.sdapitahub.dto.Job;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobApprovalTaskDTO {

    @NotBlank(message = "Job title is mandatory")
    private String jobTitle;

    @NotNull(message = "Number of openings is mandatory")
    private Integer noOfOpenings;

    // Getters and Setters
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getNoOfOpenings() {
        return noOfOpenings;
    }

    public void setNoOfOpenings(Integer noOfOpenings) {
        this.noOfOpenings = noOfOpenings;
    }
}
