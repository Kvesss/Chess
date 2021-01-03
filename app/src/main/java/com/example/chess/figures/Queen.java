package com.example.chess.figures;

import com.example.chess.Board;
import com.example.chess.BoardFuntions;
import com.example.chess.Field;
import com.example.chess.GetMovesStrategy.GetPossibleQueenMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Move;
import com.example.chess.Team;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{

    private IGetPossibleMovesStrategy getPossibleMovesStrategy;
    private final static int[] possible_moves_coefficients = { -9, -8, -7, -1, 1, 7, 8, 9};

    public Queen(int position, Team team) {
        super(position, team, Type.QUEEN);
        this.getPossibleMovesStrategy = new GetPossibleQueenMoves();
    }

    private static boolean isInvalidEdgeCaseOne(int position, int coordinate){
        return BoardFuntions.COLUMN_ONE[position] && ((coordinate == -9) || (coordinate == 7) || (coordinate == -1));
    }
    private static boolean isInvalidEdgeCaseEight(int position, int coordinate){
        return BoardFuntions.COLUMN_EIGHT[position] && ((coordinate == -7) || (coordinate == 9) || (coordinate == 1));
    }
    public static boolean isInvalidEdge(int position, int coordinate){
        return isInvalidEdgeCaseOne(position,coordinate) || isInvalidEdgeCaseEight(position, coordinate);
    }

    @Override
    public List<Move> getPossibleMoves(Board board) {
        return this.getPossibleMovesStrategy.getPossibleMoves(board, this);
    }

    public static int[] getPossible_moves_coefficients() {
        return possible_moves_coefficients;
    }
}
