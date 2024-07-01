package tahub.sdapitahub.dto.Role;

import jakarta.validation.constraints.NotBlank;

public class RoleDTO {

    @NotBlank(message = "role is required")
    private String role;

    // Getter and Setter for the role field
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
