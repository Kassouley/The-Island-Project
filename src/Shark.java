
import java.util.*;

/**
 * 
 */
public class Shark extends Pawn {

    /**
     * Default constructor
     */
    public Shark() {
    }

    /**
     * 
     */
    public void makeEffect(Board board, Hexagon hexagon) {
        for (Pawn p : hexagon.getPawnsList()) {
            if (p instanceof Explorer) {
                Explorer explorer = (Explorer) p;
                explorer.removeFromBoard(board);
                explorer.setStatus(ExplorerStatus.DEAD); // mort
            }
        }
    }

}