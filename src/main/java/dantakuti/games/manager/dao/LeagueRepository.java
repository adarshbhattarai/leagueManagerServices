package dantakuti.games.manager.dao;

import dantakuti.games.manager.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

/**
 * @author adarshbhattarai on 2020-05-26
 * @project LeagueManager
 */
public interface LeagueRepository extends JpaRepository<League,Long> {

    @Query("SELECT l FROM League l WHERE l.token = ?1")
    Optional<League> findByToken(UUID token);
}