package dantakuti.games.manager.awsServices;


import java.io.File;

/**
 * @author adarshbhattarai on 2020-05-30
 * @project LeagueManager
 */

public interface S3ImageServices {


    public String uploadImage(File file, String path, String fileName);
}
