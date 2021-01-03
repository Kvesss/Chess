package com.example.chess.figures;

import com.example.chess.GetMovesStrategy.GetPossibleKnightMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Team;
import com.example.chess.Board;
import com.example.chess.BoardFuntions;
import com.example.chess.Field;
import com.example.chess.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private IGetPossibleMovesStrategy getPossibleMovesStrategy;
    private final static int[] possible_moves_coefficients = { -17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(int position, Team team) {
        super(position, team, Type.KNIGHT);
        this.getPossibleMovesStrategy = new GetPossibleKnightMoves();
    }

    private static boolean isInvalidEdgeCaseOne(int position, int coordinate){
        return BoardFuntions.COLUMN_ONE[position] && ((coordinate == -17) || (coordinate == -10)
                || (coordinate == 6) || (coordinate == 15));
    }
    private static boolean isInvalidEdgeCaseTwo(int position, int coordinate){
        return BoardFuntions.COLUMN_TWO[position] && ((coordinate == -10) || (coordinate == 6));
    }
    private static boolean isInvalidEdgeCaseThree(int position, int coordinate){
        return BoardFuntions.COLUMN_SEVEN[position] && ((coordinate == -6) || (coordinate == 10));
    }
    private static boolean isInvalidEdgeCaseFour(int position, int coordinate){
        return BoardFuntions.COLUMN_EIGHT[position] && ((coordinate == -15) || (coordinate == -6)
                || (coordinate == 10) || (coordinate == 17));
    }
    public static boolean isInvalidEdge(int position, int coordinate){
        return (isInvalidEdgeCaseOne(position,coordinate) || isInvalidEdgeCaseTwo(position,coordinate)
                || isInvalidEdgeCaseThree(position,coordinate) || isInvalidEdgeCaseFour(position,coordinate));
    }

    @Override
    public List<Move> getPossibleMoves(final Board board) {
        return getPossibleMovesStrategy.getPossibleMoves(board, this);
    }

    public static int[] getPossible_moves_coefficients() {
        return possible_moves_coefficients;
    }

}
