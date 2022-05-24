package be.pxl.anime;

import be.pxl.anime.domain.AnimeCharacter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class AnimeBackendApplication {

    private static final Logger LOGGER = LogManager.getLogger(AnimeBackendApplication.class);
    public static void main( String[] args )
    {
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("Starting app on " + System.getProperty("os.name") + "...");
        }
        AnimeCharacter animeCharacter = new AnimeCharacter("Luffy", "Monkey D.", "Pirate King");
        LOGGER.info(animeCharacter.getFirstName());
        LOGGER.info(animeCharacter.getLastName());
        LOGGER.warn("Stopping app ...");
    }
}
