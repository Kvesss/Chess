package com.example.chess;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

import com.example.chess.figures.Piece;

public abstract class Field {

    protected final int coordinate;
    private static final Map<Integer, EmptyField> EMPTY_FIELD_MAP = createAllPossibleEmptyFields();

    private static Map<Integer, EmptyField> createAllPossibleEmptyFields() {    //Immutable
        final Map<Integer, EmptyField> emptyFieldMap = new HashMap<>();

        for(int i = 0;i < BoardFuntions.NUMBER_OF_FIELDS; i++){
            emptyFieldMap.put(i, new EmptyField(i));
        }
        return ImmutableMap.copyOf(emptyFieldMap);
    }

    public static Field createField(final int coordinate, final Piece piece){
        if(piece == null){
            return new EmptyField(coordinate);
        }
        return new EmptyField.OccupiedField(coordinate, piece);
    }

    private Field(int coordinate){
        this.coordinate = coordinate;
    }

    public abstract boolean isOccupied();
    public abstract Piece getPiece();

    public static final class EmptyField extends Field {
        private EmptyField(final int coordinate) {
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

        public static final class OccupiedField extends Field {
            private final Piece piece;

            private OccupiedField(final int coordinate, final Piece piece) {
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

