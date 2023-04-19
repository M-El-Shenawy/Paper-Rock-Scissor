package com.exercise.lottoland.game.game.model;

public class GameStatisticsResponse {
    long totalRounds = 0;
    long totalWinsForPlayer1 = 0;
    long totalWinsForPlayer2 = 0;
    long totalDraws = 0;

    public void setTotalRounds(long totalRounds) {
        this.totalRounds = totalRounds;
    }

    public void setTotalWinsForPlayer1(long totalWinsForPlayer1) {
        this.totalWinsForPlayer1 = totalWinsForPlayer1;
    }

    public void setTotalWinsForPlayer2(long totalWinsForPlayer2) {
        this.totalWinsForPlayer2 = totalWinsForPlayer2;
    }

    public void setTotalDraws(long totalDraws) {
        this.totalDraws = totalDraws;
    }

    public long getTotalRounds() {
        return totalRounds;
    }

    public long getTotalWinsForPlayer1() {
        return totalWinsForPlayer1;
    }

    public long getTotalWinsForPlayer2() {
        return totalWinsForPlayer2;
    }

    public long getTotalDraws() {
        return totalDraws;
    }

    @Override
    public String toString() {
        return "GameStatisticsResponse{" +
                "totalRounds=" + totalRounds +
                ", totalWinsForPlayer1=" + totalWinsForPlayer1 +
                ", totalWinsForPlayer2=" + totalWinsForPlayer2 +
                ", totalDraws=" + totalDraws +
                '}';
    }
}
