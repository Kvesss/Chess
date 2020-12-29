package com.example.chess;

import com.example.chess.figures.Bishop;
import com.example.chess.figures.King;
import com.example.chess.figures.Knight;
import com.example.chess.figures.Pawn;
import com.example.chess.figures.Piece;
import com.example.chess.figures.Queen;
import com.example.chess.figures.Rook;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {

    private final List<Field> board;
    private final List<Piece> whiteTeam;
    private final List<Piece> blackTeam;
    private final List<Move> possibleWhiteMoves;
    private final List<Move> possibleBlackMoves;


    private Board(BoardBuilder board) {
        this.board = createBoard(board);
        whiteTeam = extractTeam(Team.WHITE);
        blackTeam = extractTeam(Team.BLACK);
        possibleWhiteMoves = getPossibleMoves(whiteTeam);
        possibleBlackMoves = getPossibleMoves(blackTeam);
    }

    private List<Piece> extractTeam(Team team) {
            List<Piece> chessTeam = new ArrayList<>();
            for (Field field:board) {
                if(field.isOccupied()){
                    if(field.getPiece().getTeam() == team){
                        chessTeam.add(field.getPiece());
                    }
                }
            }
            return ImmutableList.copyOf(chessTeam);
        }


    private List<Move> getPossibleMoves(List<Piece> team) {
        List<Move> allPossibleMoves = new ArrayList<>();
        for (Piece piece : team) {
            allPossibleMoves.addAll(piece.getPossibleMoves(this));
        }
        return ImmutableList.copyOf(allPossibleMoves);
    }

//    private List<Piece> extractBlackTeam() {
//        List<Piece> blackTeam = new ArrayList<>();
//        for (Field field:board) {
//            if(field.isOccupied()){
//                if(field.getPiece().getTeam() == Team.BLACK){
//                    blackTeam.add(field.getPiece());
//                }
//            }
//        }
//        return ImmutableList.copyOf(blackTeam);
//    }
//
//    private List<Piece> extractWhiteTeam() {
//        List<Piece> whiteTeam = new ArrayList<>();
//        for (Field field:board) {
//            if(field.isOccupied()){
//                if(field.getPiece().getTeam() == Team.WHITE){
//                    whiteTeam.add(field.getPiece());
//                }
//            }
//        }
//        return ImmutableList.copyOf(whiteTeam);
//    }

    public Field getField(int position){
        return board.get(position);
    }

    private static List<Field> createBoard(BoardBuilder boardBuilder) {
        List<Field> chessField = new ArrayList<>(64);
        for (int i=0; i< 64; i++) {
            chessField.set(i, Field.createField(i, boardBuilder.boardBlueprint.get(i)));

        }
        return ImmutableList.copyOf(chessField);
    }

    private static Board initiateOpeningBoard(){
        BoardBuilder builder = new BoardBuilder();
        builder.setPiece(new Rook(0, Team.BLACK));
        builder.setPiece(new Knight(1, Team.BLACK));
        builder.setPiece(new Bishop(2, Team.BLACK));
        builder.setPiece(new Queen(3, Team.BLACK));
        builder.setPiece(new King(4, Team.BLACK));
        builder.setPiece(new Bishop(5, Team.BLACK));
        builder.setPiece(new Knight(6, Team.BLACK));
        builder.setPiece(new Rook(7, Team.BLACK));
        builder.setPiece(new Pawn(8, Team.BLACK));
        builder.setPiece(new Pawn(9, Team.BLACK));
        builder.setPiece(new Pawn(10, Team.BLACK));
        builder.setPiece(new Pawn(11, Team.BLACK));
        builder.setPiece(new Pawn(12, Team.BLACK));
        builder.setPiece(new Pawn(13, Team.BLACK));
        builder.setPiece(new Pawn(14, Team.BLACK));
        builder.setPiece(new Pawn(15, Team.BLACK));

        builder.setPiece(new Pawn(48, Team.WHITE));
        builder.setPiece(new Pawn(49, Team.WHITE));
        builder.setPiece(new Pawn(50, Team.WHITE));
        builder.setPiece(new Pawn(51, Team.WHITE));
        builder.setPiece(new Pawn(52, Team.WHITE));
        builder.setPiece(new Pawn(53, Team.WHITE));
        builder.setPiece(new Pawn(54, Team.WHITE));
        builder.setPiece(new Pawn(55, Team.WHITE));
        builder.setPiece(new Rook(56, Team.WHITE));
        builder.setPiece(new Knight(57, Team.WHITE));
        builder.setPiece(new Bishop(58, Team.WHITE));
        builder.setPiece(new Queen(59, Team.WHITE));
        builder.setPiece(new King(60, Team.WHITE));
        builder.setPiece(new Bishop(61, Team.WHITE));
        builder.setPiece(new Knight(62, Team.WHITE));
        builder.setPiece(new Rook(63, Team.WHITE));

        builder.setNextMove(Team.WHITE);

        return builder.build();
    }



    private static class BoardBuilder{
        private Map<Integer, Piece> boardBlueprint;
        private Team nextMove;

        public BoardBuilder() {
        }

//        public BoardBuilder(Map<Integer, Piece> boardBlueprint, Team nextMove) {
//            this.boardBlueprint = boardBlueprint;
//            this.nextMove = nextMove;
//        }


        public BoardBuilder setNextMove(Team nextMove) {
            this.nextMove = nextMove;
            return this;
        }

        public BoardBuilder setPiece(final Piece piece){
            boardBlueprint.put(piece.getPosition(), piece);
            return this;
        }

        public Board build(){
            return new Board(this);
        }



    }


}
