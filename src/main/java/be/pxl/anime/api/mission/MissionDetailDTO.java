package be.pxl.anime.api.mission;

import be.pxl.anime.api.anime_character.AnimeCharacterDTO;
import be.pxl.anime.domain.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionDetailDTO extends MissionDTO{
    private final List<AnimeCharacterDTO> animeCharacters;

    public MissionDetailDTO(Mission mission){
        super(mission);
        animeCharacters = mission.getAnimeCharacters().stream().map(AnimeCharacterDTO::new).collect(Collectors.toList());
    }

    public List<AnimeCharacterDTO> getAnimeCharacters() { return animeCharacters; }
}
