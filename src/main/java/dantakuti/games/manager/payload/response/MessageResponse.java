package dantakuti.games.manager.payload.response;

/**
 * @author adarshbhattarai on 2020-05-24
 * @project LeagueManager
 */
public class MessageResponse {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
