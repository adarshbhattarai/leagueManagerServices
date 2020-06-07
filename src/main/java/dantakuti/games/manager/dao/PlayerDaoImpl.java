package dantakuti.games.manager.dao;

import dantakuti.games.manager.entity.Player;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * @author adarshbhattarai on 2020-05-23
 * @project LeagueManager
 */
@Repository
@Transactional
public class PlayerDaoImpl implements PlayerDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Player findByPlayerName(String psId) {
        Player player=null;
        String queryString = "SELECT p FROM Player p " +
                "WHERE p.psId=:psId";
        TypedQuery<Player> query = entityManager.createQuery(queryString,Player.class);
        query.setParameter("psId",psId);
        player=query.getSingleResult();
        return player;
    }

    @Override
    public Player findByEmail(String email) {
        Player player=null;
        String queryString = "SELECT p FROM Player p " +
                "WHERE p.email=:email";
        TypedQuery<Player> query = entityManager.createQuery(queryString,Player.class);
        query.setParameter("email",email);
        player=query.getSingleResult();
        return player;
    }

    @Override
    public boolean existsByPsId(String username) {

        try{
            findByPlayerName(username);
            return true;
        }catch (Exception e){
            return  false;
        }
       // return findByPlayerName(username)!=null;
    }

    @Override
    public boolean existsByEmail(String email) {
        try{
            findByEmail(email);
            return true;
        }catch (Exception e){
            return  false;
        }
     //   return findByEmail(email)!=null;
    }

    @Override
    public Player save(Player player) {

        entityManager.persist(player);
        //Performance Impact, Unnecessary roundtrips to DB just to get the Id, Later create a playerUid. Simpler and efficient.
        //entityManager.flush();
        return player;
    }

    @Override
    public Player findByPlayerId(Long id) {
        return entityManager.find(Player.class,id);
    }

    @Override
    public void update(Player player) {
        entityManager.merge(player);
    }
}
