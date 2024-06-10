package tahub.sdapitahub.dto;

import javax.validation.constraints.NotNull;

public class ClientCreateDTO {
    @NotNull
    private long clientId;
    @NotNull
    private String clientName;
    @NotNull
    private String jobTitle;

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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
