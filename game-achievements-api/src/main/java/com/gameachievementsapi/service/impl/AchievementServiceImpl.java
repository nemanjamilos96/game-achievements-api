package com.gameachievementsapi.service.impl;

import com.gameachievementsapi.exception.GameAchievementsException;
import com.gameachievementsapi.model.Achievement;
import com.gameachievementsapi.model.Game;
import com.gameachievementsapi.model.dto.AchievementDto;
import com.gameachievementsapi.model.dto.GameDto;
import com.gameachievementsapi.repository.AchievementRepository;
import com.gameachievementsapi.repository.GameRepository;
import com.gameachievementsapi.service.AchievementService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final GameRepository gameRepository;

    public AchievementServiceImpl(AchievementRepository achievementRepository, GameRepository gameRepository) {
        this.achievementRepository = achievementRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public List<AchievementDto> getGameAchievements (UUID gameId){
        List<Achievement> achievements = getAllGameAchievements(gameId);
        return achievements.stream()
                 .map(this::mapAchievementDtoEntityToAchievementDtoDTO)
                 .collect(Collectors.toList());
    }

    @Override
    public AchievementDto getAchievement(UUID id) throws GameAchievementsException {
        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() -> new GameAchievementsException("Achievement not found with id: "+ id));
        return mapAchievementDtoEntityToAchievementDtoDTO(achievement);
    }

    @Override
    public Game getGame(UUID id) throws GameAchievementsException {
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameAchievementsException("Game not found with id: "+ id));
    }

    @Override
    public AchievementDto createAchievement(AchievementDto achievementDto, UUID gameId) throws GameAchievementsException {
        Game game = getGame(gameId);
        Achievement achievement = new Achievement();
        achievement.setDisplayName(achievementDto.getDisplayName());
        achievement.setDisplayOrder(achievementDto.getDisplayOrder());
        achievement.setIcon(achievementDto.getIcon());
        achievement.setDescription(achievementDto.getDescription());
        achievement.setGame(game);
        achievement.setCreated(LocalDate.now());

        return mapAchievementDtoEntityToAchievementDtoDTO(achievementRepository.save(achievement));
    }

    @Override
    public AchievementDto updateAchievement(AchievementDto achievementDto, UUID id) throws GameAchievementsException{
        Achievement achievementUpload = achievementRepository.findById(id)
                .orElseThrow(() -> new GameAchievementsException("Achievement not found with id: "+ id));

        achievementUpload.setDisplayName(achievementDto.getDisplayName());
        achievementUpload.setDescription(achievementDto.getDescription());
        achievementUpload.setIcon(achievementDto.getIcon());
        achievementUpload.setDisplayOrder(achievementDto.getDisplayOrder());
        achievementUpload.setCreated(achievementDto.getCreated());
        achievementUpload.setUpdated(LocalDate.now());
        return mapAchievementDtoEntityToAchievementDtoDTO(achievementRepository.save(achievementUpload));
    }

    @Override
    public void deleteAchievement(UUID id) throws GameAchievementsException {
        achievementRepository.deleteById(id);
    }

    private List<Achievement> getAllGameAchievements(UUID gameId){
        return achievementRepository.findByGameIdOrderByDisplayOrder(gameId);
    }

    private AchievementDto mapAchievementDtoEntityToAchievementDtoDTO(Achievement entity) {
        AchievementDto dto = new AchievementDto();
        dto.setDisplayName(entity.getDisplayName());
        dto.setIcon(entity.getIcon());
        dto.setDescription(entity.getDescription());
        dto.setDisplayOrder(entity.getDisplayOrder());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        dto.setGame(new GameDto(entity.getGame().getDisplayName()));

        return dto;
    }

}