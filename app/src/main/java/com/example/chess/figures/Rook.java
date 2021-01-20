package com.example.chess.figures;

import com.example.chess.GetMovesStrategy.GetPossibleRookMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Board.Team;
import com.example.chess.Board.Board;
import com.example.chess.Board.Move;

import java.util.List;

public class Rook extends Piece {

    private IGetPossibleMovesStrategy getPossibleMovesStrategy;

    public Rook(final int position,final Team team) {
        super(position, team, Type.ROOK);
        this.getPossibleMovesStrategy = new GetPossibleRookMoves();
    }

    @Override
    public List<Move> getPossibleMoves(final Board board) {
        return this.getPossibleMovesStrategy.getPossibleMoves(board, this);
    }
}
