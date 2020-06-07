package dantakuti.games.manager.services;

import dantakuti.games.manager.entity.League;
import dantakuti.games.manager.entity.LeagueGroups;
import dantakuti.games.manager.entity.Player;
import dantakuti.games.manager.model.GameModel;
import dantakuti.games.manager.model.GroupModel;
import dantakuti.games.manager.model.GroupTableModel;

import java.util.List;
import java.util.UUID;

/**
 * @author adarshbhattarai on 2020-05-26
 * @project LeagueManager
 */
public interface GroupServices {
    LeagueGroups createGroup(GroupModel groupModel, String groupName, League league);

    List<GroupTableModel> getStandings(UUID id, Long groupId);

    List<GameModel> getGames(UUID id, Long groupId);

    void updateStat(Long groupId, Player awayPlayer, Player homePlayer, int awayScore, int homeScore);
}
