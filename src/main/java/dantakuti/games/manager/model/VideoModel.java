package dantakuti.games.manager.model;

/**
 * @author adarshbhattarai on 2020-05-30
 * @project LeagueManager
 */
public class VideoModel {

    private String imageUrl;
    private String videoUrl;
    private String title;

    public VideoModel(String imageUrl, String videoUrl, String title) {
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
