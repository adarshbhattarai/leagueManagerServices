package dantakuti.games.manager.services;

import dantakuti.games.manager.awsServices.S3ImageServices;
import dantakuti.games.manager.dao.GameRepository;
import dantakuti.games.manager.entity.*;
import dantakuti.games.manager.helper.FileHelper;
import dantakuti.games.manager.helper.TokenHelper;
import dantakuti.games.manager.model.GameUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author adarshbhattarai on 2020-05-08
 * @project LeagueManager
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    S3ImageServices s3ImageServices;

    @Autowired
    PlayerService playerService;

    @Value("${app.awsServices.game}")
    private String gamePath;

    @Override
    public List<Game> fetchTopGames(int limit, boolean includeLiveGames) {
        List<Game> game=null;
        if(includeLiveGames){
            game=fetchLiveGames(limit);
            if(game==null)
                return null;
            if(game.size()<limit){
                game.addAll(gameRepository.fetchGames(limit-game.size(),GameStat.PLAYED));
            }
        }else {
            game = gameRepository.fetchGames(limit,GameStat.PLAYED);
        }
        return game;
    }

    @Override
    public List<Game> fetchLiveGames(int limit) {
        return gameRepository.fetchGames(limit, GameStat.LIVE);
    }

    @Override
    public Set<Game> createGame(Set<Player> groupPlayers, LeagueGroups group) {
        List<Player> players = groupPlayers.stream().collect(Collectors.toList());
        Set<Game> games = new HashSet<>();
        for(int i=0;i<players.size();i++){
            for(int j=0;j<players.size();j++){
                if(i!=j){
                    games.add(createGame(players.get(i),players.get(j),group));
                }
            }
        }
        return games;
    }

    @Override
    public List<Game> fetchGamesByGroupId(Long groupId, int limit, int offset) {
        return gameRepository.fetchGamesByGroupId(groupId,limit,offset);
    }

    @Override
    public boolean updateGameDetail(GameUpload data) {

        long gameId =  data.getGameId();
        long groupId = data.getGroupId();
        String filename = UUID.randomUUID().toString();
        Game game = gameRepository.findById(gameId);
        GameResult gameResult;
        GameStat currentStat = game.getGameStat();
        GameResult previousResult=null;
        if(currentStat == GameStat.NOT_PLAYED){
            gameResult = new GameResult(data.getHomePlayerScore(),data.getAwayPlayerScore());
            game.setGameStat(GameStat.PLAYED);
            game.setPlayedOn(new Timestamp(System.currentTimeMillis()));
            game.setGameResult(gameResult);
        }else{
            gameResult = game.getGameResult();
            previousResult = new GameResult(gameResult.getHomeScore(),gameResult.getAwayScore());
            gameResult.setAwayScore(data.getAwayPlayerScore());
            gameResult.setHomeScore(data.getHomePlayerScore());
        }

        List<String> links = game.getLinks()==null?new ArrayList<>() : game.getLinks();
        for(String videos : data.getVideoLinks())
            links.add(videos);
        game.setLinks(links);

        List<String> images = game.getImages()==null?new ArrayList<>() : game.getImages();
        if(data.getImages()!=null) {
            for (MultipartFile files : data.getImages()) {
                File file = FileHelper.convertMultiPartFileToFile(files);
                String token = TokenHelper.getUniqueToken();
                String url = s3ImageServices.uploadImage(file, gamePath, token);
                images.add(url);
            }
        }
        game.setImages(images);
        gameRepository.update(game);
        if(currentStat==GameStat.PLAYED || currentStat==GameStat.LIVE) {
            playerService.updatePlayerStat(game, groupId, previousResult);
        }else {
            playerService.addNewGameStat(game, groupId);
        }
        return true;
    }

    @Override
    public int getHighestScore(Player player, Long groupId) {
        return gameRepository.findPlayerHighestScore(player,groupId);
    }

    private Game createGame(Player homePlayer, Player awayPlayer, LeagueGroups group) {
        return new Game(
                homePlayer,
                awayPlayer,
                homePlayer.getFullname()+" vs "+ awayPlayer.getFullname(),
                GameStat.NOT_PLAYED,
                group);
    }
}
