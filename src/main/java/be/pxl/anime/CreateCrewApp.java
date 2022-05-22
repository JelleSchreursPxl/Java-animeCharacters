package be.pxl.anime;

import be.pxl.anime.dao.CrewDao;
import be.pxl.anime.domain.Crew;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class CreateCrewApp {

	private static final Logger LOGGER = LogManager.getLogger(CreateCrewApp.class);

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		Scanner scanner = new Scanner(System.in);
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("anime");
			entityManager = entityManagerFactory.createEntityManager();
			CrewDao crewDao = new CrewDao(entityManager);
			System.out.println("Crew name: ");
			String name = scanner.nextLine();
			Crew crew = new Crew(name);
			crewDao.save(crew);
		}
		finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (entityManagerFactory != null) {
				entityManagerFactory.close();
			}
		}
	}
}
