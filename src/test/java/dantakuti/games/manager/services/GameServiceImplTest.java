package dantakuti.games.manager.services;

import dantakuti.games.manager.dao.GameRepository;
import dantakuti.games.manager.entity.Player;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author adarshbhattarai on 2020-06-12
 * @project LeagueManager
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
class GameServiceImplTest {

    @Autowired
    private GameRepository repository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void fetchTopGames() {
    }

    @Test
    void fetchLiveGames() {
    }

    @Test
    void createGame() {
    }

    @Test
    void fetchGamesByGroupId() {
    }

    @Test
    void updateGameDetail() {

    }

    @Test
    void getHighestScore() {


    }
}