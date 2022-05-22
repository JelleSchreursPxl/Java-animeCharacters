package be.pxl.anime.service;

import be.pxl.anime.api.anime_character.AnimeCharacterDTO;
import be.pxl.anime.api.anime_character.AnimeCharacterRequest;

import java.util.List;

public interface AnimeCharacterService {
    List<AnimeCharacterDTO> findAllAnimeCharacter();
    AnimeCharacterDTO findAnimeCharacterById(Long animeCharacterId);
    AnimeCharacterDTO createAnimeCharacter(AnimeCharacterRequest animeCharacterRequest);
    AnimeCharacterDTO updateAnimeCharacter(Long animeCharacterId, AnimeCharacterRequest animeCharacterRequest);
    boolean deleteAnimeCharacter(Long animeCharacterId);
}
