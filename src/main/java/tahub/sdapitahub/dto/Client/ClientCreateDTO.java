package tahub.sdapitahub.dto.Client;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;



public class ClientCreateDTO {

    @NotBlank
    private String clientName;
    @NotBlank
    private String clientSpocName;
    @NotBlank
    private String clientSpocContact;
    @NotBlank
    private String clientLocation;


    @NotBlank
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
