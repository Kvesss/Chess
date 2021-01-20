package com.example.chess.Board;

import com.example.chess.player.Player;

public enum Team {
    WHITE {
        @Override
        public int getDirection() {
            return -1;
        }

        @Override
        public Player choosePlayer(final Player whitePlayer,final Player blackPlayer) {
            return whitePlayer;
        }
    },
    BLACK {
        @Override
        public int getDirection() {
            return 1;
        }

        @Override
        public Player choosePlayer(final Player whitePlayer,final Player blackPlayer) {
            return blackPlayer;
        }
    };

    public abstract int getDirection();

    public abstract Player choosePlayer(final Player whitePlayer,final Player blackPlayer);
}

