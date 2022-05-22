package be.pxl.anime;

import be.pxl.anime.domain.AnimeCharacter;
import be.pxl.anime.repository.AnimeCharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJdbcTest
public class AnimeCharacterTest {
    private static final String ANIMECHARACTER_NAME = "Pirate King";

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private AnimeCharacterRepository animeCharacterRepository;

    private AnimeCharacter animeCharacter = new AnimeCharacter("Luffy", "Monkey D.", ANIMECHARACTER_NAME);

    @BeforeEach
    public void init(){
        animeCharacterRepository.save(animeCharacter);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void returnsAnimeCharacterWithGivenCharacterName(){
        Optional<AnimeCharacter> animeCharacterUnderTest =
                animeCharacterRepository.findAnimeCharacterByCharacterName(ANIMECHARACTER_NAME);

        assertTrue(animeCharacterUnderTest.isPresent());
        assertEquals(ANIMECHARACTER_NAME, animeCharacterUnderTest.get().getCharacterName());
    }

    @Test
    public void returnsEmptyOptionalWHenInstituteWithGivenName(){
        Optional<AnimeCharacter> animeCharacterUnderTest =
                animeCharacterRepository.findAnimeCharacterByCharacterName("Gundam Knight");

        assertTrue(animeCharacterUnderTest.isEmpty());
    }
}
