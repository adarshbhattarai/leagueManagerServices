package dantakuti.games.manager.services;

import dantakuti.games.manager.entity.GameFormat;

import java.util.List;

/**
 * @author adarshbhattarai on 2020-05-08
 * @project LeagueManager
 */
public interface FormatService {

    List<GameFormat> listAll();
    GameFormat getById(Long id);
    GameFormat saveOrUpdate(GameFormat gameFormat);
    void delete(Long id);

}
