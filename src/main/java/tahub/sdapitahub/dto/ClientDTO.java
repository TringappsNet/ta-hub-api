package tahub.sdapitahub.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ClientDTO {
    @NotNull
    private long clientId;
    @NotNull
    private String clientName;
    @NotNull
    private String clientSpocName;
    @NotNull
    private String clientSpocContact;
    @NotNull
    private String clientLocation;
//    @NotNull
//    private byte[] clientLogo;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
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
