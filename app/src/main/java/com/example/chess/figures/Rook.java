package com.example.chess.figures;

import com.example.chess.BoardFuntions;
import com.example.chess.Field;
import com.example.chess.GetMovesStrategy.GetPossibleRookMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Team;
import com.example.chess.Board;
import com.example.chess.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
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
