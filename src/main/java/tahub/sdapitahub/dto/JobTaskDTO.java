package tahub.sdapitahub.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JobTaskDTO {

    @NotNull
    private LocalDate requirementStartDate;

    @NotNull
    private String jobTitle;
    @NotNull
    private int noOfOpenings;


    //Getters and Setters




    public LocalDate getRequirementStartDate() {
        return requirementStartDate;
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

}