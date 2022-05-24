package be.pxl.anime;

import be.pxl.anime.domain.AnimeCharacter;
import be.pxl.anime.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

//@SpringBootApplication
public class CallMonkeyDLuffy {

    @Autowired
    private CallService callService;

    @Value("${Luffy.phonenumber}")
    private String phoneNumber;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CallMonkeyDLuffy.class);
        CallMonkeyDLuffy app = context.getBean(CallMonkeyDLuffy.class);
        app.doCall();
    }

    private void doCall() {
        AnimeCharacter pirateKing = new AnimeCharacter("Luffy", "Monkey D.", "Pirate King");
        pirateKing.setPhoneNumber(phoneNumber);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give your message for the Pirate King: ");
        String message = scanner.nextLine();
        callService.call(pirateKing, message);
    }
}
