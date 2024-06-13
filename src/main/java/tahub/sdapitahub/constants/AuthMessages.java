package tahub.sdapitahub.constants;

public enum AuthMessages {
    INVALID_CREDENTIALS("Invalid credentials"),
    INVALID_INVITE_TOKEN("Invalid invite token"),
    RESET_PASSWORD("Password reset successfully"),
    FORGOT_PASSWORD("Password reset link sent to email"),
    RESET_PASS_SUCCESS("Password reset sent successfully"),
    LOGOUT("Logged out successfully"),
    INVALID_REQUEST_BODY("Invalid request body");





    private final String message;

    AuthMessages(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
