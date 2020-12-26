package com.example.chess;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

import figures.Piece;

public abstract class Checker {

    private static final Map<Integer, EmptyChecker> EMPTY_CHECKER_MAP = createAllPossibleEmptyFields();

    private static Map<Integer,EmptyChecker> createAllPossibleEmptyFields() {    //Immutable
        final Map<Integer, EmptyChecker> emptyCheckerMap = new HashMap<>();

        for(int i = 0;i < BoardFuntions.NUMBER_OF_FIELDS; i++){
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
            return null;        //Empty Checker
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
