package com.example.chess.GetMovesStrategy;

import com.example.chess.Board;
import com.example.chess.BoardFuntions;
import com.example.chess.Field;
import com.example.chess.Move;
import com.example.chess.figures.Piece;
import com.example.chess.figures.Queen;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class GetPossibleQueenMoves implements IGetPossibleMovesStrategy {
    @Override
    public List<Move> getPossibleMoves(Board board, Piece piece) {
        List<Move> possibleMoves = new ArrayList<>();
        for (int coefficient : Queen.getPossible_moves_coefficients()) {
            int tempCoordinate = piece.getPosition();
            while(BoardFuntions.isValidCoordinate(tempCoordinate)){
                if(!Queen.isInvalidEdge(tempCoordinate, coefficient))
                    tempCoordinate += coefficient;
                if(BoardFuntions.isValidCoordinate(tempCoordinate)){
                    Field destinationField = board.getField(tempCoordinate);
                    if(!destinationField.isOccupied()){
                        possibleMoves.add(new Move.EmptyMove(board, piece, tempCoordinate));
                    }
                    else {
                        Piece pieceAtDestination = destinationField.getPiece();
                        if(piece.getTeam()!=pieceAtDestination.getTeam()){
                            possibleMoves.add(new Move.AttackMove(board, piece, tempCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }

            }
        }
        return ImmutableList.copyOf(possibleMoves);
    }
}
