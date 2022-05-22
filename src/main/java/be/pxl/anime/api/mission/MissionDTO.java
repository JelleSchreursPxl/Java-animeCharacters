package be.pxl.anime.api.mission;

import be.pxl.anime.domain.Mission;

public class MissionDTO {
    private Long id;
    private String missionName;
    private boolean completed;
    private boolean deleted;

    public MissionDTO(Mission mission) {
        this.id = mission.getId();
        this.missionName = mission.getMissionName();
        this.completed = mission.isCompleted();
        this.deleted = mission.isDeleted();
    }
}
