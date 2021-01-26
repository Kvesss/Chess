package com.example.chess.player;

import com.example.chess.Board.Board;
import com.example.chess.Board.Field;
import com.example.chess.Board.Move;
import com.example.chess.Board.Team;
import com.example.chess.figures.Piece;
import com.example.chess.figures.Rook;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlackPlayer extends Player {

    public BlackPlayer(final Board board,final List<Move> possibleWhiteMoves,final List<Move> possibleBlackMoves) {
        super(board, possibleWhiteMoves, possibleBlackMoves);
    }

    @Override
    public List<Piece> getRemainingPieces() {
        return this.board.getBlackTeam();
    }

    @Override
    public Team getTeam() {
        return Team.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.getWhitePlayer();
    }

    @Override
    public Collection<Move> getKingCastle(Collection<Move> currentMoves, Collection<Move> opponentMoves) {
        List<Move> castles = new ArrayList<>();
        if(this.playerKing.isFirstMove() && !this.isCheck){
            if(!this.board.getField(5).isOccupied() && !this.board.getField(6).isOccupied() ){
                final Field field = this.board.getField(7);
                if(field.isOccupied() && field.getPiece().isFirstMove()){
                    if((Player.getAttacksOnPosition(5, opponentMoves).size() == 0) &&
                            (Player.getAttacksOnPosition(6, opponentMoves).size() == 0) &&
                            field.getPiece().getType() == Piece.Type.ROOK){
                        castles.add(new Move.FourFieldCastling(board, playerKing, 6, (Rook)field.getPiece(), 5));
                    }
                }
            }
        }
        if(!this.board.getField(1).isOccupied() && !this.board.getField(2).isOccupied() &&
                !this.board.getField(3).isOccupied()){
            final Field field = this.board.getField(0);
            if(field.isOccupied() && field.getPiece().isFirstMove()){
                if((Player.getAttacksOnPosition(1, opponentMoves).size() == 0) &&
                        (Player.getAttacksOnPosition(2, opponentMoves).size() == 0) &&
                        (Player.getAttacksOnPosition(3, opponentMoves).size() == 0) &&
                        field.getPiece().getType() == Piece.Type.ROOK){
                    castles.add(new Move.FiveFieldCastling(board, playerKing, 2, (Rook)field.getPiece(), 3));
                }
            }
        }

        return ImmutableList.copyOf(castles);
    }
}
