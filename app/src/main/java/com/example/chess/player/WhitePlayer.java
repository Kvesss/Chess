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

public class WhitePlayer extends Player {

    public WhitePlayer(final Board board,final List<Move> possibleWhiteMoves,final List<Move> possibleBlackMoves) {
        super(board, possibleWhiteMoves, possibleBlackMoves);
    }

    @Override
    public List<Piece> getRemainingPieces() {
        return this.board.getWhiteTeam();
    }

    @Override
    public Team getTeam() {
        return Team.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.getBlackPlayer();
    }

    @Override
    public Collection<Move> getKingCastle(Collection<Move> currentMoves, Collection<Move> opponentMoves) {
        List<Move> castles = new ArrayList<>();
        if(this.playerKing.isFirstMove() && !this.isCheck){
            if(!this.board.getField(62).isOccupied() && !this.board.getField(61).isOccupied() ){
                final Field field = this.board.getField(63);
                if(field.isOccupied() && field.getPiece().isFirstMove()){
                    if((Player.getAttacksOnPosition(62, opponentMoves).size() == 0) &&
                            (Player.getAttacksOnPosition(61, opponentMoves).size() == 0) &&
                            field.getPiece().getType() == Piece.Type.ROOK){
                        castles.add(new Move.FourFieldCastling(board, playerKing, 62, (Rook)field.getPiece(), 61));
                    }
                }
            }
        }
        if(!this.board.getField(59).isOccupied() && !this.board.getField(58).isOccupied() &&
                !this.board.getField(57).isOccupied()){
            final Field field = this.board.getField(56);
            if(field.isOccupied() && field.getPiece().isFirstMove()){
                if((Player.getAttacksOnPosition(59, opponentMoves).size() == 0) &&
                        (Player.getAttacksOnPosition(58, opponentMoves).size() == 0) &&
                        (Player.getAttacksOnPosition(57, opponentMoves).size() == 0) &&
                        field.getPiece().getType() == Piece.Type.ROOK){
                    castles.add(new Move.FiveFieldCastling(board, playerKing, 58, (Rook)field.getPiece(), 59));
                }
            }
        }

        return ImmutableList.copyOf(castles);
    }
}
