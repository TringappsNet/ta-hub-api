package tahub.sdapitahub.dto;
import tahub.sdapitahub.constants.AuthMessages;

public class ErrorResponse {
    private String message;

    public ErrorResponse(AuthMessages errorMessage) {
        this.message = errorMessage.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
