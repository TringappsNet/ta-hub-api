package tahub.sdapitahub.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Role.Builder.class)
    public class Role {
        private Long roleId;
        private String role;

        private Role(Builder builder) {
            this.roleId = builder.roleId;
            this.role = builder.role;

        }

        // Getters and Setters

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

        @JsonPOJOBuilder(withPrefix = "")
        public static class Builder {
            private Long roleId;
            private String role;


            public Builder roleId(Long roleId) {
                this.roleId = roleId;
                return this;
            }

            public Builder role(String role) {
                this.role = role;
                return this;
            }

            public Role build() {
                return new Role(this);
            }
        }
    }



