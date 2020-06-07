package dantakuti.games.manager.model;

/**
 * @author adarshbhattarai on 2020-05-30
 * @project LeagueManager
 */
public class ImageModel {

    private String src;
    private String description;

    public ImageModel(String src, String description) {
        this.src = src;
        this.description = description;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
