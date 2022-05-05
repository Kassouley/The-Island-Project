
import java.util.*;

/**
 * 
 */
public class Player {

    /**
     * Default constructor
     */
    public Player(String pseudo, Color color, boolean isBot) {
        this.pseudo = pseudo;
        this.color = color;
        this.explorerList = new ArrayList<Explorer>();
        this.isBot = isBot;
        this.moveLeft = 3;
    }

    /**
     * 
     */
    private String pseudo;

    /**
     * 
     */
    private Color color;

    /**
     * 
     */
    private List<Explorer> explorerList;

    /**
     * 
     */
    private List<Tile> tileList;

    /**
     * 
     */
    private boolean isBot;

    /**
     * 
     */
    private int moveLeft;

    /**
     * 
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * 
     */
    public String getPseudo() {
        return this.pseudo;
    }

    /**
     * 
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * 
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * 
     */
    public void setBot(boolean bool) {
        this.isBot = bool;
    }

    /**
     * 
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
     * 
     */
    public void initPlayerExplorer() {

        int[] treasureValue = new int[] { 1, 1, 1, 2, 2, 3, 3, 4, 5, 6 };
        for (int i : treasureValue) {
            Explorer explorer = new Explorer(this.color, i);
            this.explorerList.add(explorer);
        }
    }

    /**
     * 
     */
    public void removeOneMove() {
        if (this.moveLeft > 0) {
            this.moveLeft -= 1;
        }
    }

    /**
     * 
     */
    public void setMoveLeft(int moveLeft) {
        if (moveLeft > 0) {
            this.moveLeft = moveLeft;
        } else {
            System.out.println("Erreur : un nombre de coup ne peut être ni négatif ni nul");
        }
    }

    /**
     * 
     */
    public int getMoveLeft() {
        return this.moveLeft;
    }

}