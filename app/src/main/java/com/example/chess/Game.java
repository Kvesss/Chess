package com.example.chess;

public class Game {

    private final String players;
    private final String winner;

    public Game(String players, String winner) {
        this.players = players;
        this.winner = winner;
    }

    public String getPlayers() {
        return players;
    }

    public String getWinner() {
        return winner;
    }
}
