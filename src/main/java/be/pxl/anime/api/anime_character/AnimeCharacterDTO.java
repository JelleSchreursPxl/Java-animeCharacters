package be.pxl.anime.api.anime_character;

import be.pxl.anime.domain.AnimeCharacter;

public class AnimeCharacterDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String characterName;

    public AnimeCharacterDTO(){
    }

    public AnimeCharacterDTO(AnimeCharacter animeCharacter) {
        this.id = animeCharacter.getId();
        this.firstName = animeCharacter.getFirstName();
        this.lastName = animeCharacter.getLastName();
        this.characterName = animeCharacter.getCharacterName();
    }

    public Long getId() {
        return id;
    }
    public String getFirstName() { return firstName; }
    public String getLastName() {
        return lastName;
    }
    public String getCharacterName() {
        return characterName;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAnimeCharacterName(String animeCharacterName) { this.characterName = animeCharacterName; }
}
