package be.pxl.anime.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(
        name = "CrewByPhaseAndMinNumberOfCrewMembers",
        query = "SELECT c from Crew c WHERE c.crewState = :state AND c.animeCharacters.size >= :numberOfCrewMembers")
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "crew")
    private List<AnimeCharacter> animeCharacters = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private CrewState crewState;

    public Crew(){

    }

    public Crew(String name) {
        this.name = name;
        this.crewState = CrewState.INITIATING;
    }

    public void addAnimeCharacter(AnimeCharacter animeCharacter){
        animeCharacters.add(animeCharacter);
    }

    public void removeAnimeCharacter(AnimeCharacter animeCharacter){
        animeCharacters.remove(animeCharacter);
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }
    public void setCrewState(CrewState crewState) {
        this.crewState = crewState;
    }

    // getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public CrewState getCrewState() { return crewState; }
    public List<AnimeCharacter> getAnimeCharacters() { return animeCharacters; }
}
