package com.gameachievementsapi.controller;

import com.gameachievementsapi.exception.GameAchievementsException;
import com.gameachievementsapi.model.Achievement;
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

    @GetMapping(path="/get-all-game-achievements/{gameId}")
    public List<Achievement> getAllGameAchievements (@PathVariable UUID gameId){
        return achievementService.getAllGameAchievements(gameId);
    }

    @GetMapping(path="/get/{id}")
    public Achievement getAchievement (@PathVariable UUID id) throws GameAchievementsException{
        return achievementService.getAchievement(id);
    }

    @PutMapping(path="/create-achievement")
    public Achievement createAchievement (@Valid @RequestBody Achievement achievement, BindingResult result) throws BindException, GameAchievementsException{
        if (result.hasErrors())
            throw new BindException(result);
        return achievementService.createAchievement(achievement);
    }

    @PutMapping(path="/update-achievement")
    public Achievement updateAchievement (@Valid @RequestBody Achievement achievement, BindingResult result) throws BindException, GameAchievementsException {
        if (result.hasErrors())
            throw new BindException(result);
        return achievementService.updateAchievement(achievement);
    }

    @DeleteMapping(path="/delete-achievement/{id}")
    public void deleteAchievement (@PathVariable UUID id) throws GameAchievementsException {
         achievementService.deleteAchievement(id);
    }
}
