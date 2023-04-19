package com.exercise.lottoland.game.game.controller;

import com.exercise.lottoland.game.game.model.GameRequest;
import com.exercise.lottoland.game.game.model.GameResultResponse;
import com.exercise.lottoland.game.game.model.GameStatisticsResponse;
import com.exercise.lottoland.game.game.model.Player;
import com.exercise.lottoland.game.game.service.IGameSolver;
import com.exercise.lottoland.game.game.service.IGameStatisticsResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static com.exercise.lottoland.game.game.model.GameHelper.Move;
import static com.exercise.lottoland.game.game.model.GameHelper.Result;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IGameSolver gameSolver;

    @MockBean
    private IGameStatisticsResolver gameStatisticsResolver;

    @InjectMocks
    GameController gameController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameController = new GameController(mock(IGameSolver.class), gameStatisticsResolver);
    }
    @Test
    @DisplayName("Load controller")
    public void contextLoads() throws Exception {
        assertThat(gameController).isNotNull();
    }
    @Test
    @DisplayName("Player one should win")
    public void testSolveGameShouldReturnPlayerOneWins() throws Exception {

        GameRequest request = new GameRequest();
        request.setPlayer1Move(Move.PAPER);
        request.setPlayer2Move(Move.ROCK);
        String expectedWinner = Result.PLAYER_ONE.name();
        GameResultResponse expectedResponse = new GameResultResponse();
        expectedResponse.setWinner(expectedWinner);

        when(gameSolver.gameSolver(request.getPlayer1Move(), request.getPlayer2Move())).thenReturn(expectedWinner);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/game")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"player1Move\":\"PAPER\",\"player2Move\":\"ROCK\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.winner").value("PLAYER_ONE"));
    }

    @Test
    @DisplayName("Player two should win")
    public void testSolveGameShouldReturnPlayerTwoWins() throws Exception {

        GameRequest request = new GameRequest();
        request.setPlayer1Move(Move.SCISSORS);
        request.setPlayer2Move(Move.ROCK);
        String expectedWinner = Result.PLAYER_TWO.name();
        GameResultResponse expectedResponse = new GameResultResponse();
        expectedResponse.setWinner(expectedWinner);

        when(gameSolver.gameSolver(request.getPlayer1Move(), request.getPlayer2Move())).thenReturn(expectedWinner);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/game")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"player1Move\":\"SCISSORS\",\"player2Move\":\"ROCK\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.winner").value("PLAYER_TWO"));
    }

    @Test
    @DisplayName("Draw should happen")
    public void testSolveGameShouldReturnDraw() throws Exception {

        GameRequest request = new GameRequest();
        request.setPlayer1Move(Move.ROCK);
        request.setPlayer2Move(Move.ROCK);
        String expectedWinner = Result.DRAW.name();
        GameResultResponse expectedResponse = new GameResultResponse();
        expectedResponse.setWinner(expectedWinner);

        when(gameSolver.gameSolver(request.getPlayer1Move(), request.getPlayer2Move())).thenReturn(expectedWinner);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/game")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"player1Move\":\"ROCK\",\"player2Move\":\"ROCK\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.winner").value("DRAW"));
    }

    @Test
    @DisplayName("Game statistics")
    void testGameStatistics() {
        // Create test data
        Map<String, Player> players = new ConcurrentHashMap<>();
        Player playerOne = new Player();
        playerOne.setNumberOfRounds(5);
        playerOne.setTotalWins(3);
        playerOne.setTotalDraws(1);
        players.put("PLAYER_ONE", playerOne);
        Player playerTwo = new Player();
        playerTwo.setNumberOfRounds(5);
        playerTwo.setTotalWins(1);
        playerTwo.setTotalDraws(1);
        players.put("PLAYER_TWO", playerTwo);
        AtomicLong gameStatistics = new AtomicLong(4);

        GameStatisticsResponse gameStatisticsResponse = new GameStatisticsResponse();
        gameStatisticsResponse.setTotalWinsForPlayer1(3);
        gameStatisticsResponse.setTotalWinsForPlayer2(1);
        gameStatisticsResponse.setTotalDraws(1);
        gameStatisticsResponse.setTotalRounds(5);

        // Define mock behavior
        when(gameStatisticsResolver.resolve(players, gameStatistics))
                .thenReturn(gameStatisticsResponse);


        // Verify the results
        assertEquals(5, gameStatisticsResponse.getTotalRounds());
        assertEquals(gameStatisticsResponse.getTotalRounds(), players.get("PLAYER_ONE").getNumberOfRounds());
        assertEquals(gameStatisticsResponse.getTotalWinsForPlayer1(), players.get("PLAYER_ONE").getTotalWins());
        assertEquals(gameStatisticsResponse.getTotalDraws(), players.get("PLAYER_ONE").getTotalDraws());
        assertEquals(gameStatisticsResponse.getTotalRounds(), players.get("PLAYER_TWO").getNumberOfRounds());
        assertEquals(gameStatisticsResponse.getTotalWinsForPlayer2(), players.get("PLAYER_TWO").getTotalWins());
        assertEquals(gameStatisticsResponse.getTotalDraws(), players.get("PLAYER_TWO").getTotalDraws());
    }
}
