package be.pxl.anime;

import be.pxl.anime.domain.AnimeCharacter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class RetrieveJPA {
    private static final Logger LOGGER = LogManager.getLogger(RetrieveJPA.class);

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("anime");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<AnimeCharacter> query = entityManager
                .createQuery("SELECT ac FROM AnimeCharacter ac where ac.characterName = :name", AnimeCharacter.class);
        query.setParameter("name", "Monkey D. Luffy");
        AnimeCharacter result = query.getSingleResult();

        LOGGER.info(result.getFirstName());

        entityManager.close();
        entityManagerFactory.close();
    }
}
