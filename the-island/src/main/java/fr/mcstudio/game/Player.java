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

import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.ExplorerStatus;
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
    public Player(String pseudo, Color color, boolean isBot) {
        this.pseudo = pseudo;
        this.color = color;
        this.explorerList = initPlayerExplorer();
        this.isBot = isBot;
        this.moveLeft = 3;
    }

    /**
     * <p>
     * Pseudo du joueur
     * </p>
     */
    private String pseudo;

    /**
     * <p>
     * Couleur du joueur
     * </p>
     */
    private Color color;

    /**
     * <p>
     * Liste des explorateurs du joueur
     * </p>
     * 
     * @see Color.java
     */
    private List<Explorer> explorerList = new ArrayList<Explorer>();

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
        if (moveLeft > 0) {
            this.moveLeft = moveLeft;
        } else {
            System.out.println("Error : the number of move left cant be negative or zero.");
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
    /**
     * <p>
     * Permet au joueur de placer tout ses explorateurs sur une tuile vide.
     * </p>
     * 
     * @param board le plateau dans lequel on pose les explorateurs.
     */
    /*public void placeAllExplorers(Board board) {
        for (Explorer e : this.explorerList) {
            Hexagon hexagon;
            do {
                hexagon = board.returnHexagon();
            } while (!hexagon.isTiles() || !hexagon.getExplorerList().isEmpty());

            hexagon.addPawn(e);
            hexagon = null;
        }
    }*/

    /**
     * <p>
     * Permet au joueur de placer 2 bateaux sur des tuile de mer.
     * </p>
     * 
     * @param board le plateau dans lequel on pose les bateaux.
     */
    /*public void placeBoats(Board board) {
        for (int i = 0; i < 2; i++) {
            Hexagon hexagon;
            do {
                hexagon = board.returnHexagon();
            } while (!hexagon.isSea()
                    || hexagon.getBoat() != null
                    || !board.getBottomLeft(hexagon).isTiles()
                    || !board.getLeft(hexagon).isTiles()
                    || !board.getTopLeft(hexagon).isTiles()
                    || !board.getBottomRight(hexagon).isTiles()
                    || !board.getRight(hexagon).isTiles()
                    || !board.getTopRight(hexagon).isTiles());

            Boat b = new Boat();
            hexagon.addPawn(b);
            hexagon = null;
        }
    }*/

    public List<Explorer> getExplorerList() {
		return explorerList;
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

	public List<Tile> getTileList() {
		return tileList;
	}
}
