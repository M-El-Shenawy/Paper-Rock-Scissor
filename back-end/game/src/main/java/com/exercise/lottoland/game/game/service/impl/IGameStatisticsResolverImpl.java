package com.exercise.lottoland.game.game.service.impl;

import com.exercise.lottoland.game.game.model.GameHelper;
import com.exercise.lottoland.game.game.model.GameStatisticsResponse;
import com.exercise.lottoland.game.game.model.Player;
import com.exercise.lottoland.game.game.service.IGameStatisticsResolver;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class IGameStatisticsResolverImpl implements IGameStatisticsResolver{
    @Override
    public GameStatisticsResponse resolve(Map<String, Player> players, AtomicLong gameStatistics) {
        GameStatisticsResponse response = new GameStatisticsResponse();
        response.setTotalWinsForPlayer1(players.get(GameHelper.Result.PLAYER_ONE.name()).getTotalWins());
        response.setTotalWinsForPlayer2(players.get(GameHelper.Result.PLAYER_TWO.name()).getTotalWins());
        response.setTotalDraws(players.get(GameHelper.Result.PLAYER_TWO.name()).getTotalDraws());
        response.setTotalRounds(gameStatistics.get());
        return response;
    }
}
