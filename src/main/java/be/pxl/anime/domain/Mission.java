package be.pxl.anime.domain;

import be.pxl.anime.exception.EntityAlreadyExistsException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String missionName;
    private boolean completed;
    private boolean deleted;

    @ManyToMany(mappedBy = "missions")
    private List<AnimeCharacter> animeCharacters = new ArrayList<>();

    public Mission(){
    }

    public Mission(String missionName, boolean completed, boolean deleted) {
        this.missionName = missionName;
        this.completed = completed;
        this.deleted = deleted;
    }

    //setters
    public void setMissionName(String missionName) { this.missionName = missionName; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    //getters
    public Long getId() { return id; }
    public String getMissionName() { return missionName; }
    public boolean isCompleted() { return completed; }
    public boolean isDeleted() { return deleted; }
    public List<AnimeCharacter> getAnimeCharacters() { return animeCharacters; }

    public void addAnimeCharacter(AnimeCharacter animeCharacter){
        if(animeCharacters.contains(animeCharacter))
        {
            throw new EntityAlreadyExistsException();
        }
        animeCharacters.add(animeCharacter);

    }
}
