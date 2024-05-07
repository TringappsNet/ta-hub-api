package tahub.sdapitahub.DTO;

public class RoleDTO {
    private Long roleId;
    private String role;

    // Constructor
    public RoleDTO(Long roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    // Getters and setters
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
