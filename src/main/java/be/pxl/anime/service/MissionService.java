package be.pxl.anime.service;

import be.pxl.anime.api.mission.CreateMissionRequest;
import be.pxl.anime.api.mission.MissionDTO;
import be.pxl.anime.api.mission.UpdateMissionRequest;

import java.util.List;

public interface MissionService {
    List<MissionDTO> findAllMissions();
    MissionDTO findMissionById(Long missionId);
    MissionDTO createMission(CreateMissionRequest missionRequest);
    MissionDTO updateMission(Long missionId, UpdateMissionRequest missionRequest);
    boolean deleteMission(Long missionId);
}
