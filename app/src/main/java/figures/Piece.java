package figures;

import com.example.chess.Alliance;
import com.example.chess.Board;
import com.example.chess.Move;

import java.util.List;

public abstract class Piece {   //Abstraction of a figure

    protected final int position;
    protected final Alliance alliance;

    public Piece(final int position,final Alliance alliance) {
        this.position = position;
        this.alliance = alliance;
    }

    public int getPosition() {
        return position;
    }

    public Alliance getAlliance() {
        return alliance;
    }
    public abstract List<Move> getPossibleMoves(final Board board);
}
