package tahub.sdapitahub.DTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class JobRequirementDTO {
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
    private double salaryBudget;
    @NotNull
    private String modeOfInterviews;
    @NotNull
    private LocalDate tentativeStartDate;
    @NotNull
    private String tentativeDuration;
    @NotNull
    private String approvedBy;
    @NotNull
    private int yearsOfExperienceRequired;
    @NotNull
    private String primarySkillSet;
    @NotNull
    private String secondarySkillSet;

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

    public double getSalaryBudget() {
        return salaryBudget;
    }

    public void setSalaryBudget(double salaryBudget) {
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

    public void setTentativeDuration(String tentativeDuration) {this.tentativeDuration = tentativeDuration;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
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