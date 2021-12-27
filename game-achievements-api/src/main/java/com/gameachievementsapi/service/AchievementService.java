package com.gameachievementsapi.service;

import com.gameachievementsapi.exception.GameAchievementsException;
import com.gameachievementsapi.model.Achievement;
import com.gameachievementsapi.model.Game;

import java.util.List;
import java.util.UUID;

public interface AchievementService {
    public List<Achievement> getAllGameAchievements (UUID gameId);
    public Game getGame(UUID id) throws GameAchievementsException;
    public Achievement getAchievement (UUID id) throws GameAchievementsException;
    public Achievement createAchievement (Achievement achievement) throws GameAchievementsException;
    public Achievement updateAchievement (Achievement achievement) throws GameAchievementsException;
    public void deleteAchievement (UUID id) throws GameAchievementsException;
}
