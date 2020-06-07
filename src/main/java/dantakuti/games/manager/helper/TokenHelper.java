package dantakuti.games.manager.helper;

import java.util.UUID;

/**
 * @author adarshbhattarai on 2020-06-06
 * @project LeagueManager
 */
public class TokenHelper {
    public static String getUniqueToken() {
        String uuid = UUID.randomUUID().toString();
        long timeStamp = System.currentTimeMillis();
        return uuid+""+timeStamp;
    }
}
