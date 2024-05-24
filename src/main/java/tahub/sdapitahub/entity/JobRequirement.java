package tahub.sdapitahub.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonDeserialize(builder = JobRequirement.Builder.class)
public class JobRequirement {
    private long jobId;
    private long clientId;
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
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    private JobRequirement(Builder builder) {
        this.jobId = builder.jobId;
        this.clientId = builder.clientId;
        this.requirementStartDate = builder.requirementStartDate;
        this.clientName = builder.clientName;
        this.clientSpocName = builder.clientSpocName;
        this.clientSpocContact = builder.clientSpocContact;
        this.accountManager = builder.accountManager;
        this.accountManagerEmail = builder.accountManagerEmail;
        this.totalNoOfOpenings = builder.totalNoOfOpenings;
        this.salaryBudget = builder.salaryBudget;
        this.modeOfInterviews = builder.modeOfInterviews;
        this.tentativeStartDate = builder.tentativeStartDate;
        this.tentativeDuration = builder.tentativeDuration;
        this.approvedBy = builder.approvedBy;
        this.createdAt = builder.createdAt;
        this.lastUpdated = builder.lastUpdated;
    }

    // Getters and Setters

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
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



    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private long jobId;
        private long clientId;
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
        private LocalDateTime createdAt;
        private LocalDateTime lastUpdated;

        public Builder jobId(long jobId) {
            this.jobId = jobId;
            return this;
        }

        public Builder clientId(long clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder requirementStartDate(LocalDate requirementStartDate) {
            this.requirementStartDate = requirementStartDate;
            return this;
        }

        public Builder clientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public Builder clientSpocName(String clientSpocName) {
            this.clientSpocName = clientSpocName;
            return this;
        }

        public Builder clientSpocContact(String clientSpocContact) {
            this.clientSpocContact = clientSpocContact;
            return this;
        }

        public Builder accountManager(String accountManager) {
            this.accountManager = accountManager;
            return this;
        }

        public Builder accountManagerEmail(String accountManagerEmail) {
            this.accountManagerEmail = accountManagerEmail;
            return this;
        }

        public Builder totalNoOfOpenings(String totalNoOfOpenings) {
            this.totalNoOfOpenings = totalNoOfOpenings;
            return this;
        }

        public Builder salaryBudget(float salaryBudget) {
            this.salaryBudget = salaryBudget;
            return this;
        }

        public Builder modeOfInterviews(String modeOfInterviews) {
            this.modeOfInterviews = modeOfInterviews;
            return this;
        }

        public Builder tentativeStartDate(LocalDate tentativeStartDate) {
            this.tentativeStartDate = tentativeStartDate;
            return this;
        }

        public Builder tentativeDuration(String tentativeDuration) {
            this.tentativeDuration = tentativeDuration;
            return this;
        }

        public Builder approvedBy(String approvedBy) {
            this.approvedBy = approvedBy;
            return this;
        }


        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder lastUpdated(LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public JobRequirement build() {
            return new JobRequirement(this);
        }
    }
}
