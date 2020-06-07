package dantakuti.games.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author adarshbhattarai on 2020-05-29
 * @project LeagueManager
 */
@Entity
@JsonIgnoreProperties(  {"handler","hibernateLazyInitializer"} )
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    @JsonIgnore
    private LeagueGroups group_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="league_id")
    @JsonIgnore
    private League league;

    private int gamesWon;
    private int gamesLost;
    private int gamesDrawn;
    private int gamesPlayed;
    private int goalsFor;
    private int goalAgainst;
    private int highestScore;

    public Statistics() {
    }

    public Statistics(Player player, LeagueGroups group_id, League league) {
        this.player = player;
        this.group_id = group_id;
        this.league=league;
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.gamesDrawn = 0;
        this.gamesPlayed = 0;
        this.goalsFor=0;
        this.goalAgainst=0;
        this.highestScore=0;
    }

    public Long getStatId() {
        return statId;
    }

    public void setStatId(Long statId) {
        this.statId = statId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public LeagueGroups getGroup_id() {
        return group_id;
    }

    public void setGroup_id(LeagueGroups group_id) {
        this.group_id = group_id;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public int getGamesDrawn() {
        return gamesDrawn;
    }

    public void setGamesDrawn(int gamesDrawn) {
        this.gamesDrawn = gamesDrawn;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalAgainst() {
        return goalAgainst;
    }

    public void setGoalAgainst(int goalAgainst) {
        this.goalAgainst = goalAgainst;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public void updateStat(int myscore, int opponentScore) {
        this.gamesPlayed=this.gamesPlayed+1;
        this.goalsFor = this.goalsFor+myscore;
        this.goalAgainst = this.goalAgainst+opponentScore;
        this.highestScore = Math.max(highestScore,myscore);
        if(myscore==opponentScore){
            this.gamesDrawn=this.gamesDrawn+1;
        }
        else if(myscore>opponentScore){
            this.gamesWon =  gamesWon+1;
        }else{
        this.gamesLost = this.gamesLost+1;
        }
    }
}
