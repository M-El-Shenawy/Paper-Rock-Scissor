package com.exercise.lottoland.game.game.service.impl;

import com.exercise.lottoland.game.game.model.GameHelper;
import com.exercise.lottoland.game.game.model.GameStatisticsResponse;
import com.exercise.lottoland.game.game.model.Player;
import com.exercise.lottoland.game.game.service.IGameStatisticsResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IGameStatisticsResolverImplTest {

    private IGameStatisticsResolverImpl gameStatisticsResolver;

    @BeforeEach
    public void setUp() {
        this.gameStatisticsResolver = new IGameStatisticsResolverImpl();
    }

    @Test
    @DisplayName("Game statistics")
    public void testResolve() {
        // Setup
        Map<String, Player> players = new HashMap<>();
        Player player1 = new Player();
        player1.setTotalWins(3);
        players.put(GameHelper.Result.PLAYER_ONE.name(), player1);

        Player player2 = new Player();
        player2.setTotalWins(2);
        player2.setTotalDraws(1);
        players.put(GameHelper.Result.PLAYER_TWO.name(), player2);

        AtomicLong gameStatistics = new AtomicLong(5);

        GameStatisticsResponse gameStatisticsResponse = new GameStatisticsResponse();
        gameStatisticsResponse.setTotalWinsForPlayer1(3);
        gameStatisticsResponse.setTotalWinsForPlayer2(2);
        gameStatisticsResponse.setTotalDraws(1);
        gameStatisticsResponse.setTotalRounds(5);

        // Mock
        IGameStatisticsResolver gameStatisticsResolver = mock(IGameStatisticsResolver.class);
        when(gameStatisticsResolver.resolve(players, gameStatistics)).thenReturn(gameStatisticsResponse);

        // Execution
        GameStatisticsResponse response = gameStatisticsResolver.resolve(players, gameStatistics);

        // Verification
        assertEquals(3, response.getTotalWinsForPlayer1());
        assertEquals(2, response.getTotalWinsForPlayer2());
        assertEquals(1, response.getTotalDraws());
        assertEquals(5, response.getTotalRounds());
    }
}

