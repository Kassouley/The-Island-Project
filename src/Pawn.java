
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
    public void move(Hexagon oldPosition, Hexagon newPosition) {
        oldPosition.getPawnsList().remove(this);
        newPosition.getPawnsList().add(this);
    }

    /**
     * 
     */
    public void removeFromBoard(Board board) {
        board.getPawnsOnBoard().remove(this);
    }

    /**
     * 
     */
    public void addToBoard(Board board) {
        board.getPawnsOnBoard().add(this);
    }

}