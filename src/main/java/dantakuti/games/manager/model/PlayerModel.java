package dantakuti.games.manager.model;

/**
 * @author adarshbhattarai on 2020-05-11
 * @project LeagueManager
 */
public class PlayerModel {

    private Long playerId;
    private String psId;
    private String name;


    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
