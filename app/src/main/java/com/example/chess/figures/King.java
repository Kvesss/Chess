package com.example.chess.figures;

import com.example.chess.Board.Board;
import com.example.chess.GetMovesStrategy.GetPossibleKingMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Board.Move;
import com.example.chess.Board.Team;

import java.util.Collection;

public class King extends Piece{

    private IGetPossibleMovesStrategy getPossibleMovesStrategy;
    private final boolean hasCastled;
    private final boolean fourSideCastleCapable;
    private final boolean fiveSideCastleCapable;


    public King(final int position, final Team team, boolean fourSideCastleCapable, boolean fiveSideCastleCapable) {
        super(position, team, Type.KING);
        this.fourSideCastleCapable = fourSideCastleCapable;
        this.fiveSideCastleCapable = fiveSideCastleCapable;
        this.getPossibleMovesStrategy = new GetPossibleKingMoves();
        this.hasCastled = false;
    }

    @Override
    public Collection<Move> getPossibleMoves(final Board board) {
        return getPossibleMovesStrategy.getPossibleMoves(board, this);
    }

    @Override
    public King move(final Move move) {
        return new King(move.getDestination(), move.getPieceMoved().getTeam(), false, false);
    }

    public boolean hasCastled() {
        return this.hasCastled;
    }

    public boolean isFourSideCastleCapable() {
        return fourSideCastleCapable;
    }

    public boolean isFiveSideCastleCapable() {
        return fiveSideCastleCapable;
    }
}
