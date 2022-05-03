
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

    /**
     * 
     */
    private List<Pawn> unusedPawns;

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
    public void setUnusedPawns(List<Pawn> unusedPawns) {
        this.unusedPawns = unusedPawns;
    }

    /**
     * 
     */
    public List<Pawn> getUnusedPawns() {
        return this.unusedPawns;
    }

    /**
     * 
     */
    public void init() {
        // TODO implement here
    }

}