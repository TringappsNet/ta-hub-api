package tahub.sdapitahub.dto.authentication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ForgotPasswordDTO {



    @NotBlank
    @Email
    private String email;

    // Getters and Setters


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
