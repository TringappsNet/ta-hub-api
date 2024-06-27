package tahub.sdapitahub.constants;

public enum CandidateMsgs {
    CANDIDATE_CREATED("Candidate created successfully"),
    CANDIDATE_UPDATED("Candidate updated successfully"),
    CANDIDATE_NOT_FOUND("Candidate not found"),
    CANDIDATE_DELETED("Candidate deleted successfully"),
    ERROR_UPDATE("Failed to update candidate"),
    ERROR_CREATE("Failed to create candidate");






    private final String message;

    CandidateMsgs(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
