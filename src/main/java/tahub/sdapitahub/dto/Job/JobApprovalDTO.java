package tahub.sdapitahub.dto.Job;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class JobApprovalDTO {

    @NotNull(message = "Requirement start date is mandatory")
    private LocalDate requirementStartDate;

    @NotBlank(message = "Approved by email is mandatory")
    @Email(message = "Approved by must be a valid email address")
    private String approvedBy;

    @NotBlank(message = "Client name is mandatory")
    private String clientName;


    @Valid
    private List<JobApprovalTaskDTO> position;

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

    public List<JobApprovalTaskDTO> getPosition() {
        return position;
    }

    public void setPosition(List<JobApprovalTaskDTO> position) {
        this.position = position;
    }
}
