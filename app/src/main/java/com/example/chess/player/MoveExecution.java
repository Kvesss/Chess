package com.example.chess.player;

import com.example.chess.Board;
import com.example.chess.Move;

public class MoveExecution {

    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus status;

    public MoveExecution(Board transitionBoard, Move move, MoveStatus status) {
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
