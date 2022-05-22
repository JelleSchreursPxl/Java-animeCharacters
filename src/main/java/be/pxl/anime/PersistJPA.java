package be.pxl.anime;

import be.pxl.anime.domain.AnimeCharacter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class PersistJPA {
    private static final Logger LOGGER = LogManager.getLogger(PersistJPA.class);

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("anime");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        AnimeCharacter animeCharacter = new AnimeCharacter("Luffy", "Monkey D.", "Monkey D. Luffy");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(animeCharacter);
        transaction.commit();
        long animeCharacterId = animeCharacter.getId();
        LOGGER.info("Anime character saved with id [ " + animeCharacterId + " ]");
        entityManager.clear();

        AnimeCharacter savedAnimeCharacter = entityManager.find(AnimeCharacter.class, animeCharacterId);
        LOGGER.info(savedAnimeCharacter.getCharacterName());
        entityManager.close();
        entityManagerFactory.close();
    }
}
