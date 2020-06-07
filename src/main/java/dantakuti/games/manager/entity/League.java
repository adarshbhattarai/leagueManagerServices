package dantakuti.games.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author adarshbhattarai on 2020-05-04
 * @project LeagueManager
 */
@Entity
@JsonIgnoreProperties(  {"handler","hibernateLazyInitializer"} )
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueId;
    private String leagueName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="league",fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.JOIN)
    private List<LeagueGroups> groups;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "league_admin",
    joinColumns = @JoinColumn(name="league_id"),
    inverseJoinColumns = @JoinColumn(name="admin_id"))
    private Set<Player> league_admins;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="created_by")
    @JsonIgnore
    private Player createdBy;

    private Timestamp started_on;

    private Timestamp created_on;

    private Timestamp ended_on;

    private State gameState;

    private UUID token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="format_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private GameFormat format;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="winner_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Player winner;

    public League() {
    }

    public League(String leagueName, List<LeagueGroups> groups, Set<Player> league_admins, Player createdBy, GameFormat format) {
        this.leagueName = leagueName;
        this.groups = groups;
        this.league_admins = league_admins;
        this.createdBy = createdBy;
        this.created_on=new Timestamp(System.currentTimeMillis());
        this.format = format;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public List<LeagueGroups> getGroups() {
        return groups;
    }

    public void setGroups(List<LeagueGroups> groups) {
        this.groups = groups;
    }

    public Set<Player> getLeague_admins() {
        return league_admins;
    }

    public void setLeague_admins(Set<Player> league_admins) {
        this.league_admins = league_admins;
    }

    public Player getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Player createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getStarted_on() {
        return started_on;
    }

    public void setStarted_on(Timestamp started_on) {
        this.started_on = started_on;
    }

    public Timestamp getEnded_on() {
        return ended_on;
    }

    public void setEnded_on(Timestamp ended_on) {
        this.ended_on = ended_on;
    }

    public State getGameState() {
        return gameState;
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
    }

    public GameFormat getFormat() {
        return format;
    }

    public void setFormat(GameFormat format) {
        this.format = format;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Timestamp getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Timestamp created_on) {
        this.created_on = created_on;
    }
}
