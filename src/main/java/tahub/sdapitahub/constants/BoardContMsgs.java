package tahub.sdapitahub.constants;

public enum BoardContMsgs {
    BOARD_NOT_CREATED("Board not created"),
    BOARD_CREATED("Board created successfully"),
    BOARD_UPDATED("Board updated successfully"),
    BOARD_NOT_UPDATED("Board not updated"),
    BOARD_DELETED("Board deleted"),
    BOARD_NOT_DELETED("Board not deleted");






    private final String message;

    BoardContMsgs(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}


