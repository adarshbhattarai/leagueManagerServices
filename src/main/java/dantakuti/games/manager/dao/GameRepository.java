package dantakuti.games.manager.dao;

import dantakuti.games.manager.entity.Game;
import dantakuti.games.manager.entity.GameStat;

import java.util.List;

/**
 * @author adarshbhattarai on 2020-05-08
 * @project LeagueManager
 */
public interface GameRepository {

    List<Game> fetchGames(int limit,GameStat gameStat);

    List<Game> fetchGamesByGroupId(Long groupId,int limit, int offset) ;

    Game findById(long gameId);

    Game update(Game gameId);

}
