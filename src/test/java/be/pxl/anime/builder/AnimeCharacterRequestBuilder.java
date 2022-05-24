package be.pxl.anime.builder;

import be.pxl.anime.api.anime_character.AnimeCharacterRequest;

public final class AnimeCharacterRequestBuilder {
    private String firstName;
    private String lastName;
    private String characterName;

    private AnimeCharacterRequestBuilder() {
    }

    public static AnimeCharacterRequestBuilder anAnimeCharacterRequest() {
        return new AnimeCharacterRequestBuilder();
    }

    public AnimeCharacterRequestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AnimeCharacterRequestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AnimeCharacterRequestBuilder withCharacterName(String characterName) {
        this.characterName = characterName;
        return this;
    }

    public AnimeCharacterRequest build() {
        return new AnimeCharacterRequest(firstName, lastName, characterName);
    }
}
