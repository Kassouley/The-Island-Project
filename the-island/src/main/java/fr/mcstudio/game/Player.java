/*
 * Nom de classe : Player
 *
 * Description   : Gestion des joueurs du jeu The Island
 *
 * Version       : 1.0
 *
 * Date          : 05/05/2022
 * 
 * Copyright     : Lucas Neto
 */

package fr.mcstudio.game;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.pawns.Boat;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.tiles.Tile;

/**
 * <p>
 * Gestion des joueurs du jeu The Island
 * </p>
 *
 * @version 1.0
 *
 * @author Lucas Neto
 */
public class Player {

    /**
     * <p>
     * Constructeur par défaut
     * </p>
     */
    public Player(String pseudo, Color color, boolean isBot, int resolution) {
        this.pseudo = pseudo;
        this.color = color;
        this.resolution = resolution;
        this.explorerList = initPlayerExplorer();
        this.isBot = isBot;
        this.moveLeft = 3;
        this.pawnOnBoardNumber = 0;
        this.boatToSet.add(new Boat(resolution));
        this.boatToSet.add(new Boat(resolution));
    }

    /**
     * 
     */
    private int resolution;

    /**
     * <p>
     * Pseudo du joueur
     * </p>
     */
    private String pseudo;

    /**
     * 
     */
    private Icon avatar;

    /**
     * <p>
     * Couleur du joueur
     * </p>
     */
    private Color color;

    /**
     * 
     */
    private int pawnOnBoardNumber;
    /**
     * <p>
     * Liste des explorateurs du joueur
     * </p>
     * 
     * @see Color.java
     */
    private List<Explorer> explorerList = new ArrayList<Explorer>();

    
    private List<Boat> boatToSet = new ArrayList<Boat>();
    
    /**
     * <p>
     * Liste des explorateurs du joueur
     * </p>
     * 
     * @see Color.java
     */
    private List<Explorer> currentExplorerList = new ArrayList<Explorer>();

    /**
     * <p>
     * Liste des tuiles du joueur
     * </p>
     */
    private List<Tile> tileList = new ArrayList<Tile>();

    /**
     * <p>
     * Booléen ordinateur
     * </p>
     */
    private boolean isBot;

    /**
     * <p>
     * Coup restant du joueur
     * </p>
     */
    private int moveLeft;

    /**
     * <p>
     * Mutateur du pseudo du joueur.
     * </p>
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * <p>
     * Accesseur du pseudo du joueur.
     * </p>
     */
    public String getPseudo() {
        return this.pseudo;
    }

    /**
     * 
     * 
     */
    public void setAvatar(Icon avatar) {
        this.avatar = avatar;
    }

    /**
     * 
     * 
     */
    public Icon getAvatar() {
        return this.avatar;
    }

    /**
     * <p>
     * Mutateur de la couleur du joueur.
     * </p>
     * 
     * @see Color.java
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * <p>
     * Accesseur du pseudo du joueur.
     * </p>
     * 
     * @see Color.java
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * 
     * 
     */
    public int getPawnOnBoardNumber() {
        return this.pawnOnBoardNumber;
    }

    /**
     * 
     * 
     */
    public void addPawnOnBoardNumber() {
        this.pawnOnBoardNumber++;
    }

    /**
     * <p>
     * Mutateur du status ordinateur du joueur.
     * </p>
     */
    public void setBot(boolean bool) {
        this.isBot = bool;
    }

    /**
     * <p>
     * Accesseur du status ordinateur du joueur.
     * </p>
     */
    public boolean isBot() {
        return this.isBot;
    }

    /**
     * 
     */
    public void returnChoosenHexagon() {
        // TODO implement here
    }

    /**
     * 
     */
    public void returnChoosenPawn() {
        // TODO implement here
    }

    /**
     * 
     */
    public void returnChoosenTile() {
        // TODO implement here
    }

    /**
     * <p>
     * Initialise les explorateurs du joueur.
     * </p>
     */
    public List<Explorer> initPlayerExplorer() {
        List<Explorer> explorerList = new ArrayList<Explorer>();
        int[] treasureValue = new int[] { 1, 1, 1, 2, 2, 3, 3, 4, 5, 6 };
        for (int i : treasureValue) {
            Explorer explorer = new Explorer(this.color, i);
            explorer.setPosition(0, 0, resolution, 68);
            explorer.createImage(resolution);
            explorerList.add(explorer);
        }
        return explorerList;
    }

    /**
     * <p>
     * Retire un coup des coups restant du joueur.
     * </p>
     */
    public void removeOneMove() {
        if (this.moveLeft > 0) {
            this.moveLeft -= 1;
        }
    }

    /**
     * <p>
     * Mutateur des coups restant du joueur.
     * </p>
     */
    public void setMoveLeft(int moveLeft) {
        if (moveLeft >= 0) {
            this.moveLeft = moveLeft;
        } else {
            System.out.println("Error : the number of move left cant be negative.");
        }
    }

    /**
     * <p>
     * Accesseur des coups restant du joueur.
     * </p>
     */
    public int getMoveLeft() {
        return this.moveLeft;
    }

    public List<Explorer> getExplorerList() {
        return explorerList;
    }

    public List<Boat> getBoatToSet() {
        return boatToSet;
    }

    /**
     * 
     */
    public boolean haveExplorerOnBoard() {
        for (Explorer e : this.explorerList) {
            if (e.getStatus() != ExplorerStatus.DEAD
                    || e.getStatus() != ExplorerStatus.SAVED) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the tileList
     */
    public List<Tile> getTileList() {
        return tileList;
    }

    /**
     * 
     */
    public int getNumberExplorerAlive() {
        int cmpt = 0;
        for (Explorer e : currentExplorerList) {
            if (e.getStatus() == ExplorerStatus.NORMAL
                    || e.getStatus() == ExplorerStatus.SWIMMER
                    || e.getStatus() == ExplorerStatus.ONBOAT) {
                cmpt++;
            }
        }
        return cmpt;
    }

    /**
     * 
     */
    public int getNumberExplorerSaved() {
        int cmpt = 0;
        for (Explorer e : currentExplorerList) {
            if (e.getStatus() == ExplorerStatus.SAVED) {
                cmpt++;
            }
        }
        return cmpt;
    }

    /**
     * 
     */
    public int getNumberExplorerDead() {
        int cmpt = 0;
        for (Explorer e : currentExplorerList) {
            if (e.getStatus() == ExplorerStatus.DEAD) {
                cmpt++;
            }
        }
        return cmpt;
    }
    
    /**
   	 * 
   	 */
   	public void resetMovePointExplorer() {
   		for(Explorer e : this.currentExplorerList) {
   			if(e.getStatus() == ExplorerStatus.SWIMMER) {
   				e.setMovePoint(1);
   			} else {
   				e.setMovePoint(3);
   			}
   		}
   	}

   	/**
   	 * @return the currentExplorerList
   	 */
   	public List<Explorer> getCurrentExplorerList() {
   		return currentExplorerList;
   	}

}
