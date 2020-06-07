package dantakuti.games.manager.dao;

import dantakuti.games.manager.entity.GameFormat;
import org.springframework.data.repository.CrudRepository;

/**
 * @author adarshbhattarai on 2020-05-08
 * @project LeagueManager
 */
public interface FormatRepository extends CrudRepository<GameFormat,Long> {
}
