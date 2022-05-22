package be.pxl.anime.service.impl;

import be.pxl.anime.api.anime_character.AnimeCharacterDTO;
import be.pxl.anime.api.anime_character.AnimeCharacterRequest;
import be.pxl.anime.domain.AnimeCharacter;
import be.pxl.anime.exception.ResourceNotFoundException;
import be.pxl.anime.repository.AnimeCharacterRepository;
import be.pxl.anime.service.AnimeCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimeCharacterServiceImpl implements AnimeCharacterService {
    private final AnimeCharacterRepository animeCharacterRepository;

    @Autowired
    public AnimeCharacterServiceImpl(AnimeCharacterRepository animeCharacterRepository){
        this.animeCharacterRepository = animeCharacterRepository;
    }

    public List<AnimeCharacterDTO> findAllAnimeCharacter(){
        return animeCharacterRepository
                .findAll()
                .stream()
                .map(AnimeCharacterDTO::new)
                .collect(Collectors.toList());
    }

    public AnimeCharacterDTO findAnimeCharacterById(Long animeCharacterId){
        return animeCharacterRepository.findById(animeCharacterId)
                .map(AnimeCharacterDTO::new)
                .orElseThrow(()->
                        new ResourceNotFoundException("Anime character", "ID", animeCharacterId));
    }

    public AnimeCharacterDTO createAnimeCharacter(AnimeCharacterRequest animeCharacterRequest){
        Optional<AnimeCharacter> existingAnimeCharacter = animeCharacterRepository.findAnimeCharacterByCharacterName(animeCharacterRequest.getCharacterName());
        existingAnimeCharacter.ifPresent(ac -> {
            throw new ValidationException("This anime character name is already taken.");
        });

        AnimeCharacter animeCharacter = new AnimeCharacter();
        animeCharacter.setFirstName(animeCharacterRequest.getFirstName());
        animeCharacter.setLastName(animeCharacterRequest.getLastName());
        animeCharacter.setCharacterName(animeCharacter.getCharacterName());
        AnimeCharacter newAnimeCharacter = animeCharacterRepository.save(animeCharacter);
        return new AnimeCharacterDTO(newAnimeCharacter);
    }

    public AnimeCharacterDTO updateAnimeCharacter(Long animeCharacterId, AnimeCharacterRequest animeCharacterRequest){
        return animeCharacterRepository.findById(animeCharacterId)
                .map(
                    animeCharacter -> {
                        animeCharacter.setFirstName(animeCharacterRequest.getFirstName());
                        animeCharacter.setLastName(animeCharacterRequest.getLastName());
                        animeCharacter.setCharacterName(animeCharacter.getCharacterName());
                        return new AnimeCharacterDTO(animeCharacterRepository.save(animeCharacter));
                }).orElseThrow(() -> new ResourceNotFoundException("Anime character", "ID", animeCharacterId));
    }

    public boolean deleteAnimeCharacter(Long animeCharacterId){
        return animeCharacterRepository.findById(animeCharacterId)
                .map(animeCharacter -> {
                    animeCharacterRepository.delete(animeCharacter);
                    return true;
                }).orElseThrow(() -> new ResourceNotFoundException("Anime character", "ID", animeCharacterId));
    }
}
