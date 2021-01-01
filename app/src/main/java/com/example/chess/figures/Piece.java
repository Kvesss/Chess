package com.example.chess.figures;

import com.example.chess.Team;
import com.example.chess.Board;
import com.example.chess.Move;

import java.util.List;

public abstract class Piece {

    protected final Type type;
    protected final int position;
    protected final Team team;


    public Piece(final int position,final Team team, Type type) {
        this.position = position;
        this.team = team;
        this.type = type;
    }

    public Type getType() {
        return type;
    }
    public int getPosition() {
        return position;
    }
    public Team getTeam() {
        return team;
    }

    public abstract List<Move> getPossibleMoves(final Board board);

    public enum Type{

        KING,
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWN
    }
}
