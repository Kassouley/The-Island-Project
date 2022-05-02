import java.util.*;

/**
 * 
 */
public class Game {
    /**
     * Default constructor
     */
    public Game(Player[] players) {
        this.board = new Board();
        this.players = players;
    }

    /**
     * 
     */
    private Board board;

    /**
     * 
     */
    private Player[] players;

    /**
     * 
     */
    private List<Pawn> unusedPawns;

    /**
     * 
     */
    private int timer;

    /**
     * 
     */
    public void endGame() {
        // TODO
    }

}
