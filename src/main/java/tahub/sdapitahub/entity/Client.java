package tahub.sdapitahub.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "client_details")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientId")
    private Long clientId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_spoc_name")
    private String clientSpocName;

    @Column(name = "client_spoc_contact")
    private String clientSpocContact;

    @Column(name = "client_location")
    private String clientLocation;

//    @Lob
//    @Column(name = "client_logo")
//    private byte[] clientLogo;

    @Column(name = "created_at")
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


    public String getClientLocation() {
        return clientLocation;
    }

    public void setClientLocation(String clientLocation) {
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
