package fr.mcstudio.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import fr.mcstudio.board.ActionInfo;
import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.board.PlayerInfo;
import fr.mcstudio.enums.ActionTurn;
import fr.mcstudio.enums.Color;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.SeaSnake;
import fr.mcstudio.pawns.Shark;

/**
 * 
 */
public class Game {
    /**
     * Default constructor
     */
    public Game(int resolution, JPanel contentPane, Player[] players) {
        // this.board = new Board();
    	this.resolution = resolution;
    	this.contentPane = contentPane;
        this.players = players;
        this.turnNumber = 0;
        this.turnOrder = (int) (Math.random() * 3);
        this.actionTurn = ActionTurn.PLAY_TILE;
        
        initializeBoard();
    }

    /**
     * 
     */

	private Board board;
	private PlayerInfo playerInfo;
	private ActionInfo actionInfo;
	private JPanel contentPane;
	
	private int resolution;

    /**
     * 
     */
    private Player[] players;

    /**
     * 
     */
    private int turnNumber;

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
    private int turnOrder;

    /**
     * 
     */
    private ActionTurn actionTurn;
    
    public void initializeBoard() {
    	playerInfo = new PlayerInfo(resolution);
		contentPane.add(playerInfo);

		board = new Board(resolution);
		contentPane.add(board);
		
		this.board.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {
				for (int i = 0; i < 13; i++) {
					for (int j = 0; j < 12; j++) {
						Hexagon hex = board.getHexagons()[i][j];
						if (hex.isInHexagonfloat(resolution, e.getX() - hex.getX(), e.getY() - hex.getY())) {
							System.out.println(
									"Yay ! " + hex.getLine() + " " + hex.getColumn());
							
							hex.discover(null, board);
							
							Shark shark = new Shark();
							SeaSnake ss = new SeaSnake();
							Explorer ex = new Explorer(Color.RED, 0);
							Explorer ex2 = new Explorer(Color.BLUE, 0);
							Explorer ex3 = new Explorer(Color.GREEN, 0);
							hex.addPawn(shark);
							hex.addPawn(ss);
							hex.addPawn(ex);
							hex.addPawn(ex2);
							hex.addPawn(ex3);
							hex.displayPawns();
						}
					}
				}
			}

			public void mouseReleased(MouseEvent e) {}

			public void mouseEntered(MouseEvent e) {}

			public void mouseExited(MouseEvent e) {}
		});

    	this.board.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {}

			@Override
			public void mouseMoved(MouseEvent e) {
				for (int i = 0; i < 13; i++) {
					for (int j = 0; j < 12; j++) {
						Hexagon hex = board.getHexagons()[i][j];
						if (!hex.isVoid()) {
							if (hex.isInHexagonfloat(resolution, e.getX() - hex.getX(), e.getY() - hex.getY())) {
								if (!hex.isHighlight())
									hex.setHighlight(resolution, board, true, "white");

							} else {
								if (hex.isHighlight())
									hex.setHighlight(resolution, board, false, null);
							}
						}
					}
				}
			}
		});

		actionInfo = new ActionInfo(resolution);
		contentPane.add(actionInfo);
    }

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
    public void nextTurn() {
        this.turnNumber++;
        this.getCurrentPlayer().setMoveLeft(3);
        this.actionTurn = ActionTurn.PLAY_TILE;
    }

    /**
     * 
     */
    public Player getCurrentPlayer() {
        return this.players[(this.turnOrder + this.turnNumber) % players.length];
    }

    /**
     * 
     */
    public void nextActionTurn() {
        this.actionTurn = this.actionTurn.next();
    }

    /**
     * 
     */
    public ActionTurn getActionTurn() {
        return this.actionTurn;
    }

    /**
     * 
     */
    public int getTurnOrder() {
        return this.turnNumber;
    }

    /**
     * 
     */
    public int getTurn() {
        return this.turnNumber;
    }

    /**
     * 
     */
    public int getRound() {
        return this.turnNumber / players.length;
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

    public void startGame() {
    	
        /*for (int i = 0; i < players.length; i++) {
            // Afficher message "Pose tes pions"
            players[(this.turnOrder + i) % players.length]
                    .placeAllExplorers(this.board);
            players[(this.turnOrder + i) % players.length]
                    .placeBoats(this.board);
        }*/
    }


    /**
     * 
     */
    public boolean isEnd() {
        for (Player player : players) {
            if (player.haveExplorerOnBoard()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     */
    public void endGame() {
        // TODO
    }

}
