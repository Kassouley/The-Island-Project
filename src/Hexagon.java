
import java.util.*;

/**
 * 
 */
public class Hexagon {

    /**
     * <p>
     * Constructeur par défaut
     * </p>
     */
    public Hexagon() {
        this.explorerList = new ArrayList<Explorer>();
        this.sharkList = new ArrayList<Shark>();
        this.whaleList = new ArrayList<Whale>();
        this.seaSnakeList = new ArrayList<SeaSnake>();
        this.boat = null;
    }

    /**
     * 
     */
    public HexagonType type;

    /**
     * 
     */
    private List<Explorer> explorerList;

    /**
     * 
     */
    private List<Shark> sharkList;

    /**
     * 
     */
    private List<Whale> whaleList;

    /**
     * 
     */
    private List<SeaSnake> seaSnakeList;

    /**
     * 
     */
    private Boat boat;

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
     * @return
     */
    public void addPawn(Explorer e) {
        this.explorerList.add(e);
    }

    /**
     * 
     * @return
     */
    public void addPawn(Shark s) {
        this.sharkList.add(s);
    }

    /**
     * 
     * @return
     */
    public void addPawn(Whale w) {
        this.whaleList.add(w);
    }

    /**
     * 
     * @return
     */
    public void addPawn(SeaSnake ss) {
        this.seaSnakeList.add(ss);
    }

    /**
     * 
     * @return
     */
    public void addPawn(Boat b) {
        this.boat = b;
    }

    /**
     * 
     * @return
     */
    public void addPawn(EffectPawn ef) {
        if (ef instanceof Shark) {
            this.sharkList.add((Shark) ef);
        } else if (ef instanceof Whale) {
            this.whaleList.add((Whale) ef);
        } else if (ef instanceof SeaSnake) {
            this.seaSnakeList.add((SeaSnake) ef);
        }
    }

    /**
     * 
     * @return
     */
    public void removePawn(Explorer e) {
        this.explorerList.remove(e);
    }

    /**
     * 
     * @return
     */
    public void removePawn(Shark s) {
        this.sharkList.remove(s);
    }

    /**
     * 
     * @return
     */
    public void removePawn(Whale w) {
        this.whaleList.remove(w);
    }

    /**
     * 
     * @return
     */
    public void removePawn(SeaSnake ss) {
        this.seaSnakeList.remove(ss);
    }

    /**
     * 
     * @return
     */
    public void removePawn(Boat b) {
        this.boat = null;
    }

    /**
     * 
     * @return
     */
    public void removePawn(EffectPawn ef) {
        if (ef instanceof Shark) {
            this.sharkList.remove((Shark) ef);
        } else if (ef instanceof Whale) {
            this.whaleList.remove((Whale) ef);
        } else if (ef instanceof SeaSnake) {
            this.seaSnakeList.remove((SeaSnake) ef);
        }
    }

    /**
     * 
     */
    public List<Explorer> getExplorerList() {
        return this.explorerList;
    }

    /**
     * 
     */
    public List<Shark> getSharkList() {
        return this.sharkList;
    }

    /**
     * 
     */
    public List<Whale> getWhaleList() {
        return this.whaleList;
    }

    /**
     * 
     */
    public List<SeaSnake> getSeaSnakeList() {
        return this.seaSnakeList;
    }

    /**
     * 
     */
    public Boat getBoat() {
        return this.boat;
    }

}