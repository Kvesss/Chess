package com.example.chess.figures;

import com.example.chess.GetMovesStrategy.GetPossibleBishopMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Board.Team;
import com.example.chess.Board.Board;
import com.example.chess.Board.Move;

import java.util.List;

public class Bishop extends Piece{

    private IGetPossibleMovesStrategy getPossibleMovesStrategy;

    public Bishop(final int position,final Team team) {
        super(position, team, Type.BISHOP);
        this.getPossibleMovesStrategy = new GetPossibleBishopMoves();
    }

    @Override
    public List<Move> getPossibleMoves(final Board board) {
        return getPossibleMovesStrategy.getPossibleMoves(board, this);
    }

}
