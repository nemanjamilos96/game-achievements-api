package com.gameachievementsapi.service.impl;

import com.gameachievementsapi.exception.GameAchievementsException;
import com.gameachievementsapi.model.Achievement;
import com.gameachievementsapi.model.Game;
import com.gameachievementsapi.repository.AchievementRepository;
import com.gameachievementsapi.repository.GameRepository;
import com.gameachievementsapi.service.AchievementService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final GameRepository gameRepository;

    public AchievementServiceImpl(AchievementRepository achievementRepository, GameRepository gameRepository) {
        this.achievementRepository = achievementRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Achievement> getAllGameAchievements (UUID gameId){
        List<Achievement> achievements = achievementRepository.findByGameIdOrderByDisplayOrder(gameId);
        return achievements;
    }

    @Override
    public Achievement getAchievement(UUID id) throws GameAchievementsException {
        return achievementRepository.findById(id)
                .orElseThrow(() -> new GameAchievementsException("Achievement not found with id: "+ id));
    }

    @Override
    public Game getGame(UUID id) throws GameAchievementsException {
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameAchievementsException("Game not found with id: "+ id));
    }

    @Override
    public Achievement createAchievement(Achievement achievement) throws GameAchievementsException {
        Game game = getGame(achievement.getGame().getId());
        achievement.setGame(game);
        achievement.setCreated(LocalDate.now());

        return achievementRepository.save(achievement);
    }

    @Override
    public Achievement updateAchievement(Achievement achievement) throws GameAchievementsException{
        Achievement achievementUpload = getAchievement(achievement.getId());
        Game game = getGame(achievement.getGame().getId());

        achievementUpload.setDisplayName(achievement.getDisplayName());
        achievementUpload.setDescription(achievement.getDescription());
        achievementUpload.setIcon(achievement.getIcon());
        achievementUpload.setDisplayOrder(achievement.getDisplayOrder());
        achievementUpload.setGame(game);
        achievementUpload.setCreated(achievement.getCreated());
        achievementUpload.setUpdated(LocalDate.now());
        return achievementRepository.save(achievementUpload);
    }

    @Override
    public void deleteAchievement(UUID id) throws GameAchievementsException {
        Achievement achievement = getAchievement(id);
        achievementRepository.delete(achievement);
    }

}