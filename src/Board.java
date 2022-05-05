
import java.util.*;

/**
 * 
 */
public class Board {

    /**
     * Default constructor
     */
    public Board() {
    }

    private Hexagon[][] hexagons;

    /**
     * 
     */
    public List<Pawn> pawnsOnBoard;

    /**
     * 
     */
    public void setPawnsOnBoard(List<Pawn> pawnsOnBoard) {
        this.pawnsOnBoard = pawnsOnBoard;
    }

    /**
     * 
     */
    public List<Pawn> getPawnsOnBoard() {
        return this.pawnsOnBoard;
    }

    /**
     * 
     */
    public void init() {
        // TODO implement here
    }

}