package com.example.chess.figures;

import com.example.chess.Board.Team;
import com.example.chess.Board.Board;
import com.example.chess.Board.Move;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected final Type type;
    protected final int position;
    protected final Team team;
    protected final boolean isFirstMove;
    protected final int hashCode;


    private int getHashCode() {
        return hashCode;
    }

    public Piece(final int position,final Team team,final Type type) {
        this.position = position;
        this.team = team;
        this.type = type;
        isFirstMove = true;
        hashCode = hashCode();
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
    public abstract Collection<Move> getPossibleMoves(final Board board);

    public abstract Piece move(final Move move);


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;      //if (!(o instanceof Piece))
        Piece piece = (Piece) o;
        return position == piece.position &&
                isFirstMove == piece.isFirstMove &&
                type == piece.type &&
                team == piece.team;
    }

    @Override
    public int hashCode() {
        final int oddPrime = 31;
        int value = type.hashCode();
        value = value * oddPrime + team.hashCode();
        value = value * oddPrime + position;
        value = value * oddPrime + (isFirstMove() ? 1 : 0);
        return value;

    }

    public enum Type{

        KING,
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWN
    }
}
