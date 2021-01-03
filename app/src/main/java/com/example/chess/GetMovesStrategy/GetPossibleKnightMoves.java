package com.example.chess.GetMovesStrategy;

import com.example.chess.Board;
import com.example.chess.BoardFuntions;
import com.example.chess.Field;
import com.example.chess.Move;
import com.example.chess.figures.Piece;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class GetPossibleKnightMoves implements IGetPossibleMovesStrategy {

    private final static int[] possible_moves_coefficients = { -17, -15, -10, -6, 6, 10, 15, 17};

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

    private static boolean isInvalidEdge(int position, int coordinate){
        return (isInvalidEdgeCaseOne(position,coordinate) || isInvalidEdgeCaseTwo(position,coordinate)
                || isInvalidEdgeCaseThree(position,coordinate) || isInvalidEdgeCaseFour(position,coordinate));
    }

    @Override
    public List<Move> getPossibleMoves(final Board board,final Piece piece) {
        List<Move> possibleMoves = new ArrayList<>();
        for (final int coefficient: possible_moves_coefficients) {
            int destination = piece.getPosition() + coefficient;
            if(BoardFuntions.isValidCoordinate(coefficient)){
                if(!isInvalidEdge(piece.getPosition(), coefficient)){
                    final Field destinationField = board.getField(destination);
                    if(!destinationField.isOccupied()){
                        possibleMoves.add(new Move.EmptyMove(board,piece, destination ));
                    }
                    else{
                        Piece pieceAtDestination = destinationField.getPiece();
                        if(piece.getTeam() != pieceAtDestination.getTeam()){
                            possibleMoves.add(new Move.AttackMove(board, piece, destination, pieceAtDestination));
                        }
                    }
                }
            }
        }
        return ImmutableList.copyOf(possibleMoves);
    }
}
