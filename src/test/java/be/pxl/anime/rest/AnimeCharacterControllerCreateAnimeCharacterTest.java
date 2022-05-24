package be.pxl.anime.rest;

import be.pxl.anime.api.anime_character.AnimeCharacterRequest;
import be.pxl.anime.builder.AnimeCharacterRequestBuilder;
import be.pxl.anime.service.AnimeCharacterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static be.pxl.anime.builder.AnimeCharacterDTOBuilder.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AnimeCharacterController.class)
public class AnimeCharacterControllerCreateAnimeCharacterTest {
    @MockBean
    private AnimeCharacterService animeCharacterService;

    @Autowired
    private MockMvc mockMvc;

    @Captor
    private ArgumentCaptor<AnimeCharacterRequest> animeCharacterRequestArgumentCaptor;

    @Test
    public void testLastNameIsRequired() throws Exception {
        AnimeCharacterRequest animeCharacterRequest = AnimeCharacterRequestBuilder
                .anAnimeCharacterRequest()
                .withLastName("")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/anime_characters")
                .content(asJsonString(animeCharacterRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAnimeCharacterNameIsRequired() throws Exception {
        AnimeCharacterRequest animeCharacterRequest = AnimeCharacterRequestBuilder
                .anAnimeCharacterRequest()
                .withCharacterName("")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/anime_characters")
                .content(asJsonString(animeCharacterRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testLastNameWithOneCharacterIsNotValid() throws Exception {
        AnimeCharacterRequest animeCharacterRequest =
                AnimeCharacterRequestBuilder.anAnimeCharacterRequest()
                        .withLastName("D")
                        .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/anime_characters")
                        .content(asJsonString(animeCharacterRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAnimeCharacterIsCreatedWhenAllConstraintsAreSatisfied() throws Exception {
        AnimeCharacterRequest animeCharacterRequest = AnimeCharacterRequestBuilder.anAnimeCharacterRequest().build();
        mockMvc.perform(MockMvcRequestBuilders.post("/anime_characters")
                        .content(asJsonString(animeCharacterRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(animeCharacterService).createAnimeCharacter(animeCharacterRequestArgumentCaptor.capture());

        AnimeCharacterRequest createdAnimeCharacter = animeCharacterRequestArgumentCaptor.getValue();
        assertThat(createdAnimeCharacter.getFirstName()).isEqualTo(FIRSTNAME);
        assertThat(createdAnimeCharacter.getLastName()).isEqualTo(LASTNAME);
        assertThat(createdAnimeCharacter.getCharacterName()).isEqualTo(ANIMECHARACTER_NAME);
    }

    private String asJsonString(final Object object) {
        try{
            return new ObjectMapper().writeValueAsString(object);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
