
import java.util.*;

/**
 * 
 */
public class SeaSnake extends Pawn {

    /**
     * Default constructor
     */
    public SeaSnake() {
    }

    /**
     * 
     */
    public void makeEffect(Board board, Hexagon hexagon) {
        Whale whaleEffect = new Whale();
        Shark sharkEffect = new Shark();
        whaleEffect.makeEffect(board, hexagon);
        sharkEffect.makeEffect(board, hexagon);
    }

}