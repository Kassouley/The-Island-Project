
import java.util.*;

/**
 * 
 */
public class Explorer extends Pawn {

    /**
     * Default constructor
     */
    public Explorer(int color, int treasureValue) {
        this.color = color;
        this.treasureValue = treasureValue;
        this.status = 0;
    }

    /**
     * 
     */
    public int color;

    /**
     * 
     */
    public int treasureValue;

    /**
     * 
     */
    public int status;

    /**
     * 
     */
    public void setStatus(int newStatus) {
        this.status = newStatus;
    }

}