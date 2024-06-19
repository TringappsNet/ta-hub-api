package tahub.sdapitahub.constants;

public enum RoleMessages {
    ROLE_CREATED("Role created"),
    ROLE_UPDATED("Role updated"),
    ROLE_DELETED("Role deleted");



    private final String message;

    RoleMessages(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
