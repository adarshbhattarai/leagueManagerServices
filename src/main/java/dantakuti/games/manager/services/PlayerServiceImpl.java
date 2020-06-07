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
    public void updatePlayerStat(Game game, Long groupId, GameStat stat) {

        Player homePlayer = game.getHomePlayerId();
        Player awayPlayer = game.getAwayPlayerId();
        GameResult result = game.getGameResult();

        if(game.getGameStat().equals(GameStat.PLAYED)) {
            if (result.getHomeScore() < result.getAwayScore()) {
                    updateStat(awayPlayer,homePlayer,false,stat);
            } else if (result.getHomeScore() > result.getAwayScore()) {
                updateStat(awayPlayer,homePlayer,false,stat);
            } else {
                updateStat(awayPlayer,homePlayer,true,stat);
            }

            playerDao.update(homePlayer);
            playerDao.update(awayPlayer);
            groupServices.updateStat(groupId,homePlayer,awayPlayer,result.getHomeScore(),result.getAwayScore());
        }
    }

    private void updateStat(Player winner, Player loser, boolean isDraw, GameStat stat) {

        if(winner.getPlayerStat()==null){
            winner.setPlayerStat(new PlayerStat());
        }
        if(loser.getPlayerStat()==null){
            loser.setPlayerStat(new PlayerStat());
        }

        winner.getPlayerStat().setGamesPlayed( winner.getPlayerStat().getGamesPlayed()+(stat.equals(GameStat.PLAYED)? 0:1));
        loser.getPlayerStat().setGamesPlayed(loser.getPlayerStat().getGamesPlayed()+(stat.equals(GameStat.PLAYED)? 0:1));

        if(isDraw){
            winner.getPlayerStat().setGamesDrawn(winner.getPlayerStat().getGamesDrawn()+1);
            loser.getPlayerStat().setGamesDrawn(loser.getPlayerStat().getGamesDrawn()+1);
        }else{
            winner.getPlayerStat().setGamesWon(winner.getPlayerStat().getGamesWon()+1);
            loser.getPlayerStat().setGamesLost(winner.getPlayerStat().getGamesLost()+1);
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
