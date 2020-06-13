package dantakuti.games.manager.dao;

import dantakuti.games.manager.entity.Game;
import dantakuti.games.manager.entity.GameStat;
import dantakuti.games.manager.entity.Player;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author adarshbhattarai on 2020-05-08
 * @project LeagueManager
 */

@Repository
@Transactional
public class GameRepositoryImpl implements GameRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Game> fetchGames(int limit, GameStat gameStat) {

        try {
            Optional<List<Game>> games =
                    Optional.of(entityManager.createQuery("SELECT g from Game g where g.gameStat=:gameStat order by g.playedOn desc")
                            .setParameter("gameStat", gameStat)
                            .setMaxResults(limit)
                            .getResultList());
            return games.orElse(null);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<Game> fetchGamesByGroupId(Long group_id,int limit, int offset) {
        /*entityManager.createQuery(
              "select g from Game g where g.group_id=:groupId"
        ).setFirstResult(offset).setMaxResults(limit).setParameter("groupId",groupId)
                .getResultList();
        */
        List<Game> games = entityManager.createQuery(
                "select g from Game g where g.group_id.group_id=:group_id"
        ).setParameter("group_id",group_id).getResultList();

        return games;
    }

    @Override
    public Game findById(long gameId) {

        return entityManager.find(Game.class,gameId);
    }

    @Override
    public Game update(Game game) {

        entityManager.merge(game);
        return game;
    }

    @Override
    public int findPlayerHighestScore(Player player, Long groupId) {

        int highestHomeScore = findHighestHomeScore(player,groupId);
        int highestAwayScore = findHighestAwayScore(player, groupId);
        return Math.max(highestAwayScore,highestHomeScore);
    }

    private int findHighestAwayScore(Player player, Long groupId) {
        Object result = entityManager.createQuery(
                "select max(gr.awayScore ) from Game g " +
                        "inner join g.gameResult gr " +
                        "where g.group_id.group_id =:groupId and g.awayPlayerId.playerID=:playerId")
                .setParameter("groupId",groupId)
                .setParameter("playerId",player.getPlayerID())
                .getSingleResult();

        return result==null?0:(int)result;
    }

    private int findHighestHomeScore(Player player, Long groupId) {
        Object result = entityManager.createQuery(
                "select max(gr.homeScore ) from Game g " +
                        "inner join g.gameResult gr " +
                        "where g.group_id.group_id =:groupId and g.homePlayerId.playerID=:playerId")
                .setParameter("groupId",groupId)
                .setParameter("playerId",player.getPlayerID())
                .getSingleResult();

        return result==null?0:(int)result;
    }
}
