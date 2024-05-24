package tahub.sdapitahub.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDateTime;

@JsonDeserialize(builder = Candidate.Builder.class)
public class Candidate {
    private Long candidateId;
    private String candidateName;
    private String candidateEmail;
    private String candidateContact;
    private String technology;
    private String totalExperience;
    private String currentCtc;
    private String expectedCtc;
    private String noticePeriod;
    private String modeOfWork;
    private String currentLocation;
    private String candidateStatus;
    private String comments;
    private String remarks;
    private String recruiter;
    private String recruitedSource;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdated;


    private String clientName;
    private String taskCandidateStatus;

    private Candidate(Builder builder) {
        this.candidateId = builder.candidateId;
        this.candidateName = builder.candidateName;
        this.candidateEmail = builder.candidateEmail;
        this.candidateContact = builder.candidateContact;
        this.technology = builder.technology;
        this.totalExperience = builder.totalExperience;
        this.currentCtc = builder.currentCtc;
        this.expectedCtc = builder.expectedCtc;
        this.noticePeriod = builder.noticePeriod;
        this.modeOfWork = builder.modeOfWork;
        this.currentLocation = builder.currentLocation;
        this.candidateStatus = builder.candidateStatus;
        this.comments = builder.comments;
        this.remarks = builder.remarks;
        this.recruiter = builder.recruiter;
        this.recruitedSource = builder.recruitedSource;
        this.createdDate = builder.createdDate;
        this.lastUpdated = builder.lastUpdated;

        this.clientName = builder.clientName;
        this.taskCandidateStatus = builder.taskCandidateStatus;
    }

    // Getters for all fields

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getCandidateContact() {
        return candidateContact;
    }

    public void setCandidateContact(String candidateContact) {
        this.candidateContact = candidateContact;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(String totalExperience) {
        this.totalExperience = totalExperience;
    }

    public String getCurrentCtc() {
        return currentCtc;
    }

    public void setCurrentCtc(String currentCtc) {
        this.currentCtc = currentCtc;
    }

    public String getExpectedCtc() {
        return expectedCtc;
    }

    public void setExpectedCtc(String expectedCtc) {
        this.expectedCtc = expectedCtc;
    }

    public String getNoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(String noticePeriod) {
        this.noticePeriod = noticePeriod;
    }

    public String getModeOfWork() {
        return modeOfWork;
    }

    public void setModeOfWork(String modeOfWork) {
        this.modeOfWork = modeOfWork;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getCandidateStatus() {
        return candidateStatus;
    }

    public void setCandidateStatus(String candidateStatus) {
        this.candidateStatus = candidateStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(String recruiter) {
        this.recruiter = recruiter;
    }

    public String getRecruitedSource() {
        return recruitedSource;
    }

    public void setRecruitedSource(String recruitedSource) {
        this.recruitedSource = recruitedSource;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTaskCandidateStatus() {
        return taskCandidateStatus;
    }

    public void setTaskCandidateStatus(String taskCandidateStatus) {
        this.taskCandidateStatus = taskCandidateStatus;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private Long candidateId;
        private String candidateName;
        private String candidateEmail;
        private String candidateContact;
        private String technology;
        private String totalExperience;
        private String currentCtc;
        private String expectedCtc;
        private String noticePeriod;
        private String modeOfWork;
        private String currentLocation;
        private String candidateStatus;
        private String comments;
        private String remarks;
        private String recruiter;
        private String recruitedSource;
        private LocalDateTime createdDate;
        private LocalDateTime lastUpdated;

        private String clientName;
        private String taskCandidateStatus;



        public Builder candidateId(Long candidateId) {
            this.candidateId = candidateId;
            return this;
        }

        public Builder candidateName(String candidateName) {
            this.candidateName = candidateName;
            return this;
        }

        public Builder candidateEmail(String candidateEmail) {
            this.candidateEmail = candidateEmail;
            return this;
        }

        public Builder candidateContact(String candidateContact) {
            this.candidateContact = candidateContact;
            return this;
        }

        public Builder technology(String technology) {
            this.technology = technology;
            return this;
        }

        public Builder totalExperience(String totalExperience) {
            this.totalExperience = totalExperience;
            return this;
        }

        public Builder currentCtc(String currentCtc) {
            this.currentCtc = currentCtc;
            return this;
        }

        public Builder expectedCtc(String expectedCtc) {
            this.expectedCtc = expectedCtc;
            return this;
        }

        public Builder noticePeriod(String noticePeriod) {
            this.noticePeriod = noticePeriod;
            return this;
        }

        public Builder modeOfWork(String modeOfWork) {
            this.modeOfWork = modeOfWork;
            return this;
        }

        public Builder currentLocation(String currentLocation) {
            this.currentLocation = currentLocation;
            return this;
        }

        public Builder candidateStatus(String candidateStatus) {
            this.candidateStatus = candidateStatus;
            return this;
        }

        public Builder comments(String comments) {
            this.comments = comments;
            return this;
        }

        public Builder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        public Builder recruiter(String recruiter) {
            this.recruiter = recruiter;
            return this;
        }

        public Builder recruitedSource(String recruitedSource) {
            this.recruitedSource = recruitedSource;
            return this;
        }

        public Builder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder lastUpdated(LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public Builder clientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public Builder taskCandidateStatus(String taskCandidateStatus) {
            this.taskCandidateStatus = this.taskCandidateStatus;
            return this;
        }

        public Candidate build() {
            return new Candidate(this);
        }
    }
}
