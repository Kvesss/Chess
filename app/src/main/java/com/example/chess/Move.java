package com.example.chess;

import figures.Piece;

public abstract class Move {

    private final Board board;
    private final Piece pieceMoved;
    private final int destination;

    public Move(Board board, Piece pieceMoved, int destination) {
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

    public static final class AttackMove extends Move{

        Piece occupiedPiece;

        public AttackMove(Board board, Piece pieceMoved, int destination, Piece occupiedPiece) {
            super(board, pieceMoved, destination);
            this.occupiedPiece = occupiedPiece;
        }
    }

    public static final class EmptyMove extends Move{

        public EmptyMove(Board board, Piece pieceMoved, int destination) {
            super(board, pieceMoved, destination);
        }
    }

}
