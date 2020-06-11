package dantakuti.games.manager.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

/**
 * @author adarshbhattarai on 2020-05-04
 * @project LeagueManager
 */
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "playerID")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private long playerID;
    private String fullname;
    private int division;
    private String psId;
    private int stars;
    private int favoriteTeam;
    private String email;
    @JsonIgnore
    private String password;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="stat_id")
    private PlayerStat playerStat;
    private boolean registered;
    private Timestamp account_created;

    @ManyToMany(mappedBy = "league_admins")
    @JsonIgnore
    private Set<League> leagues;

    @ManyToMany(mappedBy = "group_admins")
    @JsonIgnore
    private Set<LeagueGroups> groups;

    @ManyToMany(mappedBy = "group_players")
    @JsonIgnore
    private Set<LeagueGroups> partOfGroups;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "player_roles",
            joinColumns = @JoinColumn(
                    name = "player_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @JsonIgnore
    private Collection<Roles> roles;

    public Player(String firstName, String lastName, String username, String email, String password, boolean registered) {
        if(registered==true){
            this.fullname =  lastName+" "+firstName;
            this.psId=username;

        }else{
            this.fullname=username;
        }
        this.email=email;
        this.password=password;
        this.account_created= new Timestamp(System.currentTimeMillis());
        this.registered=registered;

    }

    public Player() {
    }

    public long getPlayerID() {
        return playerID;
    }

    public void setPlayerID(long playerID) {
        this.playerID = playerID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getFavoriteTeam() {
        return favoriteTeam;
    }

    public void setFavoriteTeam(int favoriteTeam) {
        this.favoriteTeam = favoriteTeam;
    }

    public PlayerStat getPlayerStat() {
        return playerStat;
    }

    public void setPlayerStat(PlayerStat playerStat) {
        this.playerStat = playerStat;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public Timestamp getAccount_created() {
        return account_created;
    }

    public void setAccount_created(Timestamp account_created) {
        this.account_created = account_created;
    }

    public Set<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(Set<League> leagues) {
        this.leagues = leagues;
    }

    public Set<LeagueGroups> getGroups() {
        return groups;
    }

    public void setGroups(Set<LeagueGroups> groups) {
        this.groups = groups;
    }

    public Set<LeagueGroups> getPartOfGroups() {
        return partOfGroups;
    }

    public void setPartOfGroups(Set<LeagueGroups> partOfGroups) {
        this.partOfGroups = partOfGroups;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Roles> roles) {
        this.roles = roles;
    }

    public void addWin() {
        if(this.getPlayerStat() == null){
            this.setPlayerStat(new PlayerStat());
        }
        this.getPlayerStat().setGamesWon(this.getPlayerStat().getGamesWon()+1);
        this.getPlayerStat().setGamesPlayed(this.getPlayerStat().getGamesPlayed()+1);
    }

    public void addLoss() {
        if(this.getPlayerStat() == null){
            this.setPlayerStat(new PlayerStat());
        }
        this.getPlayerStat().setGamesLost(this.getPlayerStat().getGamesLost()+1);
        this.getPlayerStat().setGamesPlayed(this.getPlayerStat().getGamesPlayed()+1);
    }

    public void addDraw() {
        if(this.getPlayerStat() == null){
            this.setPlayerStat(new PlayerStat());
        }
        this.getPlayerStat().setGamesDrawn(this.getPlayerStat().getGamesDrawn()+1);
        this.getPlayerStat().setGamesPlayed(this.getPlayerStat().getGamesPlayed()+1);
    }

    public void removeDraw() {
        this.getPlayerStat().setGamesDrawn(this.getPlayerStat().getGamesDrawn()-1);
        this.getPlayerStat().setGamesPlayed(this.getPlayerStat().getGamesPlayed()-1);
    }

    public void removeWin() {
        this.getPlayerStat().setGamesWon(this.getPlayerStat().getGamesWon()-1);
        this.getPlayerStat().setGamesPlayed(this.getPlayerStat().getGamesPlayed()-1);
    }

    public void removeLoss() {
        this.getPlayerStat().setGamesLost(this.getPlayerStat().getGamesLost()-1);
        this.getPlayerStat().setGamesPlayed(this.getPlayerStat().getGamesPlayed()-1);
    }
}
