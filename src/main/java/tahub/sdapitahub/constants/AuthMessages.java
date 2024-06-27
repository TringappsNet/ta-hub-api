package tahub.sdapitahub.constants;
public enum AuthMessages {
    INVALID_CREDENTIALS("Invalid Credentials"),
    INVALID_INVITE_TOKEN("Invalid invite token"),
    RESET_PASSWORD("Password reset successfully"),
    FORGOT_PASSWORD("Password reset link sent to email successfully"),
    RESET_PASS_SUCCESS("Password reset successfully"),
    LOGOUT("Logged out successfully"),
    SERVER_ERROR("Failed to register"),
    REGISTRATION_SUCCESSFUL("Registration successful!"),
    USER_NOT_FOUND("User not found"),
    INVALID_OLD_PASS("Invalid old password"),
    INVALID_TOKEN("Invalid token");



    private final String message;
    AuthMessages(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}