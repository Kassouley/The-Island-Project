
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
}