package dantakuti.games.manager.services;

import dantakuti.games.manager.entity.Game;
import dantakuti.games.manager.entity.LeagueGroups;
import dantakuti.games.manager.entity.Player;
import dantakuti.games.manager.model.GameUpload;

import java.util.List;
import java.util.Set;

/**
 * @author adarshbhattarai on 2020-05-08
 * @project LeagueManager
 */
public interface GameService {


    List fetchTopGames(int limit, boolean includeLiveGames);

    List<Game> fetchLiveGames(int limit);

    Set<Game> createGame(Set<Player> groupPlayers, LeagueGroups group);

    List<Game> fetchGamesByGroupId(Long groupId,int limit, int offset) ;

    boolean updateGameDetail(GameUpload data);

    int getHighestScore(Player home, Long groupId);
}
