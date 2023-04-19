package com.exercise.lottoland.game.game.service.model;

public class GameHelper {
    public enum Move {
        Paper,Scissors,Rock
    }

    public enum Result {
        PLAYER_ONE("player1"),
        PLAYER_TWO("player2"),
        DRAW("draw");
        private final String value;

        Result(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }
}
