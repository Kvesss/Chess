package com.example.chess.Board;

import android.net.Uri;

import com.example.chess.figures.Piece;

public abstract class Move {

    private final Board board;
    private final Piece pieceMoved;
    private final int destination;

    public Move(final Board board,final Piece pieceMoved,final int destination) {
        this.board = board;
        this.pieceMoved = pieceMoved;
        this.destination = destination;
    }

    public Board getBoard() {
        return board;
    }
    public Piece getPieceMoved() {
        return pieceMoved;
    }
    public int getDestination() {
        return destination;
    }

    public abstract Board execute();            //TODO TODO TODO

    public static final class AttackingMove extends Move{

        Piece occupiedPiece;

        public AttackingMove(final Board board, final Piece pieceMoved, final int destination, final Piece occupiedPiece) {
            super(board, pieceMoved, destination);
            this.occupiedPiece = occupiedPiece;
        }

        @Override
        public Board execute() {


            return null;
        }
    }

    public static final class NonAttackingMove extends Move{

        public NonAttackingMove(final Board board, final Piece pieceMoved, final int destination) {
            super(board, pieceMoved, destination);
        }

        @Override
        public Board execute() {
            final Board.BoardBuilder builder = new Board.BoardBuilder();
            for(Piece piece : this.getBoard().getCurrentPlayer().getOpponent().getRemainingPieces()){
                builder.setPiece(piece);
            }

            for(Piece piece : this.getBoard().getCurrentPlayer().getRemainingPieces()){
                if(piece != this.getPieceMoved()){
                    builder.setPiece(piece);
                }
            }

            //builder.setPiece(); TODO
            builder.setNextMove(this.getBoard().getCurrentPlayer().getOpponent().getTeam());


            return builder.build();

        }
    }

}
