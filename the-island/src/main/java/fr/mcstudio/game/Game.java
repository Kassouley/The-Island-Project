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
import fr.mcstudio.enums.ExternalPanelState;
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.GameState;
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.enums.TilesEffect;
import fr.mcstudio.enums.TilesType;
import fr.mcstudio.pawns.Boat;
import fr.mcstudio.pawns.EffectPawn;
import fr.mcstudio.pawns.Pawn;
import fr.mcstudio.pawns.SeaSnake;
import fr.mcstudio.pawns.Shark;
import fr.mcstudio.pawns.Whale;
import fr.mcstudio.util.Triplet;
import fr.mcstudio.util.TripletList;

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
    
    private TripletList<Hexagon,Integer,HexagonListType> hexagonTripletList = new TripletList<Hexagon, Integer, HexagonListType>();
    
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

				if(SwingUtilities.isLeftMouseButton(e)) {
					for (int i = 0; i < 13; i++) {
						for (int j = 0; j < 12; j++) {
							Hexagon hex = board.getHexagons()[i][j];
							if (!hex.isVoid()) {
								if (hex.isInHexagonfloat(resolution, e.getX() - hex.getX(), 
										e.getY() - hex.getY())) {
									if(gameState == GameState.INITIALISATION) {						            
										int exit = 0;
								        if(hex.getTile() != null && hex.getExplorerList().isEmpty()) {
                          
								          hex.addPawn(players[turnOrder].getExplorerList().get(0));
								          players[turnOrder].getExplorerList().remove(0);
								          turnOrder = (turnOrder + 1) % players.length;
								          for(int x = 0 ; x < players.length ; x++) {
								            if(players[x].getExplorerList().size() == 0) {
								            exit ++;
								            }
								          }
								          if(exit == players.length) {
								            Shark s = new Shark();
								            Whale w = new Whale();
								            SeaSnake ss = new SeaSnake();
								            Boat b = new Boat();
								            hex.addPawn(s);
								            hex.addPawn(w);
								            hex.addPawn(ss);
								            hex.addPawn(b);
								            gameState = GameState.PLAYING;
								          }
								        }

										hex.displayPawns();
										
									} else if(gameState == GameState.PLAYING) {
										inGame(hex);
									} else if(gameState == GameState.ENDING) {
										endGame();
									}
								}
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
				if(!board.isDisplayExternalPanel()) {
					for (int i = 0; i < 13; i++) {
						for (int j = 0; j < 12; j++) {
							Hexagon hex = board.getHexagons()[i][j];
							if (!hex.isVoid()) {
								if (hex.isInHexagonfloat(resolution, e.getX() - hex.getX(), e.getY() - hex.getY())) {
									if (!hex.isHighlight())
										hex.setHighlight(resolution, board, true, "white");
	
								} else {
									if (hex.isHighlight() && hex.getHighlightColor() == null) {
										hex.setHighlight(resolution, board, false, null);
									}
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
        if(players[turnOrder].getTileList().size() > 0) {
          System.out.println(players[turnOrder].getTileList().get(0).getEffect());
          //players[turnOrder].getTileList().get(0).applyEffect(hex, board);
          // Test de tuile
          if(firstClic == true) {	
            int distance = 0;
            saveHexa = hex;
            pawnToMove = null;
            EffectPawn effect = null; 
            if (!hex.getSharkList().isEmpty() && 
                players[turnOrder].getTileList().get(0).getEffect() == TilesEffect.SHARK_LOST) {
              pawnToMove = hex.getSharkList().get(0);
              effect = (EffectPawn) pawnToMove;

            } 
            else if (!hex.getSeaSnakeList().isEmpty() && 
                players[turnOrder].getTileList().get(0).getEffect() == TilesEffect.SEASNAKE_LOST) {
              pawnToMove = hex.getSeaSnakeList().get(0);
              effect = (EffectPawn) pawnToMove;
            } 
            else if (!hex.getWhaleList().isEmpty() && 
                players[turnOrder].getTileList().get(0).getEffect() == TilesEffect.WHALE_LOST) {
              pawnToMove = hex.getWhaleList().get(0);
              effect = (EffectPawn) pawnToMove;
            }
            else if (!hex.getExplorerList().isEmpty() && 
                players[turnOrder].getTileList().get(0).getEffect() == TilesEffect.DOLPHIN_MOVE) {
              if(hex.getExplorerList().get(0).getStatus() == ExplorerStatus.SWIMMER) {
                pawnToMove = hex.getExplorerList().get(0);
              }
            }
            else if (hex.getBoat() != null && 
                players[turnOrder].getTileList().get(0).getEffect() == TilesEffect.BOAT_MOVE) {
              pawnToMove = hex.getBoat();
            }


            if(pawnToMove != null) {
            	switch(players[turnOrder].getTileList().get(0).getEffect()) {
            	case WHALE_LOST :
            	case SEASNAKE_LOST :
            	case SHARK_LOST :
            		effect.findPathEffect(hex, board, hexagonTripletList);
            		break;
            	case BOAT_MOVE :
            	case DOLPHIN_MOVE :
            		 pawnToMove.findPath(hex, board, 3, hexagonTripletList);
            		 break;
            	default :
            		break;
            	}
             
              for(Triplet<Hexagon,Integer,HexagonListType> p : hexagonTripletList) {
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

          }
          else if(firstClic == false) {
            if(hexagonTripletList.getLeftList().contains(hex)) {
              pawnToMove.move(saveHexa,hex) ;
              for(Triplet<Hexagon,Integer,HexagonListType> p : hexagonTripletList) {
                p.getLeft().setHighlightColor(null);
                p.getLeft().setHighlight(resolution, board, false, null);
              }
              hexagonTripletList.clear();
              saveHexa.displayPawns();
              firstClic = true;
              saveHexa = null;
              pawnToMove = null;
              players[turnOrder].getTileList().remove(0);
              // ActionTurn est le changement d'action, à mettre en commentaire pour test
              nextActionTurn();

            }
            else {
              firstClic = true;
              saveHexa = null;
              for(Triplet<Hexagon,Integer,HexagonListType> p : hexagonTripletList) {
                p.getLeft().setHighlightColor(null);
                p.getLeft().setHighlight(resolution, board, false, null);
              }
            }

          }

        }
        else {
          // ActionTurn est le changement d'action, à mettre en commentaire pour test
          nextActionTurn();
        }
    }
		else if(actionTurn== ActionTurn.MOVE_PAWNS) {
			if(!hex.getExplorerList().isEmpty() && firstClic == true) {										
				saveHexa = hex;
				if(!board.isDisplayExternalPanel() && pawnToMove == null) {
					if(board.getExternalPanel().getPawn() != null) {
						pawnToMove = board.getExternalPanel().getPawn();
						inGame(hex);
					} else if(hex.containsExplorerColor(getCurrentPlayer().getColor())) {
						board.getExternalPanel().setClickedHex(hex);
						board.setDisplayExternalPanel(true);
						board.getExternalPanel().setExternalPanelState(ExternalPanelState.PAWNPANEL);
						
					}
				} else {
					pawnToMove.findPath(hex, board, 3, hexagonTripletList);
					for(Triplet<Hexagon, Integer, HexagonListType> p : hexagonTripletList) {
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
			}
			else if(firstClic == false) {
				if(hexagonTripletList.getLeftList().contains(hex)) {
					pawnToMove.move(saveHexa,hex) ;
					for(Triplet<Hexagon, Integer, HexagonListType> p : hexagonTripletList) {
						p.getLeft().setHighlightColor(null);
						p.getLeft().setHighlight(resolution, board, false, null);
					}
					hexagonTripletList.clear();
					saveHexa.displayPawns();
					firstClic = true;
					saveHexa = null;
          pawnToMove = null;
					// ActionTurn est le changement d'action, � mettre en commentaire pour test
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

					// ActionTurn est le changement d'action, � mettre en commentaire pour test
					nextActionTurn();
				}
			}
			
		}
		else if(actionTurn== ActionTurn.MOVE_MONSTER){
			if((!hex.getSharkList().isEmpty() 
        			|| !hex.getSeaSnakeList().isEmpty() 
        			|| !hex.getWhaleList().isEmpty())
					&& firstClic == true) {										
				saveHexa = hex;
				//--Choix du monstre avec loik 
				if (!hex.getSharkList().isEmpty()) {
					pawnToMove = hex.getSharkList().get(0);
				} else if (!hex.getSeaSnakeList().isEmpty()) {
					pawnToMove = hex.getSeaSnakeList().get(0);
				} else if (!hex.getWhaleList().isEmpty()) {
					pawnToMove = hex.getWhaleList().get(0);
				}
				//--
				
				pawnToMove.findPath(hex, board, 3, hexagonTripletList);
				for(Triplet<Hexagon, Integer, HexagonListType> p : hexagonTripletList) {
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
				if(hexagonTripletList.getLeftList().contains(hex)) {
					pawnToMove.move(saveHexa, hex) ;
					for(Triplet<Hexagon, Integer, HexagonListType> p : hexagonTripletList) {
						p.getLeft().setHighlightColor(null);
						p.getLeft().setHighlight(resolution, board, false, null);
					}
					hexagonTripletList.clear();
					saveHexa.displayPawns();
					firstClic = true;
					saveHexa = null;
					
					// ActionTurn est le changement d'action, à mettre en commentaire pour test
					nextActionTurn();
					turnOrder = (turnOrder + 1) % players.length;
				}
				else {
			          firstClic = true;
			          saveHexa = null;
			          for(Triplet<Hexagon,Integer,HexagonListType> p : hexagonTripletList) {
			            p.getLeft().setHighlightColor(null);
			            p.getLeft().setHighlight(resolution, board, false, null);
			          }
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
