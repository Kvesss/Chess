package com.example.chess.GetMovesStrategy;

import com.example.chess.Board.Board;
import com.example.chess.Board.Move;
import com.example.chess.figures.Piece;

import java.util.List;

public interface IGetPossibleMovesStrategy {
    List<Move> getPossibleMoves(final Board board, final Piece piece);
}
