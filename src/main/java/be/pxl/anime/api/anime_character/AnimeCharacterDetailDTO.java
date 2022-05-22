package be.pxl.anime.api.anime_character;

import be.pxl.anime.api.mission.MissionDTO;
import be.pxl.anime.domain.AnimeCharacter;

import java.util.List;
import java.util.stream.Collectors;

public class AnimeCharacterDetailDTO extends AnimeCharacterDTO{
    private List<MissionDTO> missions;

    public AnimeCharacterDetailDTO(){
    }

    public AnimeCharacterDetailDTO(AnimeCharacter animeCharacter){
        super(animeCharacter);
        missions = animeCharacter.getMissions().stream().map(MissionDTO::new).collect(Collectors.toList());
    }

    public void setMissions(List<MissionDTO> missions){ this.missions = missions; }
    public List<MissionDTO> getMissions(){ return missions; }
}
