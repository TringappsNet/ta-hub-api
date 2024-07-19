package tahub.sdapitahub.constants;

public enum JobreqMsgs {
    JOB_REQ_CREATED("Job requirement created successfully"),
    ERROR_JOB_REQ_CREATE("Error occurred while creating job requirements."),
    JOB_REQ_UPDATED("Job requirement updated successfully"),
    JOB_REQ_NOT_FOUND("Job requirement ID not found"),
    JOB_APPROVAL("Job approval request sent!"),
    JOB_APPROVAL_UNAUTH("Pls enter mail id"),
    APPROVE_REQ("Job requirement approved successfully!"),
    APPROVE_REQ_ERR("Error occurred while approving job requirement."),
    JOB_REQ_DELETE("Job ID Deleted successfully");




    private final String message;

    JobreqMsgs(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
