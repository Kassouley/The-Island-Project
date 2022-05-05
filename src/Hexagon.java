
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
    public HexagonType type;

    /**
     * 
     */
    private List<Pawn> pawnsList;

    /**
     * 
     */
    public void setType(HexagonType type) {
        this.type = type;
    }

    /**
     * 
     */
    public HexagonType getType() {
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