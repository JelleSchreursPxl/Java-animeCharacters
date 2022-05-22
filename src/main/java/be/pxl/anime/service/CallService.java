package be.pxl.anime.service;

import be.pxl.anime.domain.AnimeCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CallService {

    private final SmsService smsService;
    private final Map<AnimeCharacter, Integer> numberOfCalls = new HashMap<>();

    @Autowired
    public CallService(SmsService smsService) {
        this.smsService = smsService;
    }

    public void call(AnimeCharacter animeCharacter, String message) {
        numberOfCalls.merge(animeCharacter, 1, Integer::sum);
        smsService.sendSms(message, animeCharacter.getPhoneNumber());
    }

    public int getNumberOfCalls(AnimeCharacter animeCharacter) {
        if (!numberOfCalls.containsKey(animeCharacter)) {
            return 0;
        }
        return numberOfCalls.get(animeCharacter);
    }

}
