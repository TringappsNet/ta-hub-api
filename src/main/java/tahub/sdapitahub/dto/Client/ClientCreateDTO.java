package tahub.sdapitahub.dto.Client;

import javax.validation.constraints.NotNull;


public class ClientCreateDTO {

    @NotNull
    private String clientName;
    @NotNull
    private String clientSpocName;
    @NotNull
    private String clientSpocContact;
    @NotNull
    private String clientLocation;
    @NotNull

    @NotNull
    private String jobTitle;

    // Getters and setters



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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
