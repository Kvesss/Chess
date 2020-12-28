package com.example.chess.figures;

import com.example.chess.Board;
import com.example.chess.BoardFuntions;
import com.example.chess.Field;
import com.example.chess.Move;
import com.example.chess.Team;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    private final static int[] possible_moves_coefficients = { -9, -8, -7, -1, 1, 7, 8, 9};

    public King(int position, Team team) {
        super(position, team);
    }

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
    public List<Move> getPossibleMoves(Board board) {

        List<Move> possibleMoves = new ArrayList<>();
        for(final int coefficient : possible_moves_coefficients ){
            final int tempCoordinate = this.position + coefficient;
            if(!isInvalidEdge(this.position, coefficient)){
                if(BoardFuntions.isValidCoordinate(tempCoordinate)){
                    if(!board.getField(tempCoordinate).isOccupied()){
                        possibleMoves.add(new Move.EmptyMove(board, this, tempCoordinate));
                    }
                    else{

                        final Piece attackedPiece = board.getField(tempCoordinate).getPiece();
                        if(this.getTeam() != attackedPiece.getTeam()){
                            possibleMoves.add(new Move.AttackMove(board, this, tempCoordinate, attackedPiece));
                        }
                    }
                }
            }
        }


        return ImmutableList.copyOf(possibleMoves);
    }
}
