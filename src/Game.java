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
    private int initialTimer;

    /**
     * 
     */
    private int finalTimer;

    /**
     * 
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * 
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * 
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * 
     */
    public Player[] getPlayers() {
        return this.players;
    }

    /**
     * 
     */
    public void setInitialTimer(int initialTimer) {
        this.initialTimer = initialTimer;
    }

    /**
     * 
     */
    public int getInitialTimer() {
        return this.initialTimer;
    }

    /**
     * 
     */
    public void setFinalTimer(int finalTimer) {
        this.finalTimer = finalTimer;
    }

    /**
     * 
     */
    public int getFinalTimer() {
        return this.finalTimer;
    }

    /**
     * 
     */
    public int currentTimer() {
        return 1;
    }

    /**
     * 
     */
    public void endGame() {
        // TODO
    }

}
