package be.pxl.anime.builder;

import be.pxl.anime.api.anime_character.AnimeCharacterDTO;
import be.pxl.anime.api.anime_character.AnimeCharacterDetailDTO;

import java.util.Collections;

public class AnimeCharacterDetailDTOBuilder extends AnimeCharacterDTO {
    public static final Long ID = 14L;
    public static final String FIRSTNAME = "Kakarot";
    public static final String LASTNAME = "Bardock";
    public static final String CHARACTER_NAME = "Son Goku";

    private Long id = ID;
    private String firstName = FIRSTNAME;
    private String lastName = LASTNAME;
    private String characterName = CHARACTER_NAME;

    private AnimeCharacterDetailDTOBuilder(){
    }

    public static AnimeCharacterDetailDTOBuilder aAnimeCharacterDetailDTO() { return new AnimeCharacterDetailDTOBuilder(); }

    public AnimeCharacterDetailDTOBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public AnimeCharacterDetailDTOBuilder withfirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public AnimeCharacterDetailDTOBuilder withlastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public AnimeCharacterDetailDTOBuilder withCharacterName(String characterName){
        this.characterName = characterName;
        return this;
    }

    public AnimeCharacterDetailDTO build(){
        AnimeCharacterDetailDTO animeCharacter = new AnimeCharacterDetailDTO();
        animeCharacter.setId(id);
        animeCharacter.setFirstName(firstName);
        animeCharacter.setLastName(lastName);
        animeCharacter.setAnimeCharacterName(characterName);
        animeCharacter.setMissions(Collections.emptyList());
        return animeCharacter;
    }


}
