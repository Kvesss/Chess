package com.example.chess.figures;

import com.example.chess.Team;
import com.example.chess.Board;
import com.example.chess.Move;

import java.util.List;

public abstract class Piece {   //Abstraction of a figure

    protected final int position;
    protected final Team team;

    public Piece(final int position,final Team team) {
        this.position = position;
        this.team = team;
    }

    public int getPosition() {
        return position;
    }
    public Team getTeam() {
        return team;
    }

    public abstract List<Move> getPossibleMoves(final Board board);
}
