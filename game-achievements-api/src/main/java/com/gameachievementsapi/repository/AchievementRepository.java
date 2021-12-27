package com.gameachievementsapi.repository;

import com.gameachievementsapi.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, UUID> {
    public List<Achievement> findByGameIdOrderByDisplayOrder(UUID gameId);
    public void deleteById(UUID gameId);
}
