package tahub.sdapitahub.dto;

import java.time.LocalDate;

public class JobRequirementPostDTO {

    private LocalDate requirementStartDate;
    private String clientName;
    private String clientSpocName;
    private String clientSpocContact;
    private String accountManager;
    private String accountManagerEmail;
    private String totalNoOfOpenings;
    private float salaryBudget;
    private String modeOfInterviews;
    private LocalDate tentativeStartDate;
    private String tentativeDuration;
    private String approvedBy;
    private String approvalToken;
    private boolean approvalStatus;

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

    public LocalDate getTentativeStartDate() {
        return tentativeStartDate;
    }

    public void setTentativeStartDate(LocalDate tentativeStartDate) {
        this.tentativeStartDate = tentativeStartDate;
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

    public String getApprovalToken() {
        return approvalToken;
    }

    public void setApprovalToken(String approvalToken) {
        this.approvalToken = approvalToken;
    }

    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
