package figures;

import com.example.chess.Team;
import com.example.chess.Board;
import com.example.chess.BoardFuntions;
import com.example.chess.Field;
import com.example.chess.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    private final static int[] possible_moves_coefficients = { -9, -7, 7, 9};

    public Bishop(int position, Team team) {
        super(position, team);
    }

    private static boolean isInvalidEdgeCaseOne(int position, int coordinate){
        return BoardFuntions.COLUMN_ONE[position] && ((coordinate == -9) || (coordinate == 7));
    }
    private static boolean isInvalidEdgeCaseEight(int position, int coordinate){
        return BoardFuntions.COLUMN_EIGHT[position] && ((coordinate == -7) || (coordinate == 9));
    }

    private static boolean isInvalidEdge(int position, int coordinate){
        return isInvalidEdgeCaseOne(position,coordinate) || isInvalidEdgeCaseEight(position, coordinate);
    }

    @Override
    public List<Move> getPossibleMoves(final Board board) {
        List<Move> possibleMoves = new ArrayList<>();
        for (int coefficient:possible_moves_coefficients) {
            int tempCoordinate = this.position;
            while(BoardFuntions.isValidCoordinate(tempCoordinate)){
                if (!isInvalidEdge(tempCoordinate, coefficient)) {
                    tempCoordinate += coefficient;
                    if (BoardFuntions.isValidCoordinate(tempCoordinate)) {
                        Field destinationField = board.getField(tempCoordinate);
                        if (!destinationField.isOccupied()) {
                            possibleMoves.add(new Move.EmptyMove(board, this, tempCoordinate));
                        } else {
                            Piece pieceAtDestination = destinationField.getPiece();
                            if (this.getTeam() != pieceAtDestination.getTeam()) {
                                possibleMoves.add(new Move.AttackMove(board, this, tempCoordinate, pieceAtDestination));
                            }
                            break;
                        }
                    }
                }
            }
        }
        return ImmutableList.copyOf(possibleMoves);
    }
}
