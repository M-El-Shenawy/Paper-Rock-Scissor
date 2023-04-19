package com.exercise.lottoland.game.game.service.impl;

import com.exercise.lottoland.game.game.service.IGameSolver;
import org.springframework.stereotype.Service;

import static com.exercise.lottoland.game.game.service.model.GameHelper.Move;
import static com.exercise.lottoland.game.game.service.model.GameHelper.Result;

@Service
public class IGameSolverImpl implements IGameSolver {
    @Override
    public String gameSolver(Enum player1Move, Enum player2Move) {
        if (player1Move.ordinal() == Move.Paper.ordinal() &&
                player2Move.ordinal() == Move.Rock.ordinal()) {
            return Result.PLAYER_ONE.name();}
        else if (player1Move.ordinal() == Move.Scissors.ordinal() &&
                player2Move.ordinal() == Move.Rock.ordinal()) {
            return Result.PLAYER_TWO.name();
        }
            return Result.DRAW.name();
    }
}
