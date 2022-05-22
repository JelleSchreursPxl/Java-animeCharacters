package be.pxl.anime.dao;

import be.pxl.anime.domain.AnimeCharacter;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.function.Consumer;

public class AnimeCharacterDao {
    private final EntityManager entityManager;

    public AnimeCharacterDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(AnimeCharacter animeCharacter){
        executeInsideTransaction(entityManager -> entityManager.persist(animeCharacter));
    }

    public void update(AnimeCharacter animeCharacter){
        executeInsideTransaction(entityManager -> entityManager.merge(animeCharacter));
    }

    public void delete(AnimeCharacter animeCharacter){
        executeInsideTransaction(entityManager -> entityManager.remove(animeCharacter));
    }

    public AnimeCharacter findByName(String name) {
        TypedQuery<AnimeCharacter> query = entityManager.createQuery("SELECT ac FROM AnimeCharacter ac WHERE ac.characterName = :name", AnimeCharacter.class);

        query.setParameter("name", name);
        return query.getSingleResult();
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
