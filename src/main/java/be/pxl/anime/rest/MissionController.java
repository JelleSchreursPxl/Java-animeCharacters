package be.pxl.anime.rest;

import be.pxl.anime.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mission")
public class MissionController {
    private final MissionService missionService;

    @Autowired
    public MissionController(MissionService missionService) { this.missionService = missionService; }
}
