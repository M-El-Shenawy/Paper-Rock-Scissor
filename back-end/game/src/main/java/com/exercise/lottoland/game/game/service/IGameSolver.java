package com.exercise.lottoland.game.game.service;

import com.exercise.lottoland.game.game.model.GameHelper.Move;
import org.springframework.stereotype.Service;

@Service
public interface IGameSolver {
    String gameSolver(Move player1Move, Move player2Move);
}
