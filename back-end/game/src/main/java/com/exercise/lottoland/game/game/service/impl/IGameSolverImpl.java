package com.exercise.lottoland.game.game.service.impl;

import com.exercise.lottoland.game.game.service.IGameSolver;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

import static com.exercise.lottoland.game.game.model.GameHelper.Move;
import static com.exercise.lottoland.game.game.model.GameHelper.Result;

@Service
public class IGameSolverImpl implements IGameSolver {
    @Override
    public String gameSolver(Move player1Move, Move player2Move) {
        if (player1Move == player2Move) {
            return Result.DRAW.name();
        }
        return isPlayerWin(player1Move, player2Move) ? Result.PLAYER_ONE.name() : Result.PLAYER_TWO.name();
    }

    private boolean isPlayerWin(Move playerMove, Move computerMove) {
        List<BiPredicate<Move, Move>> winningConditions = Arrays.asList(
                (p, c) -> p == Move.ROCK && c == Move.SCISSORS,
                (p, c) -> p == Move.SCISSORS && c == Move.PAPER,
                (p, c) -> p == Move.PAPER && c == Move.ROCK
        );
        return winningConditions.stream().anyMatch(condition -> condition.test(playerMove, computerMove));
    }
}
