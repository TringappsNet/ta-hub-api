package tahub.sdapitahub.constants;

public enum ClientMsgs {
    CLIENT_CREATED("Client created successfully"),
    CLIENT_NOT_CREATED("Client not created"),
    CLIENT_UPDATED("Client updated successfully"),
    CLIENT_DELETED("Client deleted"),
    ID_NOT_MATCHED("ID don't match"),
    CLIENT_NOT_FOUND("ID not found");
    private final String message;

    ClientMsgs(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
