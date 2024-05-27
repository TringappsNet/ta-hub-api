package tahub.sdapitahub.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class JobRequirementDTO {
    @NotNull
    private long jobId;
    @NotNull
    private long clientId;
    @NotNull
    private LocalDate requirementStartDate;
    @NotNull
    private String clientName;
    @NotNull
    private String clientSpocName;
    @NotNull
    private String clientSpocContact;
    @NotNull
    private String accountManager;
    @NotNull
    private String accountManagerEmail;
    @NotNull
    private String totalNoOfOpenings;
    @NotNull
    private List<TaskDTO> positions;
    @NotNull
    private float salaryBudget;
    @NotNull
    private String modeOfInterviews;
    @NotNull
    private LocalDate tentativeStartDate;
    @NotNull
    private String tentativeDuration;
    @NotNull
    private String approvedBy;
    @NotNull
    private String approvalToken;
    @NotNull
    private Boolean approvalStatus;
    @NotNull
    private String createdAt;
    @NotNull
    private String lastUpdated;


    //getters and setters

    public LocalDate getRequirementStartDate() {
        return requirementStartDate;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientSpocName() {
        return clientSpocName;
    }

    public String getClientSpocContact() {
        return clientSpocContact;
    }

    public String getAccountManager() {
        return accountManager;
    }

    public String getAccountManagerEmail() {
        return accountManagerEmail;
    }

    public String getTotalNoOfOpenings() {
        return totalNoOfOpenings;
    }

    public List<TaskDTO> getPositions() {
        return positions;
    }

    public float getSalaryBudget() {
        return salaryBudget;
    }

    public String getModeOfInterviews() {
        return modeOfInterviews;
    }

    public LocalDate getTentativeStartDate() {
        return tentativeStartDate;
    }

    public String getTentativeDuration() {
        return tentativeDuration;
    }

    public String getApprovedBy() {
        return approvedBy;
    }


    public long getJobId() {
        return jobId;
    }

    public long getClientId() {
        return clientId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public Boolean getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalToken() {
        return approvalToken;
    }

    public void setApprovalToken(String approvalToken) {
        this.approvalToken = approvalToken;
    }
}