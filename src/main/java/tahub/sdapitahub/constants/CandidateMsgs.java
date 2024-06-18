package tahub.sdapitahub.constants;

public enum CandidateMsgs {
    CANDIDATE_CREATED("Candidate created successfully"),
    CANDIDATE_NOT_CREATED("Failed to create candidate"),
    CANDIDATE_DELETED("Candidate deleted successfully"),
    CANDIDATE_UPDATED("Candidate updated"),
    ID_NOT_MATCHED("ID don't match");



    private final String message;

    CandidateMsgs(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
