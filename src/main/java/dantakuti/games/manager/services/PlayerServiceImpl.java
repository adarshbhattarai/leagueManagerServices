package dantakuti.games.manager.services;

import dantakuti.games.manager.dao.PlayerDao;
import dantakuti.games.manager.entity.*;
import dantakuti.games.manager.model.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author adarshbhattarai on 2020-05-26
 * @project LeagueManager
 */
@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    PlayerDao playerDao;

    @Autowired
    GroupServices groupServices;

    @Override
    public Player getPlayer(PlayerModel playerModel) {
        Player player;
        if(playerModel.getPlayerId()==-1){
            player = createUnregisteredPlayer(playerModel);
        }else{
             player = playerDao.findByPlayerId(playerModel.getPlayerId());
             if(player==null)
                 player = createUnregisteredPlayer(playerModel);
        }
        return player;
    }

    @Override
    public Set<Player> getPlayers(PlayerModel[] players) {
        Set<Player> setOfPlayers= new HashSet();
        for(PlayerModel playerModel :  players){
            setOfPlayers.add(getPlayer(playerModel));
        }
        return setOfPlayers;
    }
    @Override
    public void updatePlayerStat(Game game, Long groupId, GameResult previousResult) {

        updateStat(game,groupId,previousResult);
    }

    @Override
    public void addNewGameStat(Game game, long groupId) {

        updateStat(game,groupId,null);
    }

    public void updateStat(Game game, Long groupId, GameResult previousResult){
        Player homePlayer = game.getHomePlayerId();
        Player awayPlayer = game.getAwayPlayerId();
        GameResult result = game.getGameResult();
        if(previousResult!=null) removePlayerStat(game,previousResult);
        addPlayerStat(game,result);
        playerDao.update(homePlayer);
        playerDao.update(awayPlayer);
        groupServices.updateStat(groupId,game,previousResult);
    }

    private void addPlayerStat(Game game, GameResult currentResult) {
        Player homePlayer = game.getHomePlayerId();
        Player awayPlayer = game.getAwayPlayerId();
        if(currentResult.getHomeScore() > currentResult.getAwayScore()){
            homePlayer.addWin();
            awayPlayer.addLoss();
        }else if(currentResult.getHomeScore()<currentResult.getAwayScore()){
            homePlayer.addLoss();
            awayPlayer.addWin();
        }else{
            homePlayer.addDraw();
            awayPlayer.addDraw();
        }
    }

    private void removePlayerStat(Game game, GameResult previousResult) {
        Player homePlayer = game.getHomePlayerId();
        Player awayPlayer = game.getAwayPlayerId();
        if(previousResult.getHomeScore()<previousResult.getAwayScore()){
            homePlayer.removeLoss();
            awayPlayer.removeWin();
        }else if(previousResult.getHomeScore()>previousResult.getAwayScore()){
            homePlayer.removeWin();
            awayPlayer.removeLoss();
        }else{
            homePlayer.removeDraw();
            awayPlayer.removeDraw();
        }

    }

    private Player createUnregisteredPlayer(PlayerModel playerModel) {
        Player player = new Player(null,null,
                playerModel.getName(),
                null,
                null,false);
        playerDao.save(player);
        return player;
    }
}
