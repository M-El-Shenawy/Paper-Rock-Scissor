package com.exercise.lottoland.game.game.service.impl;

import com.exercise.lottoland.game.game.service.IGameSolver;
import org.springframework.stereotype.Service;

import static com.exercise.lottoland.game.game.model.GameHelper.Move;
import static com.exercise.lottoland.game.game.model.GameHelper.Result;

@Service
public class IGameSolverImpl implements IGameSolver {
    @Override
    public String gameSolver(Enum player1Move, Enum player2Move) {
            if(player1Move.equals(player2Move)) return Result.DRAW.name();
            return isPlayerWin(player1Move.name(),player2Move.name()) ? Result.PLAYER_ONE.name():Result.PLAYER_TWO.name();
    }
    private boolean isPlayerWin(String playerMove, String computerMove) {
        return playerMove.equals(Move.ROCK.name()) && computerMove.equals(Move.SCISSORS.name())
                || (playerMove.equals(Move.SCISSORS.name()) && computerMove.equals(Move.PAPER.name()))
                || (playerMove.equals(Move.PAPER.name()) && computerMove.equals(Move.ROCK.name()));
    }
}
