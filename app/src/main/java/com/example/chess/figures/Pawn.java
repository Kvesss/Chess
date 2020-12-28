package com.example.chess.figures;

import com.example.chess.Board;
import com.example.chess.BoardFuntions;
import com.example.chess.Move;
import com.example.chess.Team;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] possible_moves_coefficients = {7, 8, 9, 16};
    private final boolean isFirstMove;

    public Pawn(int position, Team team) {
        super(position, team);
        isFirstMove = true;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    @Override
    public List<Move> getPossibleMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();
        for (int coefficient : possible_moves_coefficients) {
            int tempCoordinate = this.position + (coefficient * this.getTeam().getDirection());
            if(BoardFuntions.isValidCoordinate(tempCoordinate)){
                if(coefficient == 8 && !board.getField(tempCoordinate).isOccupied()){
                    possibleMoves.add(new Move.EmptyMove(board, this, tempCoordinate));
                }
                else if (coefficient == 16 && isFirstMove() &&
                        ((int)(this.position /8) == 6 && this.getTeam() == Team.WHITE) ||
                        ((int)(this.position /8) == 1 && this.getTeam() == Team.BLACK)){
                    int doubleDestination = this.position + (this.getTeam().getDirection()*8);
                    if(!board.getField(doubleDestination).isOccupied() &&
                       !board.getField(tempCoordinate).isOccupied()){

                        possibleMoves.add(new Move.EmptyMove(board, this, tempCoordinate));
                    }

                }
                else if (coefficient == 7){
                    if(!((BoardFuntions.COLUMN_ONE[this.position] && this.getTeam() == Team.BLACK) ||
                            (BoardFuntions.COLUMN_EIGHT[this.position] && this.getTeam() == Team.WHITE)) &&
                            board.getField(tempCoordinate).isOccupied())
                    {
                        final Piece attackedPiece = board.getField(tempCoordinate).getPiece();
                        if(this.getTeam() != attackedPiece.getTeam()){
                            possibleMoves.add(new Move.AttackMove(board, this, tempCoordinate, attackedPiece));
                        }
                    }
                }
            }
            else if (coefficient == 9){
                if(!((BoardFuntions.COLUMN_ONE[this.position] && this.getTeam() == Team.WHITE) ||
                        (BoardFuntions.COLUMN_EIGHT[this.position] && this.getTeam() == Team.BLACK)) &&
                        board.getField(tempCoordinate).isOccupied())
                {
                    final Piece attackedPiece = board.getField(tempCoordinate).getPiece();
                    if(this.getTeam() != attackedPiece.getTeam()){
                        possibleMoves.add(new Move.AttackMove(board, this, tempCoordinate, attackedPiece));
                    }
                }
            }
        }
        return ImmutableList.copyOf(possibleMoves);
    }


}
