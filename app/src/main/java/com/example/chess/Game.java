package com.example.chess;

import java.time.LocalDateTime;

public class Game {

    private final int id;
    private final String players;
    private final String winner;
    private final String dateTime;

    public Game(int id, String players, String winner, String dateTime) {
        this.id = id;
        this.players = players;
        this.winner = winner;
        this.dateTime = dateTime;
    }

    public String getPlayers() {
        return players;
    }

    public String getWinner() {
        return winner;
    }

    public int getId() {
        return id;
    }

    public String getDateTime() {
        return dateTime;
    }
}
