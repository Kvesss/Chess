package com.example.chess.GetMovesStrategy;

import com.example.chess.Board.Board;
import com.example.chess.Board.BoardFuntions;
import com.example.chess.Board.Move;
import com.example.chess.Board.Team;
import com.example.chess.figures.Piece;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class GetPossiblePawnMoves implements IGetPossibleMovesStrategy {

    private final static int[] possible_moves_coefficients = {7, 8, 9, 16};

    @Override
    public List<Move> getPossibleMoves(final Board board,final Piece piece) {
        List<Move> possibleMoves = new ArrayList<>();
        for (final int coefficient : possible_moves_coefficients) {
            int tempCoordinate = piece.getPosition() + (coefficient * piece.getTeam().getDirection());
            if(BoardFuntions.isValidCoordinate(tempCoordinate)){
                if(coefficient == 8 && !board.getField(tempCoordinate).isOccupied()){
                    possibleMoves.add(new Move.NonAttackingMove(board, piece, tempCoordinate));
                }
                else if (coefficient == 16 && piece.isFirstMove() &&
                        ((int)(piece.getPosition() /8) == 6 && piece.getTeam() == Team.WHITE) ||
                        ((int)(piece.getPosition() /8) == 1 && piece.getTeam() == Team.BLACK)){
                    int doubleDestination = piece.getPosition() + (piece.getTeam().getDirection()*8);
                    if(!board.getField(doubleDestination).isOccupied() &&
                            !board.getField(tempCoordinate).isOccupied()){

                        possibleMoves.add(new Move.NonAttackingMove(board, piece, tempCoordinate));
                    }

                }
                else if (coefficient == 7){
                    if(!((BoardFuntions.COLUMN_ONE[piece.getPosition()] && piece.getTeam() == Team.BLACK) ||
                            (BoardFuntions.COLUMN_EIGHT[piece.getPosition()] && piece.getTeam() == Team.WHITE)) &&
                            board.getField(tempCoordinate).isOccupied())
                    {
                        final Piece attackedPiece = board.getField(tempCoordinate).getPiece();
                        if(piece.getTeam() != attackedPiece.getTeam()){
                            possibleMoves.add(new Move.AttackingMove(board, piece, tempCoordinate, attackedPiece));
                        }
                    }
                }
            }
            else if (coefficient == 9){
                if(!((BoardFuntions.COLUMN_ONE[piece.getPosition()] && piece.getTeam() == Team.WHITE) ||
                        (BoardFuntions.COLUMN_EIGHT[piece.getPosition()] && piece.getTeam() == Team.BLACK)) &&
                        board.getField(tempCoordinate).isOccupied())
                {
                    final Piece attackedPiece = board.getField(tempCoordinate).getPiece();
                    if(piece.getTeam() != attackedPiece.getTeam()){
                        possibleMoves.add(new Move.AttackingMove(board, piece, tempCoordinate, attackedPiece));
                    }
                }
            }
        }
        return ImmutableList.copyOf(possibleMoves);
    }
}
