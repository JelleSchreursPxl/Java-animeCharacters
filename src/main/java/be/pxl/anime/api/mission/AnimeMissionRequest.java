package be.pxl.anime.api.mission;

public class AnimeMissionRequest {
    private final Long missionId;
    private final Long animeCharacterId;

    public AnimeMissionRequest(Long missionId, Long animeCharacterId){
        this.missionId = missionId;
        this.animeCharacterId = animeCharacterId;
    }

    public Long getMissionId() { return missionId; }
    public Long getAnimeCharacterId() { return animeCharacterId; }
}
