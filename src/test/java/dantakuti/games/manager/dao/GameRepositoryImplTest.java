package dantakuti.games.manager.dao;

import dantakuti.games.manager.entity.Player;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author adarshbhattarai on 2020-06-12
 * @project LeagueManager
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/application.properties")
class GameRepositoryImplTest {


    @Autowired
    GameRepository gameRepository;

    @Test
    void fetchGames() {
    }

    @Test
    void fetchGamesByGroupId() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void findPlayerHighestScore() {
        Player p = new Player();
        p.setPlayerID(29);
        long group = 13;
        int max = gameRepository.findPlayerHighestScore(p,group);
        assertEquals(4,max);
    }
}