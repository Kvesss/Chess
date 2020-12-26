package figures;

import com.example.chess.Alliance;
import com.example.chess.Board;
import com.example.chess.Move;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(int position, Alliance alliance) {
        super(position, alliance);
    }

    @Override
    public List<Move> getPossibleMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();



        return possibleMoves;
    }


    public List<Move> getRowMoves(){
        List<Move> moves = new ArrayList<>();
        int rowPosition = ((int)position/8)*8;
        for(int i= rowPosition; i<rowPosition+8;i++){
            moves.add(new Move());      /*Not Implemented Yet*/
        }


        return moves;
    }
}
