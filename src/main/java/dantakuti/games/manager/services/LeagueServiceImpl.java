package dantakuti.games.manager.services;

import dantakuti.games.manager.dao.LeagueRepository;
import dantakuti.games.manager.dao.PlayerDao;
import dantakuti.games.manager.dao.PlayerDaoImpl;
import dantakuti.games.manager.dao.StatisticsRepository;
import dantakuti.games.manager.entity.*;
import dantakuti.games.manager.model.GroupModel;
import dantakuti.games.manager.model.LeagueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author adarshbhattarai on 2020-05-10
 * @project LeagueManager
 */
@Service
@Transactional
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    PlayerDao playerDao;

    @Autowired
    GroupServices groupServices;

    @Autowired
    FormatService formatService;

    @Autowired
    LeagueRepository leagueRepository;

    @Autowired
    GameService gameService;

    @Autowired
    StatisticsRepository statisticsRepository;

    @Override
    public League createLeague(LeagueModel leagueModel, Principal principal) {

        Player creator = playerDao.findByPlayerName(principal.getName());
        GroupModel[] groups = leagueModel.getGroups();
        List<LeagueGroups> groupsSet = new ArrayList<>();
        //Set league admins
        Set<Player> admins = new HashSet();
        admins.add(creator);
        String leagueName = leagueModel.getLeagueName();
        League league =  new League(
                leagueName,
                groupsSet,
                admins,
                creator,
                formatService.getById(leagueModel.getFormatId())
        );

        int i=0;
        for(GroupModel groupModel :  groups){
            groupsSet.add(groupServices.createGroup(groupModel,(char)(i + 65)+"",league));
            i++;
        }



        league.setToken(UUID.randomUUID());
        league = leagueRepository.save(league);
        return league;
    }

    @Override
    public League fetchLeague(UUID token) {
        return leagueRepository.findByToken(token).orElse(null);
    }

    @Override
    public List<Game> fetchTopGames(UUID fromString) {
        return gameService.fetchTopGames(10,true);
    }

    @Override
    public List<Statistics> getHighestScorers(UUID token) {
        League league =  fetchLeague(token);
        List<Statistics> statistics = statisticsRepository.findTop10ByLeagueOrderByGoalsForDesc(league);
        return statistics;
    }
}
