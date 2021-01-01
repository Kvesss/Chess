package com.example.chess.player;

import com.example.chess.Board;
import com.example.chess.Move;
import com.example.chess.Team;
import com.example.chess.figures.Piece;

import java.util.List;

public class BlackPlayer extends Player {


    public BlackPlayer(final Board board,final List<Move> possibleWhiteMoves,final List<Move> possibleBlackMoves) {
        super(board, possibleWhiteMoves, possibleBlackMoves);
    }

    @Override
    public List<Piece> getRemainingPieces() {
        return this.board.getBlackTeam();
    }

    @Override
    public Team getTeam() {
        return Team.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.getWhitePlayer();
    }
}
