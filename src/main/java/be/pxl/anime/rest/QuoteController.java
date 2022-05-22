package be.pxl.anime.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class QuoteController {
    private final List<String> quotes = new ArrayList<>();
    private final static Random RANDOM = new Random();

    @PostConstruct
    public void init(){
        quotes.add("You never know where your next scare is coming from. You've just gotta find the courage to face it. - Shaggy");
        quotes.add("I am the hope of the universe. I am the answer to all living things that cry out for peace. I am protector of the innocent. I am the light in the darkness. I am truth. Ally to good! Nightmare to you! - Son Goku");
        quotes.add("Fear is not evil. It tells you what weakness is. And once you know your weakness, you can become stronger as well as kinder. - Gildarts Clive");
        quotes.add("If you don’t take risks, you can’t create a future! - Monkey D. Luffy");
        quotes.add("People, who can’t throw something important away, can never hope to change anything - Armin Arlert");
        quotes.add("The world isn’t perfect. But it’s there for us, doing the best it can… that’s what makes it so damn beautiful. - Roy Mustang");
    }

    @GetMapping("/quote")
    public String doQuote(){
        return quotes.get(RANDOM.nextInt(quotes.size()));
    }
}
