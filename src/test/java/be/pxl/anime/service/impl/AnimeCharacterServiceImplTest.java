package be.pxl.anime.service.impl;

import be.pxl.anime.api.anime_character.AnimeCharacterDTO;
import be.pxl.anime.api.anime_character.AnimeCharacterRequest;
import be.pxl.anime.builder.AnimeCharacterBuilder;
import be.pxl.anime.domain.AnimeCharacter;
import be.pxl.anime.repository.AnimeCharacterRepository;
import be.pxl.anime.service.impl.AnimeCharacterServiceImpl;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ValidationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnimeCharacterServiceImplTest {
    private static final String ANIMECHARACTER_NAME = "Son Goku";
    private static final String LAST_NAME = "Bardock";
    private static final String FIRST_NAME = "Kakarot";

    @Mock
    private AnimeCharacterRepository animeCharacterRepository;

    @Captor
    private ArgumentCaptor<AnimeCharacter> animeCharacterArgumentCaptor;

    @InjectMocks
    private AnimeCharacterServiceImpl animeCharacterService;

    private final AnimeCharacter animeCharacter = AnimeCharacterBuilder
            .anAnimeCharacter()
            .withFirstName(FIRST_NAME)
            .withLastName(LAST_NAME)
            .withCharacterName(ANIMECHARACTER_NAME)
            .build();

    @Test
    public void throwsValidationExceptionWhenAnimeCharacterNameIsNotUnique() {
        when(animeCharacterRepository.findAnimeCharacterByCharacterName(ANIMECHARACTER_NAME))
                .thenReturn(Optional.of(animeCharacter));
        AnimeCharacterRequest request = new AnimeCharacterRequest(FIRST_NAME, LAST_NAME, ANIMECHARACTER_NAME);
        ValidationException validationException = assertThrows(ValidationException.class, () -> animeCharacterService.createAnimeCharacter(request));
        assertEquals("This anime character name is already taken.", validationException.getMessage());
    }

    @Test
    public void savesAnimeCharacterWhenAnimeCharacterIsUnique(){
        when(animeCharacterRepository.findAnimeCharacterByCharacterName(ANIMECHARACTER_NAME))
                .thenReturn(Optional.empty());
        when(animeCharacterRepository.save(Mockito.any(AnimeCharacter.class)))
                .thenAnswer(AdditionalAnswers.returnsFirstArg());
        AnimeCharacterRequest request = new AnimeCharacterRequest(FIRST_NAME, LAST_NAME, ANIMECHARACTER_NAME);
        AnimeCharacterDTO result = animeCharacterService.createAnimeCharacter(request);
        assertEquals(FIRST_NAME, result.getFirstName());
        assertEquals(LAST_NAME, result.getLastName());
        assertEquals(ANIMECHARACTER_NAME, result.getCharacterName());

        Mockito.verify(animeCharacterRepository).save(animeCharacterArgumentCaptor.capture());
        AnimeCharacter savedAnimeCharacter = animeCharacterArgumentCaptor.getValue();
        assertEquals(FIRST_NAME, savedAnimeCharacter.getFirstName());
        assertEquals(LAST_NAME, savedAnimeCharacter.getLastName());
        assertEquals(ANIMECHARACTER_NAME, savedAnimeCharacter.getCharacterName());

    }
}
