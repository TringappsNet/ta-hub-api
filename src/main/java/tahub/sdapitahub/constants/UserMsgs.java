package tahub.sdapitahub.constants;

public enum UserMsgs {
    USER_CREATED("User created successfully"),
    ERROR_USER_CREATE("Failed to create user"),
    USER_UPDATED("User updated successfully"),
    ERROR_USER_UPDATE("Failed to update user"),
   USER_NOT_FOUND("User ID not found"),
    USER_DELETED("User Deleted successfully"),
    ERROR_USER_DELETE("Failed to delete user");
    private final String message;

    UserMsgs(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
