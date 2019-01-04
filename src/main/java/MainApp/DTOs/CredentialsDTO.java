package MainApp.DTOs;

public class CredentialsDTO {

    public String username;
    public String password;
    public String host_address;

    public CredentialsDTO(String username, String password, String host_address) {
        this.username = username;
        this.password = password;
        this.host_address = host_address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost_address() {
        return host_address;
    }

    public void setHost_address(String host_address) {
        this.host_address = host_address;
    }
}
