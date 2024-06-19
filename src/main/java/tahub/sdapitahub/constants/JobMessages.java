package tahub.sdapitahub.constants;

public enum JobMessages {
    JOB_APPROVAL_SENT("Job approval request sent!"),
    JOB_APPROVAL_SUCCESS("Job requirement approved successfully!"),
    JOB_ERROR("Error occurred while approving job requirement."),
    JOB_REQ_DELETED("Job deleted"),
    JOB_UPDATED("Job updated"),
    JOB_NOT_UPDATED("Job not updated");



    private final String message;

    JobMessages(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
