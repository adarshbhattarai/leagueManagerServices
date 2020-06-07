package dantakuti.games.manager.model;

import dantakuti.games.manager.entity.Player;

/**
 * @author adarshbhattarai on 2020-05-11
 * @project LeagueManager
 */
public class GroupModel {

    private String name;
    private PlayerModel[] players;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerModel[] getPlayers() {
        return players;
    }

    public void setPlayers(PlayerModel[] players) {
        this.players = players;
    }
}
