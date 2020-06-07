package dantakuti.games.manager.services;

import dantakuti.games.manager.dao.FormatRepository;
import dantakuti.games.manager.entity.GameFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adarshbhattarai on 2020-05-08
 * @project LeagueManager
 */
@Service
public class FormatServiceImpl implements FormatService{

    @Autowired
    FormatRepository formatRepository;

    @Override
    public List<GameFormat> listAll() {
        List<GameFormat> products = new ArrayList<>();
        formatRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public GameFormat getById(Long id) {
        return formatRepository.findById(id).orElse(null);
    }

    @Override
    public GameFormat saveOrUpdate(GameFormat gameFormat) {
        formatRepository.save(gameFormat);
        return gameFormat;
    }

    @Override
    public void delete(Long id) {
        formatRepository.deleteById(id);
    }
}
