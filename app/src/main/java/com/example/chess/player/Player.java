package com.example.chess.player;


import com.example.chess.Board;
import com.example.chess.Move;
import com.example.chess.Team;
import com.example.chess.figures.King;
import com.example.chess.figures.Piece;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected final Board board;
    protected final King playerKing;
    protected final List<Move> possibleMoves;
    protected final boolean isCheck;

    public Player(final Board board, final List<Move> possibleMoves, final List<Move> enemyMoves){
        this.board = board;
        this.playerKing = initiateKing();
        this.possibleMoves = possibleMoves;
        this.isCheck = !Player.getAttacksOnPosition(this.playerKing.getPosition(), enemyMoves).isEmpty();
    }

    public King getPlayerKing(){
        return this.playerKing;
    }
    public List<Move> getPossibleMoves(){
        return this.possibleMoves;
    }

    private static List<Move> getAttacksOnPosition(int position ,final List<Move> moves){
        List<Move> possibleMoves = new ArrayList<>();
        for (Move move : moves) {
            if(move.getDestination() == position){
                possibleMoves.add(move);
            }
        }
        return ImmutableList.copyOf(possibleMoves);
    }


    public boolean isCheck(){
        return this.isCheck;
    }

    public boolean isCheckMate(){
        return !canEscape() && isCheck();
    }

    public boolean isDraw(){
        return this.possibleMoves.isEmpty() && !isCheck();
    }


    public MoveExecution initiateMove(final Move move){
        if(isMoveLegal(move)){
            Board boardExecution = move.execute();
            List<Move> checks = Player.getAttacksOnPosition((boardExecution.getCurrentPlayer().getOpponent().getPlayerKing().getPosition()),boardExecution.getCurrentPlayer().getPossibleMoves());
            if(checks.isEmpty()){
                return new MoveExecution(this.board, move, MoveStatus.CHECK);
            }
            return new MoveExecution(this.board, move, MoveStatus.CHECK);
        }
        return new MoveExecution(this.board, move, MoveStatus.ILLEGAL);
    }

    public boolean isMoveLegal(final Move move){
        return this.possibleMoves.contains(move);
    }

    public boolean canEscape(){
        for(Move move: possibleMoves){
            MoveExecution moveExecution = initiateMove(move);
            if(moveExecution.getStatus() == MoveStatus.DONE){
                return true;
            }
        }
        return false;
    }

    private King initiateKing() {
        for (Piece piece : getRemainingPieces()) {
            if(piece.getType() == Piece.Type.KING) {
                return (King)piece;
            }
        }
        throw new RuntimeException("There is no King!");
    }

    public abstract List<Piece> getRemainingPieces();
    public abstract Team getTeam();
    public abstract Player getOpponent();


}
