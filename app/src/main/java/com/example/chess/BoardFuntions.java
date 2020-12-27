 package com.example.chess;

 public class BoardFuntions {
    public static final boolean[] COLUMN_ONE = createColumn(0);
    public static final boolean[] COLUMN_TWO = createColumn(1);
    public static final boolean[] COLUMN_SEVEN = createColumn(6);
    public static final boolean[] COLUMN_EIGHT = createColumn(7);
    public static final int NUMBER_OF_FIELDS = 64;

    private static boolean[] createColumn(int column) {
        final boolean[] values = new boolean[NUMBER_OF_FIELDS];
        for(int i = 0; i<NUMBER_OF_FIELDS; i++){
            if(i%8==column){
                values[i] = true;
            }
            else{
                values[i] = false;
            }
        }

        return values;
    }

    private BoardFuntions() {
        throw new RuntimeException("Cannot be instantiated");
    }


    public static boolean isValidCoordinate(int coordinate) {
        return(coordinate >= NUMBER_OF_FIELDS ||  coordinate < 0);
        }
}
