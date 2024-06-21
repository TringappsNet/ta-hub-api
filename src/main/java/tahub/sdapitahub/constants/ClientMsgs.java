package tahub.sdapitahub.constants;

public enum ClientMsgs {
CLIENT_CREATED("client created successfully"),
    ERROR_CREATE("Failed to create client"),
    CLIENT_UPDATED("client updated successfully"),
    ERROR_UPDATE("Failed to update client"),
    CLIENT_NOT_FOUND("Client ID not found"),
    CLIENT_DELETED("Client Deleted successfully"),
    ERROR_DELETE("Failed to delete client");







    private final String message;

    ClientMsgs(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
