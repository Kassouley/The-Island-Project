
import java.util.*;

/**
 * 
 */
public class Pawn {

    /**
     * Default constructor
     */
    public Pawn() {
    }

    /**
     * 
     */
    public void move(Board board, Hexagon newPosition) {
        // TODO implement here
    }

    /**
     * 
     */
    public void removeFromBoard(Board board) {
        board.getPawnsOnBoard().remove(this);
        board.getUnusedPawns().add(this);
    }

    /**
     * 
     */
    public void addToBoard(Board board) {
        board.getPawnsOnBoard().add(this);
        board.getUnusedPawns().remove(this);
    }

}