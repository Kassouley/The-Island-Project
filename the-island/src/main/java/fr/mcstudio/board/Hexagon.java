package fr.mcstudio.board;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;

import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.pawns.Boat;
import fr.mcstudio.pawns.EffectPawn;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.SeaSnake;
import fr.mcstudio.pawns.Shark;
import fr.mcstudio.pawns.Whale;
import fr.mcstudio.tiles.Tile;

public class Hexagon{

    /**
     * 
     */
    /**
     * <p>
     * Constructeur par d�faut
     * </p>
     */
	
	Hexagon hexagon = this;
	public Hexagon(JLayeredPane boardPane, final int ligne, final int colonne) {
    	this.ligne = ligne;
    	this.colonne = colonne;

    }

	private int ligne;
    
	private int colonne;
	
    private Tile tile;

    /**
     * 
     */
    private HexagonType type;

    /**
     * 
     */
    private List<Explorer> explorerList = new ArrayList<Explorer>();

    /**
     * 
     */
    private List<Shark> sharkList = new ArrayList<Shark>();

    /**
     * 
     */
    private List<Whale> whaleList = new ArrayList<Whale>();

    /**
     * 
     */
    private List<SeaSnake> seaSnakeList = new ArrayList<SeaSnake>();

    /**
     * 
     */
    private Boat boat = null;

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
    
    public boolean isInHexagonfloat (int resolution, float clickx, float clicky) {
    	
    	switch(resolution) {
		case 70:
			if(isInDemiPlan(tile.getX() + 35, tile.getY(), tile.getX(), tile.getY() + 15, clickx, clicky) &&
					isInDemiPlan(tile.getX() + 69, tile.getY() + 15, tile.getX() + 35, tile.getY(), clickx, clicky) &&
					isInDemiPlan(tile.getX(), tile.getY() + 54, tile.getX() + 35, tile.getY() + 69, clickx, clicky) &&
					isInDemiPlan(tile.getX() + 35, tile.getY() + 69, tile.getX() + 69, tile.getY() + 54, clickx, clicky) &&
					isInDemiPlan(tile.getX(), tile.getY() + 15, tile.getX(), tile.getY() + 54, clickx, clicky) && 
					isInDemiPlan(tile.getX() + 69, tile.getY() + 54, tile.getX() + 69, tile.getY() + 15, clickx, clicky)) {
				return true;
			}
			else {
				return false;
			}
		case 80:
			if(isInDemiPlan(tile.getX() + 40, tile.getY(), tile.getX(), tile.getY() + 17, clickx, clicky) &&
					isInDemiPlan(tile.getX() + 79, tile.getY() + 17, tile.getX() + 40, tile.getY(), clickx, clicky) &&
					isInDemiPlan(tile.getX(), tile.getY() + 62, tile.getX() + 40, tile.getY() + 79, clickx, clicky) &&
					isInDemiPlan(tile.getX() + 40, tile.getY() + 79, tile.getX() + 79, tile.getY() + 62, clickx, clicky) &&
					isInDemiPlan(tile.getX(), tile.getY() + 17, tile.getX(), tile.getY() + 62, clickx, clicky) && 
					isInDemiPlan(tile.getX() + 79, tile.getY() + 62, tile.getX() + 79, tile.getY() + 17, clickx, clicky)) {
				return true;
			}
			else {
				return false;
			}
		case 90:
			if(isInDemiPlan(tile.getX() + 45, tile.getY(), tile.getX(), tile.getY() + 20, clickx, clicky) &&
					isInDemiPlan(tile.getX() + 89, tile.getY() + 20, tile.getX() + 45, tile.getY(), clickx, clicky) &&
					isInDemiPlan(tile.getX(), tile.getY() + 69, tile.getX() + 45, tile.getY() + 89, clickx, clicky) &&
					isInDemiPlan(tile.getX() + 45, tile.getY() + 89, tile.getX() + 89, tile.getY() + 69, clickx, clicky) &&
					isInDemiPlan(tile.getX(), tile.getY() + 20, tile.getX(), tile.getY() + 69, clickx, clicky) && 
					isInDemiPlan(tile.getX() + 89, tile.getY() + 69, tile.getX() + 89, tile.getY() + 20, clickx, clicky)) {
				return true;
			}
			else {
				return false;
			}
		default:
			break;
		}
		return false;
    	
		
	}
	
	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public boolean isInDemiPlan(float ax, float ay, float bx, float by, float clickx, float clicky) {
		float d = (bx - ax)*(clicky - ay) - (by - ay)*(clickx - ax);
		
		if(d <= 0)
			return true;
		else 
			return false;
	}
}
