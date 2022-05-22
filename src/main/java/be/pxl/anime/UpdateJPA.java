package be.pxl.anime;

import be.pxl.anime.domain.AnimeCharacter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UpdateJPA {
    private static final Logger LOGGER = LogManager.getLogger(UpdateJPA.class);

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("anime");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Long animeCharacterId = 2L;
        AnimeCharacter savedAnimeCharacter = entityManager.find(AnimeCharacter.class, animeCharacterId);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        savedAnimeCharacter.setWeaponName("Gum-Gum Fruit");
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
