package dantakuti.games.manager.dao;

import dantakuti.games.manager.entity.LeagueGroups;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author adarshbhattarai on 2020-05-29
 * @project LeagueManager
 */
public interface GroupRepository extends JpaRepository<LeagueGroups,Long> {
}
