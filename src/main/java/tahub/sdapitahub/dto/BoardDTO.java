package tahub.sdapitahub.dto;

import javax.validation.constraints.NotNull;

public class BoardDTO {

    @NotNull
    private String column;

    // Getters and Setters
    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
