package com.exercise.lottoland.game.game.service.impl;

import static com.exercise.lottoland.game.game.service.model.GameHelper.Move;
import static com.exercise.lottoland.game.game.service.model.GameHelper.Result;

import com.exercise.lottoland.game.game.service.IGameSolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IGameSolverImplTest {
    private IGameSolver gameSolver;

    @BeforeEach
    void setUp(){
        gameSolver = new IGameSolverImpl();
    }

    @Test
    @DisplayName("Player1 should when win when he playing paper and the player2 plays rock")
    public void itShouldReturnPlayer1isWinning(){

        String isWinning = gameSolver.gameSolver(Move.PAPER,
                Move.ROCK);
        // if player1 is winning return 0 else return 1
        String expectedResult = Result.PLAYER_ONE.name();
        assertEquals(expectedResult,isWinning);
    }
    @Test
    @DisplayName("Player2 should when win when he playing rock and the player1 plays Scissors")
    public void itShouldReturnPlayer2isWinning(){

        String isWinning = gameSolver.gameSolver(Move.SCISSORS,
                Move.ROCK);
        // if player1 is winning return 0 else return 1
        String expectedResult = Result.PLAYER_TWO.name();
        assertEquals(expectedResult,isWinning);
    }
    @Test
    @DisplayName("Draw if two players play rock")
    public void itShouldReturnDraw(){

        String isWinning = gameSolver.gameSolver(Move.ROCK,
                Move.ROCK);
        // if player1 is winning return 0 else return 1
        String expectedResult = Result.DRAW.name();
        assertEquals(expectedResult,isWinning);
    }
}
