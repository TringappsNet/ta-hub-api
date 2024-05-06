package tahub.sdapitahub.DTO;

import java.time.LocalDateTime;

public class ClientDTO {
    private long clientId;
    private String clientName;
    private String clientSpocName;
    private String clientSpocContact;
    private String clientLocation;
//    private byte[] clientLogo;
    private LocalDateTime createdAt;

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


    public String getCientLocation() {
        return clientLocation;
    }

    public void setCientLocation(String clientLocation) {
        this.clientLocation = clientLocation;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
