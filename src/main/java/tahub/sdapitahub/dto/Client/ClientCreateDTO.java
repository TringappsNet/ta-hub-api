package tahub.sdapitahub.dto.Client;
import jakarta.validation.constraints.NotBlank;



public class ClientCreateDTO {

    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotBlank(message = "Client name is required")
    private String clientSpocName;

    @NotBlank(message = "Client SPOC Contact is required")
    private String clientSpocContact;

    @NotBlank(message = "Client Location is required")
    private String clientLocation;

    @NotBlank(message = "Job Title is required")
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
