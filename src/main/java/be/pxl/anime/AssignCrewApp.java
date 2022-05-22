package be.pxl.anime;

import be.pxl.anime.dao.AnimeCharacterDao;
import be.pxl.anime.dao.CrewDao;
import be.pxl.anime.domain.AnimeCharacter;
import be.pxl.anime.domain.Crew;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AssignCrewApp {

	private static final Logger LOGGER = LogManager.getLogger(AssignCrewApp.class);

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		Scanner scanner = new Scanner(System.in);
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("anime");
			entityManager = entityManagerFactory.createEntityManager();
			CrewDao crewDao = new CrewDao(entityManager);
			AnimeCharacterDao researcherDao = new AnimeCharacterDao(entityManager);
			System.out.println("Project name: ");
			String crewName = scanner.nextLine();
			System.out.println("Researcher name: ");
			String researcherName = scanner.nextLine();
			Crew crew = crewDao.findByName(crewName);
			AnimeCharacter animeCharacter = researcherDao.findByName(researcherName);
			animeCharacter.setCrew(crew);
			researcherDao.update(animeCharacter);
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
