package com.example.chess;

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

    public static final class AttackMove extends Move{

        Piece occupiedPiece;

        public AttackMove(final Board board,final Piece pieceMoved,final int destination,final Piece occupiedPiece) {
            super(board, pieceMoved, destination);
            this.occupiedPiece = occupiedPiece;
        }

        @Override
        public Board execute() {
            return null;
        }
    }

    public static final class EmptyMove extends Move{

        public EmptyMove(final Board board,final Piece pieceMoved,final int destination) {
            super(board, pieceMoved, destination);
        }

        @Override
        public Board execute() {
            return null;
        }
    }

}
