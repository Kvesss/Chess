package com.example.chess.figures;

import com.example.chess.Board.Team;
import com.example.chess.Board.Board;
import com.example.chess.Board.Move;

import java.util.List;

public abstract class Piece {

    protected final Type type;
    protected final int position;
    protected final Team team;
    protected final boolean isFirstMove;

    public Piece(final int position,final Team team,final Type type) {
        this.position = position;
        this.team = team;
        this.type = type;
        isFirstMove = true;
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
    public boolean isFirstMove() {
        return this.isFirstMove;
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
