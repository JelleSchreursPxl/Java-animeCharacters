package be.pxl.anime.rest;

import be.pxl.anime.api.anime_character.AnimeCharacterDetailDTO;
import be.pxl.anime.builder.AnimeCharacterDetailDTOBuilder;
import be.pxl.anime.exception.ResourceNotFoundException;
import be.pxl.anime.service.AnimeCharacterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static be.pxl.anime.builder.AnimeCharacterDTOBuilder.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AnimeCharacterController.class)
public class AnimeCharacterControllerGetAnimeCharacterTest {

    @MockBean
    private AnimeCharacterService animeCharacterService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAnimeCharacterById_ReturnsExistingAnimeCharacter() throws Exception {
        AnimeCharacterDetailDTO animeCharacterDetailDTO =
                AnimeCharacterDetailDTOBuilder
                        .aAnimeCharacterDetailDTO()
                        .withId(ANIMECHARACTER_ID)
                        .build();
        when(animeCharacterService.findAnimeCharacterById(ANIMECHARACTER_ID)).thenReturn(animeCharacterDetailDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("anime_character/{id}", ANIMECHARACTER_ID)
                                              .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ANIMECHARACTER_ID))
                .andExpect(jsonPath("$.firstName").value(FIRSTNAME))
                .andExpect(jsonPath("$.lastName").value(LASTNAME))
                .andExpect(jsonPath("$.characterName").value(ANIMECHARACTER_NAME));
    }

    @Test
    public void getAnimeCharacterById_ReturnsNotFoundWhenResourceNotFoundExceptionIsThrown() throws Exception {
        doThrow(ResourceNotFoundException.class)
                .when(animeCharacterService)
                .findAnimeCharacterById(ANIMECHARACTER_ID);
        mockMvc.perform(MockMvcRequestBuilders.get("anime_character/{id}", ANIMECHARACTER_ID)
                                              .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
