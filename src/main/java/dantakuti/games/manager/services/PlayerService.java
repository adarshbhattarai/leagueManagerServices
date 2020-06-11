package dantakuti.games.manager.services;

import dantakuti.games.manager.entity.Game;
import dantakuti.games.manager.entity.GameResult;
import dantakuti.games.manager.entity.Player;
import dantakuti.games.manager.model.PlayerModel;

import java.util.Set;

/**
 * @author adarshbhattarai on 2020-05-26
 * @project LeagueManager
 */
public interface PlayerService {

    Player getPlayer(PlayerModel playerModel);

    Set<Player> getPlayers(PlayerModel[] players);

    void updatePlayerStat(Game game, Long groupId, GameResult previousResult);

    void addNewGameStat(Game game, long groupId);
}
