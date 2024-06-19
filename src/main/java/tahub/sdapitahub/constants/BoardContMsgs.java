package tahub.sdapitahub.constants;

public enum BoardContMsgs {

    BOARD_CREATED("Board created successfully"),
    BOARD_UPDATED("Board updated successfully"),
    BOARD_NOT_FOUND("Board not found."),
    BOARD_DELETED("Board deleted"),
    BOARD_NOT_DELETED("Board not deleted"),
    ERROR_UPDATE("Failed to update board."),
    ERROR_CREATE("Failed to create board.");






    private final String message;

    BoardContMsgs(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
