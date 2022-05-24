package be.pxl.anime.service_call;

import be.pxl.anime.builder.AnimeCharacterBuilder;
import be.pxl.anime.domain.AnimeCharacter;
import be.pxl.anime.service.CallService;
import be.pxl.anime.service.SmsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @BeforeEach
    public void init(){
        callService.call(animeCharacter, "Test of Message");
    }

    @Test
    public void smsServiceIsUsedOnCall() {
        Mockito.verify(smsService).sendSms("Test of Message", "0496710721");
    }

    @Test
    public void countOfCalls() {
        Mockito.verify(smsService).sendSms("Test of Message", "0496710721");
        assertEquals(1, callService.getNumberOfCalls(animeCharacter));
    }
}
