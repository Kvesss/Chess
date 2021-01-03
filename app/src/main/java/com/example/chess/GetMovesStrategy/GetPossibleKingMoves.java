package com.example.chess.GetMovesStrategy;

import com.example.chess.Board;
import com.example.chess.BoardFuntions;
import com.example.chess.Move;
import com.example.chess.figures.King;
import com.example.chess.figures.Piece;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class GetPossibleKingMoves implements IGetPossibleMovesStrategy {
    @Override
    public List<Move> getPossibleMoves(Board board, Piece piece) {
        List<Move> possibleMoves = new ArrayList<>();
        for(final int coefficient : King.getPossible_moves_coefficients() ){
            final int tempCoordinate = piece.getPosition() + coefficient;
            if(!King.isInvalidEdge(piece.getPosition(), coefficient)){
                if(BoardFuntions.isValidCoordinate(tempCoordinate)){
                    if(!board.getField(tempCoordinate).isOccupied()){
                        possibleMoves.add(new Move.EmptyMove(board, piece, tempCoordinate));
                    }
                    else{
                        final Piece attackedPiece = board.getField(tempCoordinate).getPiece();
                        if(piece.getTeam() != attackedPiece.getTeam()){
                            possibleMoves.add(new Move.AttackMove(board, piece, tempCoordinate, attackedPiece));
                        }
                    }
                }
            }
        }
        return ImmutableList.copyOf(possibleMoves);
    }
}
