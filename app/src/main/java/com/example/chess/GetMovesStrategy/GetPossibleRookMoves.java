package com.example.chess.GetMovesStrategy;

import com.example.chess.Board.Board;
import com.example.chess.Board.BoardFuntions;
import com.example.chess.Board.Field;
import com.example.chess.Board.Move;
import com.example.chess.figures.Piece;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GetPossibleRookMoves implements IGetPossibleMovesStrategy {

    private final static int[] possible_moves_coefficients = { -8, -1, 1, 8};

    private static boolean isInvalidEdgeCaseOne(int position, int coordinate){
        return BoardFuntions.COLUMN_ONE[position] && (coordinate == -1);
    }

    private static boolean isInvalidEdgeCaseEight(int position, int coordinate){
        return BoardFuntions.COLUMN_EIGHT[position] && (coordinate == -1);
    }

    private static boolean isInvalidEdge(int position, int coordinate){
        return isInvalidEdgeCaseOne(position,coordinate) || isInvalidEdgeCaseEight(position, coordinate);
    }

    @Override
    public Collection<Move> getPossibleMoves(final Board board, final Piece piece) {
        List<Move> possibleMoves = new ArrayList<>();
        for (final int coefficient: possible_moves_coefficients) {
            int tempCoordinate = piece.getPosition();
            while(BoardFuntions.isValidCoordinate(tempCoordinate)){
                if (!isInvalidEdge(tempCoordinate, coefficient)) {
                    tempCoordinate += coefficient;
                    if (BoardFuntions.isValidCoordinate(tempCoordinate)) {
                        final Field destinationField = board.getField(tempCoordinate);
                        if (!destinationField.isOccupied()) {
                            possibleMoves.add(new Move.NonAttackingMove(board, piece, tempCoordinate));
                        } else {
                            final Piece pieceAtDestination = destinationField.getPiece();
                            if (piece.getTeam() != pieceAtDestination.getTeam()) {
                                possibleMoves.add(new Move.AttackingMove(board, piece, tempCoordinate, pieceAtDestination));
                            }
                            break;
                        }
                    }
                }
            }
        }
        return ImmutableList.copyOf(possibleMoves);
    }
}
