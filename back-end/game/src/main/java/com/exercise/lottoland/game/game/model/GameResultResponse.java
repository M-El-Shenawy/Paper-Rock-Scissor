package com.exercise.lottoland.game.game.model;

public class GameResultResponse {
    String winner;
    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return "GameResultResponse{" +
                "winner='" + winner + '\'' +
                '}';
    }
}
