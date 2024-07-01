package tahub.sdapitahub.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@JsonDeserialize(builder = Client.Builder.class)
@Entity
public class Client {
    @Id
    private Long clientId;
    private String clientName;


    @Column(nullable=false)
    private String clientSpocName;
    private String clientSpocContact;
    private String clientLocation;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    private String jobTitle;


    private Client(Builder builder) {
        this.clientId = builder.clientId;
        this.clientName = builder.clientName;
        this.clientSpocName = builder.clientSpocName;
        this.clientSpocContact = builder.clientSpocContact;
        this.clientLocation = builder.clientLocation;
        this.createdAt = builder.createdAt;
        this.lastUpdated = builder.lastUpdated;

        this.jobTitle = builder.jobTitle;

    }

    // Getters and Setters

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSpocName() {
        return clientSpocName;
    }

    public void setClientSpocName(String clientSpocName) {
        this.clientSpocName = clientSpocName;
    }

    public String getClientSpocContact() {
        return clientSpocContact;
    }

    public void setClientSpocContact(String clientSpocContact) {
        this.clientSpocContact = clientSpocContact;
    }

    public String getClientLocation() {
        return clientLocation;
    }

    public void setClientLocation(String clientLocation) {
        this.clientLocation = clientLocation;
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


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }


    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private Long clientId;
        private String clientName;

        @Column(nullable=false)
        private String clientSpocName;
        private String clientSpocContact;
        private String clientLocation;
        private LocalDateTime createdAt;
        private LocalDateTime lastUpdated;

        private String jobTitle;

        public Builder clientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder clientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public Builder clientSpocName(String clientSpocName) {
            this.clientSpocName = clientSpocName;
            return this;
        }

        public Builder clientSpocContact(String clientSpocContact) {
            this.clientSpocContact = clientSpocContact;
            return this;
        }

        public Builder clientLocation(String clientLocation) {
            this.clientLocation = clientLocation;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder lastUpdated(LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }


        public Builder jobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
