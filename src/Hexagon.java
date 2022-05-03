
import java.util.*;

/**
 * 
 */
public class Hexagon {

    /**
     * Default constructor
     */
    public Hexagon() {
    }

    /**
     * 
     */
    public int type;

    /**
     * 
     */
    private List<Pawn> pawnsList;

    /**
     * 
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 
     */
    public int getType() {
        return this.type;
    }

    /**
     * 
     */
    public void setPawnsList(List<Pawn> pawnsList) {
        this.pawnsList = pawnsList;
    }

    /**
     * 
     */
    public List<Pawn> getPawnsList() {
        return this.pawnsList;
    }

}