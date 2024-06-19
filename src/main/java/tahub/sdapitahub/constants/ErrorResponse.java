package tahub.sdapitahub.constants;

public enum ErrorResponse {
    USER_NOT_FOUND("User not found"),
    INVALID_OLD_PASSWORD("Invalid old password"),
    INVALID_TOKEN("Invalid token");

private final String message;

ErrorResponse(String message) {
    this.message = message;
}

public String getMessage() {
    return message;
}
}
