package be.pxl.anime.mocking;

import be.pxl.anime.builder.AnimeCharacterBuilder;
import be.pxl.anime.domain.AnimeCharacter;
import be.pxl.anime.service.CallService;
import be.pxl.anime.service.SmsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CallServiceTest {
    @Mock
    private SmsService smsService;

    @InjectMocks
    private CallService callService;

    private final AnimeCharacter animeCharacter =
            AnimeCharacterBuilder.anAnimeCharacter()
                                 .withCharacterName("animeCharacter")
                                 .withPhoneNumber("0496710721")
                                 .build();

    @Test
    public void smsServiceIsUsedOnCall() {
        callService.call(animeCharacter, "Test of Message");
        Mockito.verify(smsService).sendSms("Test of Message", "0496710721");
    }

    @Test
    public void countOfCalls() {

    }
}
