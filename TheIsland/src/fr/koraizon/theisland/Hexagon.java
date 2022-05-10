package fr.koraizon.theisland;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.koraizon.theisland.enums.HexagonType;

@SuppressWarnings("serial")
public class Hexagon{


    /**
     * 
     */
    /**
     * <p>
     * Constructeur par défaut
     * </p>
     */
    public Hexagon(JLayeredPane boardPane, int ligne, int colonne) {
    	this.ligne = ligne;
    	this.colonne = colonne;
        this.explorerList = new ArrayList<Explorer>();
        this.sharkList = new ArrayList<Shark>();
        this.whaleList = new ArrayList<Whale>();
        this.seaSnakeList = new ArrayList<SeaSnake>();
        this.boat = null;

        boardPane.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println(e.getX() + "," + e.getY() + "," + getX() + "," + getY());
				if(tile != null)
					if(isInHexagonfloat(e.getX(), e.getY())) {
						System.out.println("Yay ! " + ligne + " " + colonne);
					}
			}

			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		  });
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
	
	public boolean isInHexagonfloat (float clickx, float clicky) {
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
	}
	
	public boolean isInDemiPlan(float ax, float ay, float bx, float by, float clickx, float clicky) {
		float d = (bx - ax)*(clicky - ay) - (by - ay)*(clickx - ax);
		
		if(d <= 0)
			return true;
		else 
			return false;
	}
}
