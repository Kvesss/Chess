package com.example.chess.figures;

import com.example.chess.Board.Board;
import com.example.chess.GetMovesStrategy.GetPossiblePawnMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Board.Move;
import com.example.chess.Board.Team;

import java.util.List;

public class Pawn extends Piece{

    private IGetPossibleMovesStrategy getPossibleMovesStrategy;

    public Pawn(final int position,final Team team) {
        super(position, team, Type.PAWN);
        this.getPossibleMovesStrategy = new GetPossiblePawnMoves();
    }

    @Override
    public List<Move> getPossibleMoves(final Board board) {
        return this.getPossibleMovesStrategy.getPossibleMoves(board, this);
    }

}
