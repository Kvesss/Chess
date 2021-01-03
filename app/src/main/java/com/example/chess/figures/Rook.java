package com.example.chess.figures;

import com.example.chess.BoardFuntions;
import com.example.chess.Field;
import com.example.chess.GetMovesStrategy.GetPossibleRookMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Team;
import com.example.chess.Board;
import com.example.chess.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    private IGetPossibleMovesStrategy getPossibleMovesStrategy;
    private final static int[] possible_moves_coefficients = { -8, -1, 1, 8};

    public Rook(int position, Team team) {
        super(position, team, Type.ROOK);
        this.getPossibleMovesStrategy = new GetPossibleRookMoves();
    }

    private static boolean isInvalidEdgeCaseOne(int position, int coordinate){
        return BoardFuntions.COLUMN_ONE[position] && (coordinate == -1);
    }
    private static boolean isInvalidEdgeCaseEight(int position, int coordinate){
        return BoardFuntions.COLUMN_EIGHT[position] && (coordinate == -1);
    }
    public static boolean isInvalidEdge(int position, int coordinate){
        return isInvalidEdgeCaseOne(position,coordinate) || isInvalidEdgeCaseEight(position, coordinate);
    }

    @Override
    public List<Move> getPossibleMoves(final Board board) {
        return this.getPossibleMovesStrategy.getPossibleMoves(board, this);
    }
    public static int[] getPossible_moves_coefficients() {
        return possible_moves_coefficients;
    }
}
