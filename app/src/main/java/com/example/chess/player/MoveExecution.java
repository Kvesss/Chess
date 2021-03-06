package com.example.chess.player;

import com.example.chess.Board.Board;
import com.example.chess.Board.Move;

public class MoveExecution {

    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus status;

    public MoveExecution(final Board transitionBoard,final Move move,final MoveStatus status) {
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.status = status;
    }

    public Board getTransitionBoard() {
        return transitionBoard;
    }
    public Move getMove() {
        return move;
    }
    public MoveStatus getStatus() {
        return status;
    }
}
