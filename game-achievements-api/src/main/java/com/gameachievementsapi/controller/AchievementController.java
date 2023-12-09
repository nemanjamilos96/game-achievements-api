package com.gameachievementsapi.controller;

import com.gameachievementsapi.exception.GameAchievementsException;
import com.gameachievementsapi.model.dto.AchievementDto;
import com.gameachievementsapi.service.AchievementService;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class AchievementController extends BaseController {

    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping(path="/games/{gameId}/achievements")
    public List<AchievementDto> getGameAchievements (@PathVariable UUID gameId){
        return achievementService.getGameAchievements(gameId);
    }

    @GetMapping(path="/achievements/{id}")
    public AchievementDto getAchievement (@PathVariable UUID id) throws GameAchievementsException{
        return achievementService.getAchievement(id);
    }

    @PutMapping(path="/create/achievement/{gameId}/game")
    public AchievementDto createAchievement (@Valid @RequestBody AchievementDto achievementDto, @PathVariable UUID gameId, BindingResult result) throws BindException, GameAchievementsException{
        if (result.hasErrors())
            throw new BindException(result);
        return achievementService.createAchievement(achievementDto, gameId);
    }

    @PutMapping(path="/update/achievement/{id}")
    public AchievementDto updateAchievement (@Valid @RequestBody AchievementDto achievementDto, @PathVariable UUID id, BindingResult result) throws BindException, GameAchievementsException {
        if (result.hasErrors())
            throw new BindException(result);
        return achievementService.updateAchievement(achievementDto, id);
    }

    @DeleteMapping(path="/delete/achievement/{id}")
    public void deleteAchievement (@PathVariable UUID id) throws GameAchievementsException {
         achievementService.deleteAchievement(id);
    }
}
