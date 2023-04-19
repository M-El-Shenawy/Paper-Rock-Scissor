package com.exercise.lottoland.game.game.service;

import com.exercise.lottoland.game.game.service.model.GameHelper;
import org.springframework.stereotype.Service;

@Service
public interface IGameSolver {
    String gameSolver(Enum player1Move, Enum player2Move);
}
