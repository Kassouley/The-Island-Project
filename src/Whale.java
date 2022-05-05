
import java.util.*;

/**
 * 
 */
public class Whale extends Pawn {

    /**
     * Default constructor
     */
    public Whale() {
    }

    /**
     * 
     */
    public void makeEffect(Board board, Hexagon hexagon) {
        for (Pawn p : hexagon.getPawnsList()) {
            if (p instanceof Boat) {
                Boat boat = (Boat) p;
                if (!boat.explorerList.isEmpty()) {
                    boat.sunk(board, hexagon);
                }
            }
        }
    }
}