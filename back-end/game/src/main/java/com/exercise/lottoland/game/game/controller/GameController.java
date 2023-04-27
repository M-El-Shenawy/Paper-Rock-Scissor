package com.exercise.lottoland.game.game.controller;

import com.exercise.lottoland.game.game.model.*;
import com.exercise.lottoland.game.game.service.IGameSolver;
import com.exercise.lottoland.game.game.service.IGameStatisticsResolver;
import org.springframework.web.bind.annotation.*;
import static com.exercise.lottoland.game.game.model.GameHelper.Result.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameController {
    private final IGameSolver gameSolver;
    private final IGameStatisticsResolver gameStatisticsResolver;
    private final Map<String, Player> players = new ConcurrentHashMap<>();

    private final AtomicLong gameStatistics;

    public GameController(IGameSolver gameSolver, IGameStatisticsResolver gameStatisticsResolver) {
        this.gameSolver = gameSolver;
        this.gameStatisticsResolver = gameStatisticsResolver;
        this.gameStatistics = new AtomicLong(0);
        this.players.put(PLAYER_ONE.name(), new Player());
        this.players.put(PLAYER_TWO.name(), new Player());
    }

    @PostMapping("/game")
    public GameResultResponse solveGame(@RequestBody GameRequest request) {
        String winner = gameSolver.gameSolver(request.getPlayer1Move(), request.getPlayer2Move());
        GameResultResponse response = new GameResultResponse();
        response.setWinner(winner);
        gameStatistics.incrementAndGet();
        handlePlayedResult(winner);
        return response;
    }

    @GetMapping("/game")
    public GameStatisticsResponse gameStatistics() {
        return gameStatisticsResolver.resolve(players, gameStatistics);
    }

    private void handlePlayedResult(String winner) {
        switch (winner) {
            case "PLAYER_ONE", "PLAYER_TWO":
                players.computeIfPresent(winner, (key, player) -> {
                    player.setNumberOfRounds(player.getNumberOfRounds() + 1);
                    player.setTotalWins(player.getTotalWins() + 1);
                    return player;
                });
                break;
            default:
                players.computeIfPresent(PLAYER_ONE.name(), (key, player1) -> {
                    player1.setNumberOfRounds(player1.getNumberOfRounds() + 1);
                    player1.setTotalDraws(player1.getTotalDraws() + 1);
                    return player1;
                });
                players.computeIfPresent(PLAYER_TWO.name(), (key, player2) -> {
                    player2.setNumberOfRounds(player2.getNumberOfRounds() + 1);
                    player2.setTotalDraws(player2.getTotalDraws() + 1);
                    return player2;
                });
                break;
        }
    }
}
