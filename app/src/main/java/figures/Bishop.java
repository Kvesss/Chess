package figures;

import com.example.chess.Alliance;
import com.example.chess.Board;
import com.example.chess.BoardFuntions;
import com.example.chess.Move;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    private final static int[] possible_moves_coefficients = { -9, -7, 7, 9};


    public Bishop(int position, Alliance alliance) {
        super(position, alliance);
    }



    @Override
    public List<Move> getPossibleMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();

        for (int coefficient:possible_moves_coefficients) {
            int tempCoordinate = this.position;
            while(BoardFuntions.isValidCoordinate(tempCoordinate)){
                tempCoordinate += coefficient;
                if(BoardFuntions.isValidCoordinate(tempCoordinate)){
                    possibleMoves.add(new)
                }
            }
        }

    }
}
