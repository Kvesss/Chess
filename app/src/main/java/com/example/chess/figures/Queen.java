package com.example.chess.figures;

import com.example.chess.Board.Board;
import com.example.chess.GetMovesStrategy.GetPossibleQueenMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Board.Move;
import com.example.chess.Board.Team;

import java.util.List;

public class Queen extends Piece{

    private IGetPossibleMovesStrategy getPossibleMovesStrategy;

    public Queen(final int position,final Team team) {
        super(position, team, Type.QUEEN);
        this.getPossibleMovesStrategy = new GetPossibleQueenMoves();
    }

    @Override
    public List<Move> getPossibleMoves(final Board board) {
        return this.getPossibleMovesStrategy.getPossibleMoves(board, this);
    }

}
