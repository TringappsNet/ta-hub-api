package tahub.sdapitahub.dto.Board;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class BoardDTO {

    @NotNull
    @NotEmpty
    @NotBlank(message = "Column must not be blank")
    private String column;

    // Getters and Setters
    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
