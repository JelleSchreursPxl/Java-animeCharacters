package be.pxl.anime.domain;

import javax.persistence.*;

@Entity
public class StoryVillage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String villageName;

    @OneToOne(cascade = CascadeType.ALL)
    private AnimeCharacter animeCharacter;

    // setters
    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }
    public void setAnimeCharacter(AnimeCharacter animeCharacter) {
        this.animeCharacter = animeCharacter;
    }

    // getters
    public Long getId() { return id;}
    public String getVillageName() { return villageName; }
    public AnimeCharacter getAnimeCharacter() { return animeCharacter; }
}
