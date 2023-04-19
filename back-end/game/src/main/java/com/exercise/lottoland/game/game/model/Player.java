package com.exercise.lottoland.game.game.model;

public class Player {
    long totalWins;
    long totalDraws;
    long numberOfRounds;

    public long getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(long totalWins) {
        this.totalWins = totalWins;
    }

    public long getTotalDraws() {
        return totalDraws;
    }

    public void setTotalDraws(long totalDraws) {
        this.totalDraws = totalDraws;
    }

    public long getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(long numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }
}
