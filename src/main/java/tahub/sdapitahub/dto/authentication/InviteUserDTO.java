package tahub.sdapitahub.dto.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class InviteUserDTO {

    @NotNull
    private Long roleId;

    @NotNull
    @Email
    private String email;

    // Getters and Setters
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

