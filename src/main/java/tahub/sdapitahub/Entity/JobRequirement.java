package tahub.sdapitahub.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_requirements")
public class JobRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private long jobId;

    @Column(name = "requirement_start_date")
    private LocalDate requirementStartDate;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_spoc_name")
    private String clientSpocName;

    @Column(name = "client_spoc_contact")
    private String clientSpocContact;

    @Column(name = "account_manager")
    private String accountManager;

    @Column(name = "account_manager_email")
    private String accountManagerEmail;

    @Column(name = "total_no_of_openings")
    private String totalNoOfOpenings;

    @Column(name = "salary_budget")
    private double salaryBudget;

    @Column(name = "mode_of_interviews")
    private String modeOfInterviews;

    @Column(name = "tentative_start_date")
    private LocalDate tentativeStartDate;

    @Column(name = "tentative_duration")
    private String tentativeDuration;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "years_of_experience_required")
    private int yearsOfExperienceRequired;

    @Column(name = "primary_skill_set")
    private String primarySkillSet;

    @Column(name = "secondary_skill_set")
    private String secondarySkillSet;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Getters and setters

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}