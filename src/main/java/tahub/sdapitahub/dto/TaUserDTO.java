package tahub.sdapitahub.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class TaUserDTO {
    @NotNull
    private long userId;

    private long roleId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotNull(message = "Email is required")
    @Size(max = 50, message = "Email must not exceed 50 characters")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email format")
    private String email;

    @NotNull(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    @Pattern(regexp = "^[0-9 ()+-]*$", message = "Invalid phone number format")
    private String phone;

    private String resetToken;

    private LocalDateTime createdAt;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    @NotNull
    private boolean isActive;

    private String currentSessionId;

    private LocalDateTime lastLoginTime;

    private String oldPassword;

    private String newPassword;

    private LocalDateTime lastUpdated;
    // Getters and setters

    public long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public long getRoleId() {
        return roleId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getResetToken() {
        return resetToken;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public String getCurrentSessionId() {
        return currentSessionId;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}
