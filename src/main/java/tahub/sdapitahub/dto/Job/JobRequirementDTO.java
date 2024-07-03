package tahub.sdapitahub.dto.Job;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import tahub.sdapitahub.dto.Task.TaskDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class JobRequirementDTO {

    private LocalDate requirementStartDate;

    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotBlank(message = "Client SPOC name is required")
    private String clientSpocName;

    @NotBlank(message = "Client SPOC contact is required")
    private String clientSpocContact;

    @NotBlank(message = "Account manager is must")
    private String accountManager;

    @NotBlank(message = "Account manager email is required")
    private String accountManagerEmail;

    private String totalNoOfOpenings;


    @Valid
    private List<TaskDTO> positions;

    @NotNull(message = "Salary budget is required")
    private float salaryBudget;

    @NotBlank(message = "Mode of interviews is required")
    private String modeOfInterviews;


    @NotBlank(message = "Tentative duration is required")
    private String tentativeDuration;

    @NotBlank(message = "Approved by is required")
    private String approvedBy;

    // Getters and Setters

    public LocalDate getRequirementStartDate() {
        return requirementStartDate;
    }

    public void setRequirementStartDate(LocalDate requirementStartDate) {
        this.requirementStartDate = requirementStartDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSpocName() {
        return clientSpocName;
    }

    public void setClientSpocName(String clientSpocName) {
        this.clientSpocName = clientSpocName;
    }

    public String getClientSpocContact() {
        return clientSpocContact;
    }

    public void setClientSpocContact(String clientSpocContact) {
        this.clientSpocContact = clientSpocContact;
    }

    public String getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(String accountManager) {
        this.accountManager = accountManager;
    }

    public String getAccountManagerEmail() {
        return accountManagerEmail;
    }

    public void setAccountManagerEmail(String accountManagerEmail) {
        this.accountManagerEmail = accountManagerEmail;
    }

    public String getTotalNoOfOpenings() {
        return totalNoOfOpenings;
    }

    public void setTotalNoOfOpenings(String totalNoOfOpenings) {
        this.totalNoOfOpenings = totalNoOfOpenings;
    }

    public List<TaskDTO> getPositions() {
        return positions;
    }

    public void setPositions(List<TaskDTO> positions) {
        this.positions = positions;
    }

    public float getSalaryBudget() {
        return salaryBudget;
    }

    public void setSalaryBudget(float salaryBudget) {
        this.salaryBudget = salaryBudget;
    }

    public String getModeOfInterviews() {
        return modeOfInterviews;
    }

    public void setModeOfInterviews(String modeOfInterviews) {
        this.modeOfInterviews = modeOfInterviews;
    }



    public String getTentativeDuration() {
        return tentativeDuration;
    }

    public void setTentativeDuration(String tentativeDuration) {
        this.tentativeDuration = tentativeDuration;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}