package com.example.chess.player;

import com.example.chess.Board;
import com.example.chess.Move;
import com.example.chess.Team;
import com.example.chess.figures.Piece;

import java.util.List;

public class WhitePlayer extends Player {

    public WhitePlayer(final Board board,final List<Move> possibleWhiteMoves,final List<Move> possibleBlackMoves) {
        super(board, possibleWhiteMoves, possibleBlackMoves);
    }

    @Override
    public List<Piece> getRemainingPieces() {
        return this.board.getWhiteTeam();
    }

    @Override
    public Team getTeam() {
        return Team.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.getBlackPlayer();
    }
}
