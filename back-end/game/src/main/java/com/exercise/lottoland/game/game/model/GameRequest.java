package com.exercise.lottoland.game.game.model;
import static com.exercise.lottoland.game.game.model.GameHelper.Move;
public class GameRequest {
    private Move player1Move;
    private Move player2Move;


    public void setPlayer1Move(Move player1Move) {
        this.player1Move = player1Move;
    }

    public void setPlayer2Move(Move player2Move) {
        this.player2Move = player2Move;
    }


    public Move getPlayer1Move() {
        return player1Move;
    }

    public Move getPlayer2Move() {
        return player2Move;
    }

    @Override
    public String toString() {
        return "GameRequest{" +
                ", player1Move=" + player1Move +
                ", player2Move=" + player2Move +
                '}';
    }
}
