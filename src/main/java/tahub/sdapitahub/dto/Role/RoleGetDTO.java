package tahub.sdapitahub.dto.Role;

import javax.validation.constraints.NotNull;

public class RoleGetDTO {
    @NotNull
    private Long roleId;
    @NotNull
    private String role;

    // Constructor
    public RoleGetDTO(Long roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    // Getters and setters
    public Long getRoleId() {
        return roleId;
    }

    public String getRole() {
        return role;
    }

}
