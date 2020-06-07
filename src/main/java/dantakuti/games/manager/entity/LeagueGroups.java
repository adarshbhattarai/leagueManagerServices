package dantakuti.games.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @author adarshbhattarai on 2020-05-09
 * @project LeagueManager
 */
@Entity
public class LeagueGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long group_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="league_id")
    @JsonIgnore
    private League league;

    @Column(name = "group_name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_admin",
            joinColumns = @JoinColumn(name="group_id"),
            inverseJoinColumns = @JoinColumn(name="admin_id"))
    private Set<Player> group_admins;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="group_id")
    private Set<Game> game;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_player",
            joinColumns = @JoinColumn(name="group_id"),
            inverseJoinColumns = @JoinColumn(name="player_id"))
    private Set<Player> group_players;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="group_id")
    private Set<Statistics> groupStat;

    private Timestamp started_on;

    private Timestamp ended_on;

    public LeagueGroups() {
    }

    public LeagueGroups(String groupName, Set<Player> groupPlayers, Set<Player> groupAdmins, League league) {
        this.name=groupName;
        this.group_players=groupPlayers;
        this.group_admins=groupAdmins;
        this.league=league;
        this.setGroupStat(new HashSet<>());
        for(Player player : groupPlayers){
            this.getGroupStat().add(new Statistics(player,this,league));
        }
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Player> getGroup_admins() {
        return group_admins;
    }

    public void setGroup_admins(Set<Player> group_admins) {
        this.group_admins = group_admins;
    }

    public Set<Game> getGame() {
        return game;
    }

    public void setGame(Set<Game> game) {
        this.game = game;
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

    public Set<Player> getGroup_players() {
        return group_players;
    }

    public void setGroup_players(Set<Player> group_players) {
        this.group_players = group_players;
    }

    public Set<Statistics> getGroupStat() {
        return groupStat;
    }

    public void setGroupStat(Set<Statistics> groupStat) {
        this.groupStat = groupStat;
    }
}
