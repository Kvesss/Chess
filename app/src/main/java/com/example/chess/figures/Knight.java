package com.example.chess.figures;

import com.example.chess.GetMovesStrategy.GetPossibleKnightMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Board.Team;
import com.example.chess.Board.Board;
import com.example.chess.Board.Move;

import java.util.Collection;
import java.util.List;

public class Knight extends Piece {

    private IGetPossibleMovesStrategy getPossibleMovesStrategy;

    public Knight(final int position,final Team team) {
        super(position, team, Type.KNIGHT);
        this.getPossibleMovesStrategy = new GetPossibleKnightMoves();
    }

    @Override
    public Collection<Move> getPossibleMoves(final Board board) {
        return getPossibleMovesStrategy.getPossibleMoves(board, this);
    }

    @Override
    public Knight move(final Move move) {
        return new Knight(move.getDestination(), move.getPieceMoved().getTeam());
    }

}
