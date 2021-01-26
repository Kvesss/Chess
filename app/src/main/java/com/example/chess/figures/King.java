package com.example.chess.figures;

import com.example.chess.Board.Board;
import com.example.chess.GetMovesStrategy.GetPossibleKingMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Board.Move;
import com.example.chess.Board.Team;

import java.util.Collection;
import java.util.List;

public class King extends Piece{

    private IGetPossibleMovesStrategy getPossibleMovesStrategy;

    public King(final int position,final Team team) {
        super(position, team, Type.KING);
        this.getPossibleMovesStrategy = new GetPossibleKingMoves();
    }

    @Override
    public Collection<Move> getPossibleMoves(final Board board) {
        return getPossibleMovesStrategy.getPossibleMoves(board, this);
    }

    @Override
    public King move(final Move move) {
        return new King(move.getDestination(), move.getPieceMoved().getTeam());
    }

}
