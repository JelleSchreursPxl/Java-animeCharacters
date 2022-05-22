package be.pxl.anime.builder;

import be.pxl.anime.domain.AnimeCharacter;
import be.pxl.anime.domain.Crew;
import be.pxl.anime.domain.ShipInformation;

public final class AnimeCharacterBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String characterName;
    private String phoneNumber;
    private String weaponName;
    private Crew crew;
    private ShipInformation shipInformation;

    private AnimeCharacterBuilder() {
    }

    public static AnimeCharacterBuilder anAnimeCharacter() {
        return new AnimeCharacterBuilder();
    }

    public AnimeCharacterBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AnimeCharacterBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AnimeCharacterBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AnimeCharacterBuilder withCharacterName(String characterName) {
        this.characterName = characterName;
        return this;
    }

    public AnimeCharacterBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public AnimeCharacterBuilder withWeaponName(String weaponName) {
        this.weaponName = weaponName;
        return this;
    }

    public AnimeCharacterBuilder withCrew(Crew crew) {
        this.crew = crew;
        return this;
    }

    public AnimeCharacterBuilder withShipInformation(ShipInformation shipInformation) {
        this.shipInformation = shipInformation;
        return this;
    }

    public AnimeCharacter build() {
        AnimeCharacter animeCharacter = new AnimeCharacter();
        animeCharacter.setFirstName(firstName);
        animeCharacter.setLastName(lastName);
        animeCharacter.setCharacterName(characterName);
        animeCharacter.setPhoneNumber(phoneNumber);
        animeCharacter.setWeaponName(weaponName);
        animeCharacter.setCrew(crew);
        animeCharacter.setShipInformation(shipInformation);
        return animeCharacter;
    }
}
