package com.example.chess.figures;

import com.example.chess.GetMovesStrategy.GetPossibleRookMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Board.Team;
import com.example.chess.Board.Board;
import com.example.chess.Board.Move;

import java.util.Collection;
import java.util.List;

public class Rook extends Piece {

    private IGetPossibleMovesStrategy getPossibleMovesStrategy;

    public Rook(final int position,final Team team) {
        super(position, team, Type.ROOK);
        this.getPossibleMovesStrategy = new GetPossibleRookMoves();
    }

    @Override
    public Collection<Move> getPossibleMoves(final Board board) {
        return this.getPossibleMovesStrategy.getPossibleMoves(board, this);
    }

    @Override
    public Rook move(final Move move) {
        return new Rook(move.getDestination(), move.getPieceMoved().getTeam());
    }
}
