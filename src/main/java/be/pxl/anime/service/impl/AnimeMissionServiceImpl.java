package be.pxl.anime.service.impl;

import be.pxl.anime.api.mission.*;
import be.pxl.anime.domain.Mission;
import be.pxl.anime.exception.ResourceNotFoundException;
import be.pxl.anime.repository.MissionRepository;
import be.pxl.anime.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimeMissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;

    @Autowired
    public AnimeMissionServiceImpl(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Override
    public List<MissionDTO> findAllMissions() {
        return missionRepository.findAll().stream().map(MissionDTO::new).collect(Collectors.toList());
    }

    @Override
    public MissionDTO findMissionById(Long missionId) {
        return missionRepository.findById(missionId)
                                .map(MissionDTO::new)
                                .orElseThrow(() -> new ResourceNotFoundException("Mission", "ID", missionId));
    }

    @Override
    public MissionDTO createMission(CreateMissionRequest missionRequest) {
        Optional<Mission> missionByName = missionRepository.findMissionByName(missionRequest.getMissionName());
        if (missionByName.isPresent()) {
            throw new ValidationException("Name already exists");
        }
        Mission mission = new Mission();
        mission.setMissionName(missionRequest.getMissionName());
        Mission savedMission = missionRepository.save(mission);
        return new MissionDTO(savedMission);
    }

    @Override
    public MissionDTO updateMission(Long missionId, UpdateMissionRequest missionRequest) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new ResourceNotFoundException("Mission", "ID", missionId));
        mission.setCompleted(missionRequest.isCompleted());
        return new MissionDetailDTO(mission);
    }

    @Override
    public boolean deleteMission(Long missionId) {
        return missionRepository.findById(missionId).map(mission -> {
            missionRepository.delete(mission);
            return true;
        }).orElseThrow(() -> new ResourceNotFoundException("Mission", "ID", missionId));
    }
}
