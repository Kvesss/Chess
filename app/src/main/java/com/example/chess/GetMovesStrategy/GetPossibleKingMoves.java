package com.example.chess.GetMovesStrategy;

import com.example.chess.Board.Board;
import com.example.chess.Board.BoardFuntions;
import com.example.chess.Board.Move;
import com.example.chess.figures.Piece;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GetPossibleKingMoves implements IGetPossibleMovesStrategy {
    private final static int[] possible_moves_coefficients = { -9, -8, -7, -1, 1, 7, 8, 9};

    private static boolean isInvalidEdgeCaseOne(int position, int coordinate){
        return BoardFuntions.COLUMN_ONE[position] && ((coordinate == -9) || (coordinate == 7) || (coordinate == -1));
    }

    private static boolean isInvalidEdgeCaseEight(int position, int coordinate){
        return BoardFuntions.COLUMN_EIGHT[position] && ((coordinate == -7) || (coordinate == 9) || (coordinate == 1));
    }

    private static boolean isInvalidEdge(int position, int coordinate){
        return isInvalidEdgeCaseOne(position,coordinate) || isInvalidEdgeCaseEight(position, coordinate);
    }

    @Override
    public Collection<Move> getPossibleMoves(final Board board, final Piece piece) {
        List<Move> possibleMoves = new ArrayList<>();
        for(final int coefficient : possible_moves_coefficients ){
            final int tempCoordinate = piece.getPosition() + coefficient;
            if(!isInvalidEdge(piece.getPosition(), coefficient)){
                if(BoardFuntions.isValidCoordinate(tempCoordinate)){
                    if(!board.getField(tempCoordinate).isOccupied()){
                        possibleMoves.add(new Move.NonAttackingMove(board, piece, tempCoordinate));
                    }
                    else{
                        final Piece attackedPiece = board.getField(tempCoordinate).getPiece();
                        if(piece.getTeam() != attackedPiece.getTeam()){
                            possibleMoves.add(new Move.AttackingMove(board, piece, tempCoordinate, attackedPiece));
                        }
                    }
                }
            }
        }
        return ImmutableList.copyOf(possibleMoves);
    }
}
