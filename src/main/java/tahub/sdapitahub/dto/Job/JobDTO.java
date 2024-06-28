//package tahub.sdapitahub.dto.Job;
//
//import jakarta.validation.constraints.NotBlank;
//import java.time.LocalDate;
//import java.util.List;
//
//public class JobDTO {
//
//    @NotNull
//    private LocalDate requirementStartDate;
//    @NotNull
//    private String approvedBy;
//    @NotNull
//    private String clientName;
//
//    @NotNull
//    private List<JobTaskDTO> position;
//
//
//
//
//
//
//    //getters and setters
//
//    public LocalDate getRequirementStartDate() {
//        return requirementStartDate;
//    }
//
//
//    public String getClientName() {
//        return clientName;
//    }
//
//
//
//    public List<JobTaskDTO> getPosition() {
//        return position;
//    }
//
//
//
//    public String getApprovedBy() {
//        return approvedBy;
//    }
//
//
//
//
//
//}




package tahub.sdapitahub.dto.Job;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class JobDTO {

    @NotNull(message = "Requirement start date is required")
    private LocalDate requirementStartDate;

    @NotBlank(message = "Approved by is required")
    private String approvedBy;

    @NotBlank(message = "Client name is required")
    private String clientName;


    private List<JobTaskDTO> position;

    // Getters and Setters

    public LocalDate getRequirementStartDate() {
        return requirementStartDate;
    }

    public void setRequirementStartDate(LocalDate requirementStartDate) {
        this.requirementStartDate = requirementStartDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<JobTaskDTO> getPosition() {
        return position;
    }

    public void setPosition(List<JobTaskDTO> position) {
        this.position = position;
    }
}
