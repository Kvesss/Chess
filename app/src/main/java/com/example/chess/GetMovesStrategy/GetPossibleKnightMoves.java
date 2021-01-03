package com.example.chess.GetMovesStrategy;

import com.example.chess.Board;
import com.example.chess.BoardFuntions;
import com.example.chess.Field;
import com.example.chess.Move;
import com.example.chess.figures.Knight;
import com.example.chess.figures.Piece;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class GetPossibleKnightMoves implements IGetPossibleMovesStrategy {
    @Override
    public List<Move> getPossibleMoves(final Board board, Piece piece) {
        List<Move> possibleMoves = new ArrayList<>();
        for (int move: Knight.getPossible_moves_coefficients()) {
            int destination = piece.getPosition() + move;
            if(BoardFuntions.isValidCoordinate(move)){
                if(!Knight.isInvalidEdge(piece.getPosition(), move)){
                    Field destinationField = board.getField(destination);
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
