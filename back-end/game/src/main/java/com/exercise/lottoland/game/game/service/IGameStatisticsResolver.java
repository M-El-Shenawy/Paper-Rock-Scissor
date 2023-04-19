package com.exercise.lottoland.game.game.service;

import com.exercise.lottoland.game.game.model.GameStatisticsResponse;
import com.exercise.lottoland.game.game.model.Player;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public interface IGameStatisticsResolver {
    GameStatisticsResponse resolve(Map<String, Player> players, AtomicLong gameStatistics);
}
