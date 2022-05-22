package be.pxl.anime;

import be.pxl.anime.dao.AnimeCharacterDao;
import be.pxl.anime.domain.AnimeCharacter;
import be.pxl.anime.domain.ShipInformation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class CreateAnimeCharacterApp {

	private static final Logger LOGGER = LogManager.getLogger(CreateCrewApp.class);

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		Scanner scanner = new Scanner(System.in);
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("anime");
			entityManager = entityManagerFactory.createEntityManager();
			AnimeCharacterDao animeCharacterDao = new AnimeCharacterDao(entityManager);
			System.out.println("Anime character name: ");
			String name = scanner.nextLine();
			System.out.println("Ship name: ");
			String shipName = scanner.nextLine();
			System.out.println("Ship special force: ");
			String specialForce = scanner.nextLine();
			System.out.println("Sea location: ");
			String seaLocation = scanner.nextLine();
			AnimeCharacter animeCharacter = new AnimeCharacter();
			animeCharacter.setCharacterName(name);
			ShipInformation shipInformation = new ShipInformation();
			shipInformation.setShipName(shipName);
			shipInformation.setShipSpecialForce(specialForce);
			shipInformation.setCurrentSeaLocation(seaLocation);
			animeCharacter.setShipInformation(shipInformation);
			animeCharacterDao.save(animeCharacter);
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
