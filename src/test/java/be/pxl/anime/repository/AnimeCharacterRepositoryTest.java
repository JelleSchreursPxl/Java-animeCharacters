package be.pxl.anime.repository;

import be.pxl.anime.builder.AnimeCharacterBuilder;
import be.pxl.anime.domain.AnimeCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class AnimeCharacterRepositoryTest {
    private static final String ANIMECHARACTER_NAME = "Son Goku";

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private AnimeCharacterRepository animeCharacterRepository;
    private AnimeCharacter animeCharacter = AnimeCharacterBuilder.anAnimeCharacter().build();

    @BeforeEach
    public void init(){
        animeCharacterRepository.save(animeCharacter);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void returnsAnimeCharacterWithGivenAnimeCharacterName() {
        Optional<AnimeCharacter> animeCharacterUnderTest = animeCharacterRepository.findAnimeCharacterByCharacterName(ANIMECHARACTER_NAME);

        assertTrue(animeCharacterUnderTest.isPresent());
        assertEquals(ANIMECHARACTER_NAME, animeCharacterUnderTest.get().getCharacterName());
    }

    @Test
    public void returnsEmptyOptionalWhenNoInstituteWithGivenName() {
        Optional<AnimeCharacter> animeCharacterUnderTest = animeCharacterRepository.findAnimeCharacterByCharacterName("The Great Saiyaman");
        assertTrue(animeCharacterUnderTest.isEmpty());
    }

}
