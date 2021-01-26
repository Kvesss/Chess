package com.example.chess.Board;

import android.net.Uri;
import android.os.Build;

import com.example.chess.figures.Pawn;
import com.example.chess.figures.Piece;
import com.example.chess.figures.Rook;
import com.example.chess.player.MoveStatus;

import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;  //if (!(o instanceof Move))
        Move move = (Move) o;
        return getPieceMoved().getPosition() == move.getPieceMoved().getPosition() &&
                getDestination() == move.getDestination() &&
                getPieceMoved().equals(move.getPieceMoved());
    }

    @Override
    public int hashCode() {
        final int oddPrime = 31;
        int value = 1;
        value = value * oddPrime + this.getDestination();
        value = value * oddPrime + this.getPieceMoved().hashCode();
        //value = value * oddPrime + this.getPieceMoved().getPosition();
        //value = value + ( ? 1 : 0);
        return value;
    }
    public boolean isAttackingMove(){
        return false;
    }

    public Piece getOccupiedPiece(){
        return null;
    }

    public boolean isCastleMove(){
        return false;
    }


    public Board executeMove() {
        final Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
        for(Piece piece : this.getBoard().getCurrentPlayer().getOpponent().getRemainingPieces()){
            boardBuilder.setPiece(piece);
        }

        for(Piece piece : this.getBoard().getCurrentPlayer().getRemainingPieces()){
            if(!piece.equals(this.getPieceMoved())){
                boardBuilder.setPiece(piece);
            }
        }

        boardBuilder.setPiece(this.getPieceMoved().move(this));
        boardBuilder.setNextMove(this.getBoard().getCurrentPlayer().getOpponent().getTeam());

        return boardBuilder.build();

    }


    public static final class MovesFactory {
        private MovesFactory(){
        }
        public static Move makeMove(final Board board, final int destination, final int currentPosition){
            for(Move move : board.getAllPossibleMoves()){
                if(move.getDestination() == destination){
                    if (move.getPieceMoved().getPosition() == currentPosition){
                        return move;
                    }
                }
            }
            return new InvalidMove();

        }


    }


    public static final class InvalidMove extends Move{

        public InvalidMove(){
            super(null, null, 64);
        }

        @Override
        public Board executeMove() {
            throw new RuntimeException();
        }
    }


    public static class AttackingMove extends Move{

        Piece occupiedPiece;

        public AttackingMove(final Board board, final Piece pieceMoved, final int destination,
                             final Piece occupiedPiece) {
            super(board, pieceMoved, destination);
            this.occupiedPiece = occupiedPiece;
        }

        @Override
        public boolean isAttackingMove() {
            return true;
        }

        @Override
        public Piece getOccupiedPiece() {
            return this.occupiedPiece;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            AttackingMove that = (AttackingMove) o;
            return super.equals(that) && getOccupiedPiece().equals(that.getOccupiedPiece());
        }

        @Override
        public int hashCode() {
            return this.occupiedPiece.hashCode() + super.hashCode();
        }

        @Override
        public Board executeMove() {

            return null;
        }
    }

    public static final class NonAttackingMove extends Move{

        public NonAttackingMove(final Board board, final Piece pieceMoved, final int destination) {
            super(board, pieceMoved, destination);
        }

    }

    public static final class PawnMove extends Move{

        public PawnMove(final Board board, final Piece pieceMoved, final int destination) {
            super(board, pieceMoved, destination);
        }

    }

    public static final class FirstPawnJump extends Move{

        public FirstPawnJump(final Board board, final Piece pieceMoved, final int destination) {
            super(board, pieceMoved, destination);
        }

        @Override
        public Board executeMove() {
            Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
            for(final Piece piece : this.getBoard().getCurrentPlayer().getRemainingPieces()){
                if(!getPieceMoved().equals(piece)){
                    boardBuilder.setPiece(piece);
                }
            }
            for (final Piece piece : this.getBoard().getCurrentPlayer().getOpponent().getRemainingPieces()){
                boardBuilder.setPiece(piece);
            }
            Pawn jumpPawn = (Pawn)getPieceMoved().move(this);
            boardBuilder.setPiece(jumpPawn);
            boardBuilder.setNextMove(this.getBoard().getCurrentPlayer().getOpponent().getTeam());
            return boardBuilder.build();

        }
    }


    public static final class AttackingPawnMove extends AttackingMove{

        public AttackingPawnMove(final Board board, final Piece pieceMoved, final int destination,
                                 final Piece occupiedPiece) {
            super(board, pieceMoved, destination, occupiedPiece);
        }

    }


    public static abstract class Castling extends Move{

        protected final Rook castlingRook;
        protected final int castlingRookDestination;

        public Rook getCastlingRook() {
            return castlingRook;
        }
        public int getCastlingRookDestination() {
            return castlingRookDestination;
        }

        public Castling(Board board, Piece pieceMoved, int destination, Rook castlingRook, int castlingRookDestination) {
            super(board, pieceMoved, destination);
            this.castlingRook = castlingRook;
            this.castlingRookDestination = castlingRookDestination;
        }

        @Override
        public boolean isCastleMove() {
            return true;
        }

        @Override
        public Board executeMove() {
            Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
            for(final Piece piece : this.getBoard().getCurrentPlayer().getRemainingPieces()){
                if(!getPieceMoved().equals(piece) && !this.castlingRook.equals(piece)){
                    boardBuilder.setPiece(piece);
                }
            }
            for(final Piece piece : this.getBoard().getCurrentPlayer().getOpponent().getRemainingPieces()){
                if(!getPieceMoved().equals(piece) && !this.castlingRook.equals(piece)){
                    boardBuilder.setPiece(piece);
                }
            }
            boardBuilder.setPiece(this.getPieceMoved().move(this));
            boardBuilder.setPiece(new Rook(this.castlingRookDestination, this.castlingRook.getTeam()));
            boardBuilder.setNextMove(this.getBoard().getCurrentPlayer().getOpponent().getTeam());
            return boardBuilder.build();
        }
    }

    public static final class FiveFieldCastling extends Castling{

        public FiveFieldCastling(Board board, Piece pieceMoved, int destination, Rook castlingRook, int castlingRookDestination) {
            super(board, pieceMoved, destination, castlingRook, castlingRookDestination);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof FiveFieldCastling)) {
                return false;
            }
            final FiveFieldCastling otherQueenSideCastleMove = (FiveFieldCastling) o;
            return super.equals(otherQueenSideCastleMove) && this.castlingRook.equals(otherQueenSideCastleMove.getCastlingRook());
        }

    }
    public static final class FourFieldCastling extends Castling{

        public FourFieldCastling(Board board, Piece pieceMoved, int destination, Rook castlingRook, int castlingRookDestination) {
            super(board, pieceMoved, destination, castlingRook, castlingRookDestination);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof FourFieldCastling)) {
                return false;
            }
            final FourFieldCastling otherKingSideCastleMove = (FourFieldCastling) o;
            return super.equals(otherKingSideCastleMove) && this.castlingRook.equals(otherKingSideCastleMove.getCastlingRook());
        }
    }

}
