package be.pxl.anime.api.anime_character;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AnimeCharacterRequest {
    private String firstName;
    @NotBlank(message = "The character must have a lastname")
    @Size(min = 5)
    private String lastName;
    @NotBlank(message = "The character must have a name")
    private String characterName;

    public AnimeCharacterRequest(String firstName, String lastName, String characterName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.characterName = characterName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}
