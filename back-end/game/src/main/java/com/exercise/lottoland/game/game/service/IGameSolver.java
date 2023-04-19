package com.exercise.lottoland.game.game.service;

import org.springframework.stereotype.Service;

@Service
public interface IGameSolver {
    String gameSolver(Enum player1Move, Enum player2Move);
}
