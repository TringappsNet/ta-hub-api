package tahub.sdapitahub.dto;

import java.time.LocalDateTime;

public class UserCreateDTO {
    private Long userId;
    private Long roleId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private String resetToken;
    private String password;
    private boolean isActive;
    private String inviteToken;
    private String gAccessToken;
    private Long gTokenExpiresIn;
    private String gIdToken;
    private LocalDateTime gAccessTokenCreatedAt;
    private String currentSessionId;
    private LocalDateTime lastLoginTime;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getInviteToken() {
        return inviteToken;
    }

    public void setInviteToken(String inviteToken) {
        this.inviteToken = inviteToken;
    }

    public String getgAccessToken() {
        return gAccessToken;
    }

    public void setgAccessToken(String gAccessToken) {
        this.gAccessToken = gAccessToken;
    }

    public Long getgTokenExpiresIn() {
        return gTokenExpiresIn;
    }

    public void setgTokenExpiresIn(Long gTokenExpiresIn) {
        this.gTokenExpiresIn = gTokenExpiresIn;
    }

    public String getgIdToken() {
        return gIdToken;
    }

    public void setgIdToken(String gIdToken) {
        this.gIdToken = gIdToken;
    }

    public LocalDateTime getgAccessTokenCreatedAt() {
        return gAccessTokenCreatedAt;
    }

    public void setgAccessTokenCreatedAt(LocalDateTime gAccessTokenCreatedAt) {
        this.gAccessTokenCreatedAt = gAccessTokenCreatedAt;
    }

    public String getCurrentSessionId() {
        return currentSessionId;
    }

    public void setCurrentSessionId(String currentSessionId) {
        this.currentSessionId = currentSessionId;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
