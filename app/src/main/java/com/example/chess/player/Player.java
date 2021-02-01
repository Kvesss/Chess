package com.example.chess.player;


import com.example.chess.Board.Board;
import com.example.chess.Board.Move;
import com.example.chess.Board.Team;
import com.example.chess.figures.King;
import com.example.chess.figures.Piece;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {
    protected final Board board;
    protected final King playerKing;
    protected final List<Move> possibleMoves;
    protected final boolean isCheck;

    public Player(final Board board, final List<Move> possibleMoves, final List<Move> enemyMoves){
        this.board = board;
        this.playerKing = initiateKing();
        this.possibleMoves = ImmutableList.copyOf(Iterables.concat(possibleMoves, getKingCastle(possibleMoves, enemyMoves)));
        this.isCheck = !Player.getAttacksOnPosition(this.playerKing.getPosition(), enemyMoves).isEmpty();
    }

    public abstract Collection<Piece> getRemainingPieces();
    public abstract Team getTeam();
    public abstract Player getOpponent();
    public abstract Collection<Move> getKingCastle(Collection<Move> currentMoves, Collection<Move> opponentMoves);

    public King getPlayerKing(){
        return this.playerKing;
    }
    public List<Move> getPossibleMoves(){
        return this.possibleMoves;
    }

    public static Collection<Move> getAttacksOnPosition(final int position ,final Collection<Move> moves){
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
            Board boardExecution = move.executeMove();
            Collection<Move> checks = Player.getAttacksOnPosition((boardExecution.getCurrentPlayer().getOpponent().getPlayerKing().getPosition()),
                    boardExecution.getCurrentPlayer().getPossibleMoves());
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


    protected boolean canCastle() {
        return !this.isCheck && !this.playerKing.hasCastled() &&
                (this.playerKing.isFourSideCastleCapable() || this.playerKing.isFiveSideCastleCapable());
    }




}
