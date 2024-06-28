package tahub.sdapitahub.dto.Candidate;


import jakarta.validation.constraints.NotBlank;

public class CandidateCreateDTO {

    @NotBlank(message = "Candidate name is required")
    private String candidateName;

    @NotBlank(message = "Candidate email is required")
    private String candidateEmail;

    @NotBlank(message = "Candidate contact is required")
    private String candidateContact;

    @NotBlank(message = "Technology is required")
    private String technology;

    @NotBlank(message = "Total experience is required")
    private String totalExperience;

    @NotBlank(message = "Current CTC is required")
    private String currentCtc;

    @NotBlank(message = "Expected CTC is required")
    private String expectedCtc;

    @NotBlank(message = "Notice period is required")
    private String noticePeriod;

    @NotBlank(message = "Mode of work is required")
    private String modeOfWork;

    @NotBlank(message = "Current location is required")
    private String currentLocation;

    @NotBlank(message = "Candidate status is required")
    private String candidateStatus;

    @NotBlank(message = "Comments are required")
    private String comments;

    @NotBlank(message = "Remarks are required")
    private String remarks;

    @NotBlank(message = "Recruiter is required")
    private String recruiter;

    @NotBlank(message = "Recruited source is required")
    private String recruitedSource;

    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotBlank(message = "Task candidate status is required")
    private String taskCandidateStatus;

    // Getters and Setters

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
}
