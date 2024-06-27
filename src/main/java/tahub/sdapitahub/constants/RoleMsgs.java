package tahub.sdapitahub.constants;

public enum RoleMsgs {
    ROLE_CREATED("Role created successfully"),
    ERROR_CREATE("Failed to create role"),
    ROLE_UPDATED("Role updated successfully"),
    ERROR_UPDATE("Failed to update role"),
    ROLE_NOT_FOUND("Role ID not found"),
    ROLE_DELETED("Role Deleted successfully"),
    ERROR_DELETE("Failed to delete role");







    private final String message;

    RoleMsgs(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
