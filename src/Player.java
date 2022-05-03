
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

}