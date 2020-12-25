package com.example.chess;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Checker {

    private static final Map<Integer, EmptyChecker> EMPTY_CHECKER_MAP = createAllPossibleEmptyTiles();

    private static Map<Integer,EmptyChecker> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyChecker> emptyCheckerMap = new HashMap<>();

        for(int i = 0;i < 65; i++){
            emptyCheckerMap.put(i, new EmptyChecker(i));
        }
        return ImmutableMap.copyOf(emptyCheckerMap);
    }

    public static Checker createChecker(final int coordinate, final Piece piece){
        if(piece == null){
            return new EmptyChecker(coordinate);
        }
        return new OccupiedChecker(coordinate, piece);
    }

    protected final int coordinate;

    private Checker(int coordinate){
        this.coordinate = coordinate;
    }

    public abstract boolean isOccupied();

    public abstract Piece getPiece();

    public static final class EmptyChecker extends Checker{
        private EmptyChecker(final int coordinate) {
            super(coordinate);
        }

        @Override
        public boolean isOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }



    }
    public static final class OccupiedChecker extends Checker{
        private final Piece piece;

        private OccupiedChecker(final int coordinate,final Piece piece) {
            super(coordinate);
            this.piece = piece;
        }

        @Override
        public boolean isOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.piece;
        }
    }

}
