package dantakuti.games.manager.services;

import dantakuti.games.manager.entity.League;
import dantakuti.games.manager.entity.Statistics;
import dantakuti.games.manager.model.LeagueModel;
import dantakuti.games.manager.entity.Game;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author adarshbhattarai on 2020-05-10
 * @project LeagueManager
 */
public interface LeagueService {

    public League createLeague(LeagueModel leagueModel, Principal principal);

    League fetchLeague(UUID token);

    List<Game> fetchTopGames(UUID fromString);

    List<Statistics> getHighestScorers(UUID fromString);
}
