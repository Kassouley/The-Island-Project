
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
    private int color;

    /**
     * 
     */
    private int treasureValue;

    /**
     * 
     */
    private int status;

    /**
     * 
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * 
     */
    public int getColor() {
        return this.color;
    }

    /**
     * 
     */
    public void setTreasureValue(int treasureValue) {
        this.treasureValue = treasureValue;
    }

    /**
     * 
     */
    public int getTreasureValue(int treasureValue) {
        return this.treasureValue;
    }

    /**
     * 
     */
    public void setStatus(int newStatus) {
        this.status = newStatus;
    }

    /**
     * 
     */
    public int getStatus(int newStatus) {
        return this.status;
    }

    /**
     * 
     */
    public void getOn(Boat boat) {
        // todo
    }

    /**
     * 
     */
    public void getOff(Boat boat) {
        // todo
    }

}