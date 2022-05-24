package be.pxl.anime.rest.servlets;

import be.pxl.anime.domain.servlets.University;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class UniversityController {

    private final WebClient webClient = WebClient.create("http://universities.hipolabs.com");

    @GetMapping("/country_selection")
    public String countrySelection() {
        return "country_selection";
    }

    @RequestMapping("/universities")
    public String addUser(@RequestParam(value = "country") String country,  Model model) {
        WebClient.ResponseSpec response = webClient.get()
                .uri(String.format("search?country=%s", country))
                .retrieve();
        University[] universities = response.bodyToMono(University[].class).block();
        model.addAttribute("universities", universities);
        return "universities";
    }
}
