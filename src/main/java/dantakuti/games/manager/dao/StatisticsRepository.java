package dantakuti.games.manager.dao;

import dantakuti.games.manager.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author adarshbhattarai on 2020-05-30
 * @project LeagueManager
 */
@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {


    List<Statistics> findTop10ByLeagueOrderByGoalsForDesc(League league);

    @Query("select stat from Statistics  stat where stat.group_id=(:groupId) and stat.player=(:player)")
    Statistics findByGroup_idAndPlayer(@Param("groupId")LeagueGroups groupId, @Param("player") Player player);
}
