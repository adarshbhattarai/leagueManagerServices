package dantakuti.games.manager.controller;

import dantakuti.games.manager.entity.Game;
import dantakuti.games.manager.model.GameUpload;
import dantakuti.games.manager.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author adarshbhattarai on 2020-05-04
 * @project LeagueManager
 */
@RestController
@RequestMapping("/games")
@CrossOrigin(origins="http://localhost:3000")
public class GamesController {

    @Autowired
    GameService gameService;

    @RequestMapping("/top-games")
    public ResponseEntity<List<Game>> fetchTopGames(
            @RequestParam(value = "limit", required = false, defaultValue = "9") int limit,
            @RequestParam(value = "includeLive", required = false, defaultValue = "true") boolean includeLiveGames
    ){

        List<Game> games = gameService.fetchTopGames(limit,includeLiveGames);
        return new ResponseEntity(games,HttpStatus.OK);
    }

    @RequestMapping("/ongoing-games")
    public ResponseEntity<List<Game>> liveGames(
            @RequestParam(value = "limit", required = false) int limit
    ){
        List<Game> games = gameService.fetchLiveGames(limit);
        return new ResponseEntity(games,HttpStatus.OK);
    }



    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Game>> upload(
            GameUpload data
    ){
        boolean gameData= gameService.updateGameDetail(data);
        return new ResponseEntity("asdasdasd",HttpStatus.OK);
    }
}