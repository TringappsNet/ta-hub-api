package tahub.sdapitahub.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.security.PrivateKey;
import java.time.LocalDateTime;

@JsonDeserialize(builder = TaUser.Builder.class)
public class TaUser {
    private Long userId;
    private long roleId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private String resetToken;
    private String password;
    private boolean isActive;
    private String gAccessToken;
    private String gRefreshToken;
    private String currentSessionId;
    private LocalDateTime lastLoginTime;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    public TaUser(Builder builder) {
        this.userId = builder.userId;
        this.roleId = builder.roleId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.username = builder.username;
        this.email = builder.email;
        this.phone = builder.phone;
        this.resetToken = builder.resetToken;
        this.password = builder.password;
        this.isActive = builder.isActive;
        this.gAccessToken = builder.gAccessToken;
        this.gRefreshToken = builder.gRefreshToken;
        this.currentSessionId = builder.currentSessionId;
        this.lastLoginTime = builder.lastLoginTime;
        this.createdAt = builder.createdAt;
        this.lastUpdated = builder.lastUpdated;

    }

    // Getters and Setters


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
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

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public String getgAccessToken() {
        return gAccessToken;
    }

    public void setgAccessToken(String gAccessToken) {
        this.gAccessToken = gAccessToken;
    }

    public String getgRefreshToken() {
        return gRefreshToken;
    }

    public void setgRefreshToken(String gRefreshToken) {
        this.gRefreshToken = gRefreshToken;
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

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private Long userId;
        private int roleId;
        private String firstName;
        private String lastName;
        private String username;
        private String email;
        private String phone;
        private String resetToken;
        private String password;
        private boolean isActive;
        private String gAccessToken;
        private String gRefreshToken;
        private String currentSessionId;
        private LocalDateTime lastLoginTime;
        private LocalDateTime createdAt;
        private LocalDateTime lastUpdated;

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder roleId(int roleId) {
            this.roleId = roleId;
            return this;
        }


        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder resetToken(String resetToken) {
            this.resetToken = resetToken;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder gAccessToken(String gAccessToken) {
            this.gAccessToken = gAccessToken;
            return this;
        }
        public Builder gRefreshToken(String gRefreshToken) {
            this.gRefreshToken = gRefreshToken;
            return this;
        }

        public Builder currentSessionId(String currentSessionId) {
            this.currentSessionId = currentSessionId;
            return this;
        }

        public Builder lastLoginTime(LocalDateTime lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public Builder lastUpdated(LocalDateTime lastUpdated) {
            this.createdAt = lastUpdated;
            return this;
        }

        public TaUser build() {
            return new TaUser(this);
        }
    }
}
