package com.example.chess.figures;

import com.example.chess.Board;
import com.example.chess.BoardFuntions;
import com.example.chess.Field;
import com.example.chess.GetMovesStrategy.GetPossibleKingMoves;
import com.example.chess.GetMovesStrategy.IGetPossibleMovesStrategy;
import com.example.chess.Move;
import com.example.chess.Team;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    private IGetPossibleMovesStrategy getPossibleMovesStrategy;

    public King(final int position,final Team team) {
        super(position, team, Type.KING);
        this.getPossibleMovesStrategy = new GetPossibleKingMoves();
    }

    @Override
    public List<Move> getPossibleMoves(final Board board) {
        return getPossibleMovesStrategy.getPossibleMoves(board, this);
    }

}
