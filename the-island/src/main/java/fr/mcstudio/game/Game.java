package fr.mcstudio.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JPanel;

import fr.mcstudio.board.ActionInfo;
import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.board.PlayerInfo;
import fr.mcstudio.enums.ActionTurn;
import fr.mcstudio.enums.Color;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.Pawn;
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
        this.turnOrder = (int) (Math.random() * players.length);
        
        //A set comme vous voulez pour effectuer des test sur les differentes actions
        this.actionTurn = ActionTurn.PLAY_TILE;
        
        initializeBoard();
    }

    /**
     * 
     */
    private Game game = this;
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
    
    /**
     * 
     */
    public void initializeBoard() {
    	playerInfo = new PlayerInfo(resolution);
		contentPane.add(playerInfo);

		board = new Board(resolution);
		contentPane.add(board);
		boardClickAction();
		
		actionInfo = new ActionInfo(resolution);
		contentPane.add(actionInfo);
		actionInfoClickAction();
    }
    
    /**
     * 
     */
    boolean firstClic = true;
    
    /**
     * 
     */
    private Pawn pawnToMove;
    
    /**
     * 
     */
    private Hexagon saveHexa;
    
    public void boardClickAction() {

		this.board.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {
				Random r = new Random();
				for (int i = 0; i < 13; i++) {
					for (int j = 0; j < 12; j++) {
						Hexagon hex = board.getHexagons()[i][j];
						if (!hex.isVoid()) {
							if (hex.isInHexagonfloat(resolution, e.getX() - hex.getX(), e.getY() - hex.getY())) {
								
								if(actionTurn == ActionTurn.PLAY_TILE) {
									//Pour test plus facilement ; les 4 prochaines lignes servent a afficher un pion
									Explorer yop = new Explorer(players[turnOrder].getColor(),5);
									Shark shark = new Shark();
									hex.addPawn(shark);
									hex.addPawn(yop);
									

									// ActionTurn est le changement d'action, à mettre en commentaire pour test
									actionTurn = actionTurn.next();
								}
								else if(actionTurn== ActionTurn.MOVE_PAWNS) {
									if(!board.getHexagons()[i][j].getExplorerList().isEmpty() && firstClic == true) {										
										saveHexa = board.getHexagons()[i][j];
										//--Choix de l'explorateur avec loik 
										for( Explorer explo : board.getHexagons()[i][j].getExplorerList()) {
											pawnToMove = explo;
										}			
										//--
										firstClic = false;	
									}
									else if(firstClic == false) {
										System.out.println("yopi2");
										pawnToMove.move(saveHexa,hex) ;
										
										saveHexa.displayPawns();
										firstClic = true;
										saveHexa = null;
										
										// ActionTurn est le changement d'action, à mettre en commentaire pour test
										actionTurn = actionTurn.next();
									}	
								}
								else if(actionTurn== ActionTurn.DISCOVER_TILE){									
									hex.discover(players[turnOrder], board);

									// ActionTurn est le changement d'action, à mettre en commentaire pour test
									actionTurn = actionTurn.next();
								}
								else if(actionTurn== ActionTurn.MOVE_MONSTER){
									if(!board.getHexagons()[i][j].getSharkList().isEmpty() && firstClic == true) {										
										saveHexa = board.getHexagons()[i][j];
										//--Choix du monstre avec loik 
										for( Shark ss : board.getHexagons()[i][j].getSharkList()) {
											pawnToMove = ss;
										}			
										//--
										firstClic = false;
										
									}
									else if(firstClic == false) {
										System.out.println("yopi2");
										pawnToMove.move(saveHexa, board.getHexagons()[i][j]) ;
										saveHexa.displayPawns();
										firstClic = true;
										saveHexa = null;
										
										// ActionTurn est le changement d'action, à mettre en commentaire pour test
										actionTurn = actionTurn.next();
										turnOrder = (turnOrder + 1) % players.length;
									}
								}
								System.out.println("Joueur :"+ turnOrder + "; " + players[turnOrder].getPseudo());						
								System.out.println(actionTurn + "\n");
									
								
								hex.displayPawns();
							}
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
    }
    
    public void actionInfoClickAction() {
    	this.actionInfo.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}

			public void mouseEntered(MouseEvent e) {}

			public void mouseExited(MouseEvent e) {}
		});

    	this.actionInfo.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {}

			@Override
			public void mouseMoved(MouseEvent e) {}
		});
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
