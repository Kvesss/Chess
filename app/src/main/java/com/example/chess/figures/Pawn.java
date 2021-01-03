package com.example.chess.figures;

import com.example.chess.Board;
import com.example.chess.BoardFuntions;
import com.example.chess.GetMovesStrategy.GetPossiblePawnMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Move;
import com.example.chess.Team;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    private IGetPossibleMovesStrategy getPossibleMovesStrategy;
    private final static int[] possible_moves_coefficients = {7, 8, 9, 16};

    public Pawn(int position, Team team) {
        super(position, team, Type.PAWN);
        this.getPossibleMovesStrategy = new GetPossiblePawnMoves();
    }

    @Override
    public List<Move> getPossibleMoves(final Board board) {
        return this.getPossibleMovesStrategy.getPossibleMoves(board, this);
    }

    public static int[] getPossible_moves_coefficients() {
        return possible_moves_coefficients;
    }
}
