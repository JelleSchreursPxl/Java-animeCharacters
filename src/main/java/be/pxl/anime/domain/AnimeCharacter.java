package be.pxl.anime.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="anime_character")
public class AnimeCharacter {

    private static final Logger LOGGER = LogManager.getLogger(AnimeCharacter.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String characterName;
    private String phoneNumber;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Mission> missions = new ArrayList<>();

    @Column(name="weapons")
    private String weaponName;

    @ManyToOne
    private Crew crew;

    @OneToOne
    private ShipInformation shipInformation;

    public AnimeCharacter(){
        // JPA only
    }

    public AnimeCharacter(String firstName, String lastName, String characterName) {
        LOGGER.debug("Creating new anime character ...");
        this.firstName = firstName;
        this.lastName = lastName;
        this.characterName = characterName;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
    public void setWeaponName(String weaponName) { this.weaponName = weaponName; }
    public void setCrew(Crew crew) { this.crew = crew; }
    public void setShipInformation(ShipInformation shipInformation) {
        this.shipInformation = shipInformation;
    }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber;}

    // Getters
    public String getFirstName() {
        LOGGER.trace("FirstName of " + characterName + " was revealed");
        return firstName;
    }
    public String getLastName() {
        LOGGER.fatal("LastName of " + characterName + " was revealed");
        return lastName;
    }

    public Long getId() { return id; }
    public String getCharacterName() {
        return characterName;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public Crew getCrew() {
        return crew;
    }

    public ShipInformation getShipInformation() {
        return shipInformation;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public List<Mission> getMissions() {
        return missions;
    }

    // Override
    @Override
    public String toString() {
        return "Anime character: '" + characterName ;
    }
}

