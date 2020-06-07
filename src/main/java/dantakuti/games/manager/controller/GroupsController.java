package dantakuti.games.manager.controller;

import dantakuti.games.manager.entity.Game;
import dantakuti.games.manager.entity.Statistics;
import dantakuti.games.manager.model.GameModel;
import dantakuti.games.manager.model.GroupTableModel;
import dantakuti.games.manager.services.GroupServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author adarshbhattarai on 2020-05-29
 * @project LeagueManager
 */
@RestController
@RequestMapping("/{leagueId}")
public class GroupsController {

    @Autowired
    GroupServices groupServices;

    @RequestMapping(value="/group-table/{groupId}",method= RequestMethod.GET)
    public ResponseEntity<?> fetchleagueWithToken(@PathVariable("leagueId") String token,@PathVariable("groupId") Long groupId){

        UUID id = UUID.fromString(token);
        List<GroupTableModel> table = groupServices.getStandings(id,groupId);
        return ResponseEntity.ok(table);
    }

    @RequestMapping(value="/group-games/{groupId}",method= RequestMethod.GET)
    public ResponseEntity<?> getGroupGames(@PathVariable("leagueId") String token,@PathVariable("groupId") Long groupId){
        UUID id = UUID.fromString(token);
        List<GameModel> games = groupServices.getGames(id,groupId);
        return ResponseEntity.ok(games);
    }
}
