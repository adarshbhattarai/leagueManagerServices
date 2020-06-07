package dantakuti.games.manager.model;

import dantakuti.games.manager.entity.GameStat;

import java.util.List;

/**
 * @author adarshbhattarai on 2020-05-30
 * @project LeagueManager
 */
public class GameModel {

    private String homePlayer;
    private String awayPlayer;
    private int homeScore;
    private int awayScore;
    private Long homePlayerId;
    private Long awayPlayerId;
    private Long gameId;
    private List<VideoModel> videos;
    private List<ImageModel> images;
    private GameStat stat;

    public GameModel(String homePlayer, String awayPlayer, int homeScore, int awayScore, Long homePlayerId, Long awayPlayerId, Long gameId, List<VideoModel> videos, List<ImageModel> images, GameStat stat) {
        this.homePlayer = homePlayer;
        this.awayPlayer = awayPlayer;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.homePlayerId = homePlayerId;
        this.awayPlayerId = awayPlayerId;
        this.gameId = gameId;
        this.videos = videos;
        this.images = images;
        this.stat = stat;
    }

    public GameModel(String homePlayer, String awayPlayer, Long homePlayerId, Long awayPlayerId, Long gameId,GameStat stat ) {
        this.homePlayer = homePlayer;
        this.awayPlayer = awayPlayer;
        this.homePlayerId = homePlayerId;
        this.awayPlayerId = awayPlayerId;
        this.gameId = gameId;
        this.stat = stat;
    }

    public String getHomePlayer() {
        return homePlayer;
    }

    public void setHomePlayer(String homePlayer) {
        this.homePlayer = homePlayer;
    }

    public String getAwayPlayer() {
        return awayPlayer;
    }

    public void setAwayPlayer(String awayPlayer) {
        this.awayPlayer = awayPlayer;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public Long getHomePlayerId() {
        return homePlayerId;
    }

    public void setHomePlayerId(Long homePlayerId) {
        this.homePlayerId = homePlayerId;
    }

    public Long getAwayPlayerId() {
        return awayPlayerId;
    }

    public void setAwayPlayerId(Long awayPlayerId) {
        this.awayPlayerId = awayPlayerId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public List<VideoModel> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoModel> videos) {
        this.videos = videos;
    }

    public List<ImageModel> getImages() {
        return images;
    }

    public void setImages(List<ImageModel> images) {
        this.images = images;
    }

    public GameStat getStat() {
        return stat;
    }

    public void setStat(GameStat stat) {
        this.stat = stat;
    }
}
