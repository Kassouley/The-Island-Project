package fr.mcstudio.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import fr.mcstudio.board.ActionInfo;
import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.board.PlayerInfo;
import fr.mcstudio.enums.ActionTurn;
<<<<<<< Updated upstream
import fr.mcstudio.enums.GameState;
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.enums.TilesType;
=======
import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.ExternalPanelState;
>>>>>>> Stashed changes
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.Pawn;
import fr.mcstudio.pawns.SeaSnake;
import fr.mcstudio.pawns.Shark;
import fr.mcstudio.util.Pair;
import fr.mcstudio.util.PairList;

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
        this.gameState = GameState.INITIALISATION;
        
    }

    /**
     * 
     */
	private Board board;
	private PlayerInfo playerInfo;
	private ActionInfo actionInfo;
	private JPanel contentPane;
	private GameState gameState;
	
	
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
    
    private PairList<Hexagon,HexagonListType> hexagonPairList = new PairList<Hexagon, HexagonListType>();
    
    /**
     * 
     */
    public void initializeBoard() {
    	playerInfo = new PlayerInfo(resolution);
		contentPane.add(playerInfo);

		board = new Board(this, resolution);
		contentPane.add(board);
		boardClickAction();
		
		actionInfo = new ActionInfo(resolution);
		contentPane.add(actionInfo);
		actionInfoClickAction();
		
		board.getHexagons()[1][1].getSeaSnakeList().add(new SeaSnake());
    	board.getHexagons()[1][1].displayPawns();
    	board.getHexagons()[2][10].getSeaSnakeList().add(new SeaSnake());
    	board.getHexagons()[2][10].displayPawns();
    	board.getHexagons()[10][0].getSeaSnakeList().add(new SeaSnake());
    	board.getHexagons()[10][0].displayPawns();
    	board.getHexagons()[11][10].getSeaSnakeList().add(new SeaSnake());
    	board.getHexagons()[11][10].displayPawns();
    	board.getHexagons()[6][5].getSeaSnakeList().add(new SeaSnake());
    	board.getHexagons()[6][5].displayPawns();
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
<<<<<<< Updated upstream
				if(SwingUtilities.isLeftMouseButton(e)) {
					for (int i = 0; i < 13; i++) {
						for (int j = 0; j < 12; j++) {
							Hexagon hex = board.getHexagons()[i][j];
							if (!hex.isVoid()) {
								if (hex.isInHexagonfloat(resolution, e.getX() - hex.getX(), 
										e.getY() - hex.getY())) {
									if(gameState == GameState.INITIALISATION) {
										gameState = GameState.PLAYING;
									} else if(gameState == GameState.PLAYING) {
										inGame(hex);
									} else if(gameState == GameState.ENDING) {
										endGame();
									}
								}
=======
				if(!board.isDisplayExternalPanel()) {
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
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
				for (int i = 0; i < 13; i++) {
					for (int j = 0; j < 12; j++) {
						Hexagon hex = board.getHexagons()[i][j];
						if (!hex.isVoid()) {
							if (hex.isInHexagonfloat(resolution, e.getX() - hex.getX(), e.getY() - hex.getY())) {
								if (!hex.isHighlight() || hex.getHighlightColor() == null)
									hex.setHighlight(resolution, board, true, "white");

							} else {
								if (hex.isHighlight() && hex.getHighlightColor() == null) {
									hex.setHighlight(resolution, board, false, null);
=======
				if(!board.isDisplayExternalPanel()) {
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
>>>>>>> Stashed changes
								}
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
        /*if(actionTurn == ActionTurn.PLAY_TILE) {
        	if(getCurrentPlayer().getTileList().isEmpty()) {
        	System.out.println(getCurrentPlayer().getPseudo() + " n'a pas de tuiles à jouer !");
        		nextActionTurn();
        	}
        } else if(actionTurn == ActionTurn.MOVE_PAWNS) {
        	if (!getCurrentPlayer().haveExplorerOnBoard()) {
        		System.out.println(getCurrentPlayer().getPseudo() + " n'a plus d'Explorateur à bouger!");
            	nextActionTurn();
            }
        } else if(actionTurn == ActionTurn.MOVE_MONSTER) {
        	if(!board.isSeaSnakeOnBoard() 
        			&& !board.isSharkOnBoard() 
        			&& !board.isWhaleOnBoard()) {
        		System.out.println("Il n'y a pas de monstres marins en jeu");
            	nextActionTurn();
        	}
        }*/
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
        return this.turnOrder;
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
    
    public void inGame(Hexagon hex) {
    	if(actionTurn == ActionTurn.PLAY_TILE) {
			//Pour test plus facilement ; les 4 prochaines lignes servent a afficher un pion
			Explorer yop = new Explorer(players[turnOrder].getColor(),5);
			hex.addPawn(yop);
			

			// ActionTurn est le changement d'action, à mettre en commentaire pour test
			nextActionTurn();
		}
		else if(actionTurn== ActionTurn.MOVE_PAWNS) {
			if(!hex.getExplorerList().isEmpty() && firstClic == true) {										
				saveHexa = hex;
				//--Choix de l'explorateur avec loik 
				pawnToMove = hex.getExplorerList().get(0);
				//--
				
				pawnToMove.findPath(hex, board, 3, hexagonPairList);
				for(Pair<Hexagon, HexagonListType> p : hexagonPairList) {
					String s;
					switch(p.getRight()) {
						case NORMAL:
							s = "yellow";
							break;
						case BOAT:
							s = "purple";
							break;
						case DEATH:
							s = "red";
							break;
						default:
							s = "white";
							break;
					}
					p.getLeft().setHighlight(resolution, board, true, s);
				}
				
				firstClic = false;	
			}
			else if(firstClic == false) {
				if(hexagonPairList.getLeftList().contains(hex)) {
					pawnToMove.move(saveHexa,hex) ;
					for(Pair<Hexagon, HexagonListType> p : hexagonPairList) {
						p.getLeft().setHighlightColor(null);
						p.getLeft().setHighlight(resolution, board, false, null);
					}
					hexagonPairList.clear();
					saveHexa.displayPawns();
					firstClic = true;
					saveHexa = null;
					// ActionTurn est le changement d'action, à mettre en commentaire pour test
					nextActionTurn();
					
				}								
			}	
		}
		else if(actionTurn== ActionTurn.DISCOVER_TILE){	
			if(hex.getTile() != null) {
				if(hex.getTile().getType() == TilesType.BEACH 
						|| (hex.getTile().getType() == TilesType.FOREST
						&& board.getNbBeach() == 0)
						|| (hex.getTile().getType() == TilesType.MOUNTAINS
						&& board.getNbBeach() == 0
						&& board.getNbForest() == 0)) {
					board.decreaseNbTile(hex.getTile().getType());
					hex.discover(players[turnOrder], board);

					// ActionTurn est le changement d'action, à mettre en commentaire pour test
					//nextActionTurn();
				}
			}
			
		}
		else if(actionTurn== ActionTurn.MOVE_MONSTER){
			if((!hex.getSharkList().isEmpty() 
        			|| !hex.getSeaSnakeList().isEmpty() 
        			|| !hex.getWhaleList().isEmpty())
					&& firstClic == true) {										
				saveHexa = hex;
				System.out.println("bouh");
				//--Choix du monstre avec loik 
				if (!hex.getSharkList().isEmpty()) {
					pawnToMove = hex.getSharkList().get(0);
				} else if (!hex.getSeaSnakeList().isEmpty()) {
					pawnToMove = hex.getSeaSnakeList().get(0);
				} else if (!hex.getWhaleList().isEmpty()) {
					pawnToMove = hex.getWhaleList().get(0);
				}
				//--
				
				pawnToMove.findPath(hex, board, 3, hexagonPairList);
				for(Pair<Hexagon, HexagonListType> p : hexagonPairList) {
					String s;
					switch(p.getRight()) {
						case NORMAL:
							s = "yellow";
							break;
						case DEATH:
							s = "red";
							break;
						default:
							s = "white";
							break;
					}
					p.getLeft().setHighlight(resolution, board, true, s);
				}
				firstClic = false;
				
			}
			else if(firstClic == false) {
				System.out.println("bouh2");
				if(hexagonPairList.getLeftList().contains(hex)) {
					pawnToMove.move(saveHexa, hex) ;
					for(Pair<Hexagon, HexagonListType> p : hexagonPairList) {
						p.getLeft().setHighlightColor(null);
						p.getLeft().setHighlight(resolution, board, false, null);
					}
					hexagonPairList.clear();
					saveHexa.displayPawns();
					firstClic = true;
					saveHexa = null;
					
					// ActionTurn est le changement d'action, à mettre en commentaire pour test
					nextActionTurn();
					turnOrder = (turnOrder + 1) % players.length;
				}
			}
		}
		System.out.println("Joueur :"+ turnOrder + "; " + players[turnOrder].getPseudo());						
		System.out.println(actionTurn + "\n");
			
		
		hex.displayPawns();
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
