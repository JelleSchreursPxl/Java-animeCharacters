package be.pxl.anime.rest;

import be.pxl.anime.api.anime_character.AnimeCharacterDTO;
import be.pxl.anime.api.anime_character.AnimeCharacterRequest;
import be.pxl.anime.service.AnimeCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anime_characters")
public class AnimeCharacterController {
    private final AnimeCharacterService animeCharacterService;

    @Autowired
    public AnimeCharacterController(AnimeCharacterService animeCharacterService){
        this.animeCharacterService = animeCharacterService;
    }

    @GetMapping
    public List<AnimeCharacterDTO> getAnimeCharacters(){
        return animeCharacterService.findAllAnimeCharacter();
    }

    @GetMapping("/{animeCharacterId}")
    public AnimeCharacterDTO getAnimeCharacterById(@PathVariable Long animeCharacterId){
        return animeCharacterService.findAnimeCharacterById(animeCharacterId);
    }

    @PostMapping
    public ResponseEntity<AnimeCharacterDTO> createAnimeCharacter(@RequestBody AnimeCharacterRequest animeCharacterRequest){
        return new ResponseEntity<>(animeCharacterService.createAnimeCharacter(animeCharacterRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{animeCharacterId}")
    public AnimeCharacterDTO updateAnimeCharacter(@PathVariable Long animeCharacterId, @RequestBody AnimeCharacterRequest animeCharacterRequest){
        return animeCharacterService.updateAnimeCharacter(animeCharacterId, animeCharacterRequest);
    }

    @DeleteMapping("/{animeCharacterId}")
    public ResponseEntity<Void> deleteAnimeCharacter(@PathVariable Long animeCharacterId){
        boolean deleted = animeCharacterService.deleteAnimeCharacter(animeCharacterId);
        return deleted? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
