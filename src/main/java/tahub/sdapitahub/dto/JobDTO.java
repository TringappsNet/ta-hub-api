package tahub.sdapitahub.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class JobDTO {

    @NotNull
    private LocalDate requirementStartDate;
    @NotNull
    private String approvedBy;
    @NotNull
    private String clientName;

    @NotNull
    private List<JobTaskDTO> position;






    //getters and setters

    public LocalDate getRequirementStartDate() {
        return requirementStartDate;
    }


    public String getClientName() {
        return clientName;
    }



    public List<JobTaskDTO> getPosition() {
        return position;
    }



    public String getApprovedBy() {
        return approvedBy;
    }





}