package be.pxl.anime.dao;

import be.pxl.anime.domain.Crew;
import be.pxl.anime.domain.CrewState;
import be.pxl.anime.exception.EntityAlreadyExistsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.function.Consumer;


public class CrewDao {
    private static final Logger LOGGER = LogManager.getLogger(CrewDao.class);

    private final EntityManager entityManager;

    public CrewDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(Crew crew){
        if(findByName(crew.getName()) != null ){
            throw new EntityAlreadyExistsException();
        }
        executeInsideTransaction(entityManager -> entityManager.persist(crew));
    }

    public void delete(Crew crew)
    {
        executeInsideTransaction(entityManager -> entityManager.remove(crew));
    }

    public Crew findByName(String name) {
        TypedQuery<Crew> query = entityManager.createQuery("SELECT c FROM Crew c WHERE c.name = :name", Crew.class);

        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No project found with name [" + name + "]");
            return null;
        }
    }

    public List<Crew> CrewByPhaseAndMinNumberOfCrewMembers(CrewState state, int minNumberOfCrewMembers) {
        TypedQuery<Crew> query = entityManager.createNamedQuery("CrewByPhaseAndMinNumberOfCrewMembers", Crew.class);
        query.setParameter("state", state);
        query.setParameter("numberOfCrewMembers", minNumberOfCrewMembers);
        return query.getResultList();
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

