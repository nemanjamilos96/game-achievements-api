package com.gameachievementsapi.service;

import com.gameachievementsapi.exception.GameAchievementsException;
import com.gameachievementsapi.model.Game;
import com.gameachievementsapi.model.dto.AchievementDto;

import java.util.List;
import java.util.UUID;

public interface AchievementService {
    public List<AchievementDto> getGameAchievements (UUID gameId);
    public Game getGame(UUID id) throws GameAchievementsException;
    public AchievementDto getAchievement (UUID id) throws GameAchievementsException;
    public AchievementDto createAchievement (AchievementDto achievementDto, UUID gameId) throws GameAchievementsException;
    public AchievementDto updateAchievement (AchievementDto achievementDto, UUID id) throws GameAchievementsException;
    public void deleteAchievement (UUID id) throws GameAchievementsException;
}
