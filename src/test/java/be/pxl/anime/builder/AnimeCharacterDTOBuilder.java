package be.pxl.anime.builder;

import be.pxl.anime.api.anime_character.AnimeCharacterDTO;

public final class AnimeCharacterDTOBuilder {
    public static final Long ANIMECHARACTER_ID = 13L;
    public static final String FIRSTNAME = "Luffy";
    public static final String LASTNAME = "Monkey D.";
    public static final String ANIMECHARACTER_NAME = "Monkey D. Luffy";

    private Long id = ANIMECHARACTER_ID;
    private String firstName = FIRSTNAME;
    private String lastName = LASTNAME;
    private String characterName = ANIMECHARACTER_NAME;

    private AnimeCharacterDTOBuilder() {
    }

    public static AnimeCharacterDTOBuilder anAnimeCharacterDTO() {
        return new AnimeCharacterDTOBuilder();
    }

    public AnimeCharacterDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AnimeCharacterDTOBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AnimeCharacterDTOBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AnimeCharacterDTOBuilder withAnimeCharacterName(String characterName) {
        this.characterName = characterName;
        return this;
    }

    public AnimeCharacterDTO build() {
        AnimeCharacterDTO animeCharacterDTO = new AnimeCharacterDTO();
        animeCharacterDTO.setId(id);
        animeCharacterDTO.setFirstName(firstName);
        animeCharacterDTO.setLastName(lastName);
        animeCharacterDTO.setAnimeCharacterName(characterName);
        return animeCharacterDTO;
    }
}
