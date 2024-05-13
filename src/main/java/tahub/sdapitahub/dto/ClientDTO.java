package tahub.sdapitahub.dto;

import java.time.LocalDateTime;

public class ClientDTO {
    private long clientId;
    private String clientName;
    private String clientSpocName;
    private String clientSpocContact;
    private String clientLocation;
//    private byte[] clientLogo;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;
    // Getters and setters

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientSpocName() {
        return clientSpocName;
    }

    public String getClientSpocContact() {
        return clientSpocContact;
    }

    public String getCientLocation() {
        return clientLocation;
    }

//    public byte[] getClientLogo() {
//        return clientLogo;
//    }
//
//    public void setClientLogo(byte[] clientLogo) {
//        this.clientLogo = clientLogo;
//    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}
