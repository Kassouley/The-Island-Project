package fr.mcstudio.theisland.board;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.mcstudio.theisland.enums.HexagonType;
import fr.mcstudio.theisland.pawns.*;
import fr.mcstudio.theisland.tiles.*;

@SuppressWarnings("serial")
public class Hexagon extends JPanel {

    /**
     * 
     */
    /**
     * <p>
     * Constructeur par d�faut
     * </p>
     */
    public Hexagon(JLayeredPane plateauPane) {
        this.explorerList = new ArrayList<Explorer>();
        this.sharkList = new ArrayList<Shark>();
        this.whaleList = new ArrayList<Whale>();
        this.seaSnakeList = new ArrayList<SeaSnake>();
        this.boat = null;
        plateauPane.setLayer(this, 2);
    }

    private Tile tile;

    /**
     * 
     */
    private HexagonType type;

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

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

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

    public void setPosition(int x, int y) {

        this.setBounds(x, y, 90, 90);

    }
}
