package dantakuti.games.manager.dao;

import dantakuti.games.manager.entity.Player;

/**
 * @author adarshbhattarai on 2020-05-23
 * @project LeagueManager
 */
public interface PlayerDao {
    Player findByPlayerName(String psId);
    Player findByEmail(String email);
    boolean existsByPsId(String username);
    boolean existsByEmail(String email);
    Player save(Player player);
    Player findByPlayerId(Long id);

    void update(Player awayPlayer);
}
