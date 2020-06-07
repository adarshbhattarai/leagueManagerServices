package dantakuti.games.manager.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author adarshbhattarai on 2020-06-05
 * @project LeagueManager
 */
public class GameUpload {

    private int homePlayerScore;
    private int awayPlayerScore;
    private int groupId;
    private String[] videoLinks;
    private MultipartFile[] images;
    private Long gameId;

    public GameUpload() {
    }

    public int getHomePlayerScore() {
        return homePlayerScore;
    }

    public void setHomePlayerScore(int homePlayerScore) {
        this.homePlayerScore = homePlayerScore;
    }

    public int getAwayPlayerScore() {
        return awayPlayerScore;
    }

    public void setAwayPlayerScore(int awayPlayerScore) {
        this.awayPlayerScore = awayPlayerScore;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String[] getVideoLinks() {
        return videoLinks;
    }

    public void setVideoLinks(String[] videoLinks) {
        this.videoLinks = videoLinks;
    }

    public MultipartFile[] getImages() {
        return images;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
