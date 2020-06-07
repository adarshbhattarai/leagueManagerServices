package dantakuti.games.manager.controller;

import dantakuti.games.manager.entity.Game;
import dantakuti.games.manager.entity.League;
import dantakuti.games.manager.entity.Statistics;
import dantakuti.games.manager.model.LeagueModel;
import dantakuti.games.manager.payload.response.JwtResponse;
import dantakuti.games.manager.payload.response.MessageResponse;
import dantakuti.games.manager.services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

/**
 * @author adarshbhattarai on 2020-05-10
 * @project LeagueManager
 */
@RestController
@RequestMapping("/league")
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="create",method= RequestMethod.POST)
    public ResponseEntity<?>  createNewLeague(@Valid @RequestBody LeagueModel model, Principal principal){
        League leagueCreated = leagueService.createLeague(model,principal);
        return ResponseEntity.ok(new MessageResponse(leagueCreated.getToken().toString()));
    }

    @RequestMapping(value="/{token}",method= RequestMethod.GET)
    public ResponseEntity<?>  fetchleagueWithToken(@PathVariable("token") String token){
        UUID id = UUID.fromString(token);
        League league = leagueService.fetchLeague(id);
        if(league==null)
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("League Doesn't exist"));

        return ResponseEntity.ok(league);
    }

    @RequestMapping(value="/{token}/top-games",method= RequestMethod.GET)
    public ResponseEntity<?>  fetchTopGames(@PathVariable("token") String token){
//        List<Game> games = leagueService.fetchTopGames(UUID.fromString(token));
        List links = new ArrayList();
        links.add(new String[]{"Brito vs Roshan","https://www.youtube.com/watch?v=EYqoIjo7-KA&feature=youtu.be","https://i.ytimg.com/vi/EYqoIjo7-KA/hqdefault.jpg"});
        links.add(new String[]{"Saugat vs Gaurav","https://www.youtube.com/watch?v=reiyBb0VrXo","https://i.ytimg.com/vi/reiyBb0VrXo/hqdefault.jpg"});
        links.add(new String[]{"Saugat vs Gaurav","https://www.youtube.com/watch?v=t8RnxpQwTxg","https://i.ytimg.com/vi/t8RnxpQwTxg/hqdefault.jpg"});
        links.add(new String[]{"Saugat vs Amrit","https://www.youtube.com/watch?v=y_n1cOeHyzI","https://i.ytimg.com/vi/y_n1cOeHyzI/hqdefault.jpg"});
        //https://www.youtube.com/watch?v=EYqoIjo7-KA&feature=youtu.be

        if(1==1){

        }
        return ResponseEntity.ok(links);
    }

    @RequestMapping(value="/{token}/highest-scorers",method= RequestMethod.GET)
    public ResponseEntity<?>  fetchHighestScorers(@PathVariable("token") String token){

        List<Statistics> statisticsSet = leagueService.getHighestScorers(UUID.fromString(token));
        if(statisticsSet==null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Could not be loaded at the moment"));
        }
        return ResponseEntity.ok(statisticsSet);
    }


}