package dantakuti.games.manager.services;

import dantakuti.games.manager.dao.GroupRepository;
import dantakuti.games.manager.dao.StatisticsRepository;
import dantakuti.games.manager.entity.*;
import dantakuti.games.manager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author adarshbhattarai on 2020-05-26
 * @project LeagueManager
 */
@Service
public class GroupServicesImpl implements GroupServices {

    @Autowired
    PlayerService playerService;

    @Autowired
    GameService gameService;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    StatisticsRepository statisticsRepository;

    @Override
    public LeagueGroups createGroup(GroupModel groupModel, String groupName, League league) {
        LeagueGroups group;
        Set<Player> groupPlayers = playerService.getPlayers(groupModel.getPlayers());
        Set<Player> groupAdmins = new HashSet<>();
        groupAdmins.addAll(groupPlayers);

        group=new LeagueGroups(
                groupName,
                groupPlayers,
                groupAdmins,
                league
        );
        Set<Game> games = gameService.createGame(groupPlayers,group);
        group.setGame(games);

        return group;
    }

    @Override
    public List<GroupTableModel> getStandings(UUID id, Long groupId) {

        Optional<LeagueGroups> groups = groupRepository.findById(groupId);
        if(!groups.isPresent()){
            return null;
        }

        Set<Statistics> statistics = groups.get().getGroupStat();

        List<GroupTableModel> models = new ArrayList<>();
        statistics.stream().forEach(
                stat-> {
                    int totalPoint=stat.getGamesWon() * 3 + stat.getGamesDrawn();
                    models.add(
                            new GroupTableModel(stat.getPlayer().getFullname(),
                                    stat.getGamesPlayed(),
                                    stat.getGamesWon(),
                                    stat.getGamesDrawn(),
                                    stat.getGamesLost(),
                                    stat.getGoalsFor(),
                                    stat.getGoalAgainst(),
                                    stat.getGoalsFor() - stat.getGoalAgainst(),
                                    totalPoint,
                                    stat.getGamesPlayed()==0?3:Math.round(totalPoint/(stat.getGamesPlayed()*3) * 300.0) / 100.0)
                    );
                }
        );

        Collections.sort(models,(a,b)->
                a.getPoints()==b.getPoints()?
                b.getGoalsFor()==a.getGoalsFor() ? a.getGoalAgainst()-b.getGoalAgainst() : b.getGoalsFor()-a.getGoalsFor() :
                b.getPoints()-a.getPoints()
        );

        return models;
    }

    @Override
    public List<GameModel> getGames(UUID id, Long groupId) {
        List<Game> games = gameService.fetchGamesByGroupId(groupId,-1,-1);
        List<GameModel> gameModels = new ArrayList<>();
        games.stream().forEach(
                game->{
                    List<VideoModel> videoLinks = new ArrayList<>();
                    List<ImageModel> images = new ArrayList<>();
                    for(String links : game.getLinks()){
                        videoLinks.add(
                                new VideoModel(
                                        links,
                                        links,
                                        game.getDescription()
                                )
                        );
                    }
                    for(String links : game.getImages()){
                        images.add(
                                new ImageModel(
                                        links,
                                        game.getDescription()
                                )
                        );
                    }
                    GameModel model;
                    if(game.getGameStat() == GameStat.PLAYED || game.getGameStat() == GameStat.LIVE ) {
                         model = new GameModel(
                                game.getHomePlayerId().getFullname(),
                                game.getAwayPlayerId().getFullname(),
                                game.getGameResult().getHomeScore(),
                                game.getGameResult().getAwayScore(),
                                game.getHomePlayerId().getPlayerID(),
                                game.getAwayPlayerId().getPlayerID(),
                                game.getGameId(),
                                videoLinks,
                                images,
                                 game.getGameStat()
                        );

                    }else{
                        model= new GameModel(
                                game.getHomePlayerId().getFullname(),
                                game.getAwayPlayerId().getFullname(),
                                game.getHomePlayerId().getPlayerID(),
                                game.getAwayPlayerId().getPlayerID(),
                                game.getGameId(),
                                game.getGameStat()
                        );
                    }

                    gameModels.add(model);

                }
        );
        return gameModels;
    }

    @Override
    public void updateStat(Long groupId, Game game, GameResult previousResult) {

        Optional<LeagueGroups> leagueGroups = groupRepository.findById(groupId);
        if(leagueGroups.isPresent()){
            LeagueGroups groups = leagueGroups.get();
            Player home =  game.getHomePlayerId();
            Player away = game.getAwayPlayerId();
            Statistics homePlayerStat = statisticsRepository.findByGroup_idAndPlayer(groups,home);
            Statistics awayPlayerStat = statisticsRepository.findByGroup_idAndPlayer(groups,away);
            if(previousResult!=null){
                    homePlayerStat.removePreviousStat(previousResult,true);
                    awayPlayerStat.removePreviousStat(previousResult,false);

            }
            homePlayerStat.addNewStat(game.getGameResult(),true);
            awayPlayerStat.addNewStat(game.getGameResult(),false);

            int homePlayerHighestScore = gameService.getHighestScore(
                    home,groupId);
            int awayPlayerHighestScore = gameService.getHighestScore(
                    away,groupId);

            homePlayerStat.setHighestScore(Math.max(homePlayerHighestScore,game.getGameResult().getHomeScore()));
            awayPlayerStat.setHighestScore(Math.max(awayPlayerHighestScore, game.getGameResult().getAwayScore()));

            statisticsRepository.save(homePlayerStat);
            statisticsRepository.save(awayPlayerStat);
        }
    }
}
