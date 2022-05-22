package be.pxl.anime.repository;

import be.pxl.anime.domain.AnimeCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimeCharacterRepository extends JpaRepository<AnimeCharacter, Long> {
    Optional<AnimeCharacter> findAnimeCharacterByCharacterName(String animeCharacterName);
}
