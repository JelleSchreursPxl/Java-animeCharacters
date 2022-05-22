package be.pxl.anime.mocking;

import be.pxl.anime.service.AnimeCharacterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class AnimeCharacterControllerGetAnimeCharacterTest {

    @MockBean
    private AnimeCharacterService animeCharacterService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAnimeCharacterById_ReturnsExistingAnimeCharacter() throws Exception {

    }
}
