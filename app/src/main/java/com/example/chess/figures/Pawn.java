package com.example.chess.figures;

import com.example.chess.Board.Board;
import com.example.chess.GetMovesStrategy.GetPossiblePawnMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Board.Move;
import com.example.chess.Board.Team;

import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

    private IGetPossibleMovesStrategy getPossibleMovesStrategy;

    public Pawn(final int position,final Team team) {
        super(position, team, Type.PAWN);
        this.getPossibleMovesStrategy = new GetPossiblePawnMoves();
    }

    @Override
    public Collection<Move> getPossibleMoves(final Board board) {
        return this.getPossibleMovesStrategy.getPossibleMoves(board, this);
    }

    @Override
    public Pawn move(final Move move) {
        return new Pawn(move.getDestination(), move.getPieceMoved().getTeam());
    }

}
