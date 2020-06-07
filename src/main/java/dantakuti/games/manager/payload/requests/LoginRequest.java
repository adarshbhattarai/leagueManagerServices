package dantakuti.games.manager.payload.requests;

import javax.validation.constraints.NotBlank;

/**
 * @author adarshbhattarai on 2020-05-24
 * @project LeagueManager
 */
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

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
}
