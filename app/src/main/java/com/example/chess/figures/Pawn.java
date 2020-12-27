package com.example.chess.figures;

import com.example.chess.Board;
import com.example.chess.Move;
import com.example.chess.Team;

import java.util.List;

public class Pawn extends Piece{
    public Pawn(int position, Team team) {
        super(position, team);
    }

    @Override
    public List<Move> getPossibleMoves(Board board) {
        return null;
    }
}
