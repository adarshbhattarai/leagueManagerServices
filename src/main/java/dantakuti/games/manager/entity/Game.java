package dantakuti.games.manager.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author adarshbhattarai on 2020-05-04
 * @project LeagueManager
 */
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "gameId")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameId;

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST})
    @JoinColumn(name="home_player_playerid")
    private Player homePlayerId;

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST})
    @JoinColumn(name="away_player_playerid")
    private Player awayPlayerId;

    @ElementCollection(targetClass=String.class)
    private List<String> links;
    @ElementCollection(targetClass=String.class)
    private List<String> images;

    private GameStat gameStat;
    private String description;
    private Timestamp playedOn;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="result_id")
    private GameResult gameResult;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    @JsonIgnore
    private LeagueGroups group_id;

    public Game() {
    }

    public Game(Player homePlayerId, Player awayPlayerId, String description, GameStat stat, LeagueGroups group_id) {
        this.homePlayerId = homePlayerId;
        this.awayPlayerId = awayPlayerId;
        this.description = description;
        this.gameStat=stat;
        this.group_id=group_id;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public Player getHomePlayerId() {
        return homePlayerId;
    }

    public void setHomePlayerId(Player homePlayerId) {
        this.homePlayerId = homePlayerId;
    }

    public Player getAwayPlayerId() {
        return awayPlayerId;
    }

    public void setAwayPlayerId(Player awayPlayerId) {
        this.awayPlayerId = awayPlayerId;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public GameStat getGameStat() {
        return gameStat;
    }

    public void setGameStat(GameStat gameStat) {
        this.gameStat = gameStat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getPlayedOn() {
        return playedOn;
    }

    public void setPlayedOn(Timestamp playedOn) {
        this.playedOn = playedOn;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public LeagueGroups getGroup_id() {
        return group_id;
    }

    public void setGroup_id(LeagueGroups group_id) {
        this.group_id = group_id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
