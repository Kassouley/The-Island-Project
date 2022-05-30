package fr.mcstudio.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import fr.mcstudio.board.ActionInfo;
import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.board.PlayerInfo;
import fr.mcstudio.enums.ActionTurn;
import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.ExternalPanelState;
import fr.mcstudio.enums.GameState;
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.enums.PawnType;
import fr.mcstudio.enums.TilesType;
import fr.mcstudio.pawns.Boat;
import fr.mcstudio.pawns.Dolphin;
import fr.mcstudio.pawns.EffectPawn;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.Pawn;
import fr.mcstudio.pawns.SeaSnake;
import fr.mcstudio.pawns.Shark;
import fr.mcstudio.pawns.Whale;
import fr.mcstudio.tiles.Tile;
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
        this.resolution = resolution;
        this.contentPane = contentPane;
        this.players = players;
        this.turnNumber = 0;
        this.turnOrder = (int) (Math.random() * players.length);

        // A set comme vous voulez pour effectuer des test sur les differentes actions

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
    private int turnOrder;

    /**
     * 
     */
    private ActionTurn actionTurn;

    private TripletList<Hexagon, Integer, HexagonListType> hexagonTripletList = new TripletList<Hexagon, Integer, HexagonListType>();

    /**
     * 
     */
    public void initializeBoard() {
        playerInfo = new PlayerInfo(resolution);
        contentPane.add(playerInfo);

        board = new Board(this, resolution);
        contentPane.add(board);
        boardClickAction();

        actionInfo = new ActionInfo(this, resolution);
        contentPane.add(actionInfo);
        actionInfoClickAction();

        board.getHexagons()[1][1].getSeaSnakeList().add(new SeaSnake(board));
        board.getHexagons()[1][1].displayPawns(board);
        board.getHexagons()[2][10].getSeaSnakeList().add(new SeaSnake(board));
        board.getHexagons()[2][10].displayPawns(board);
        board.getHexagons()[10][0].getSeaSnakeList().add(new SeaSnake(board));
        board.getHexagons()[10][0].displayPawns(board);
        board.getHexagons()[11][10].getSeaSnakeList().add(new SeaSnake(board));
        board.getHexagons()[11][10].displayPawns(board);
        board.getHexagons()[6][5].getSeaSnakeList().add(new SeaSnake(board));
        board.getHexagons()[6][5].displayPawns(board);
    }

    /**
     * 
     */
    private boolean firstClic = true;

    /**
     * 
     */
    private Pawn pawnToMove;
    
    private JLayeredPane destination;
    
    private Tile usedTile;

    /**
     * 
     */
    private Hexagon saveHexa;

    /**
     * 
     */
    public void boardClickAction() {

        this.board.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {

                if (SwingUtilities.isLeftMouseButton(e)) {
                	if(board.isDisplayExternalPanel()) {
                		
                	}
                    for (int i = 0; i < 13; i++) {
                        for (int j = 0; j < 12; j++) {
                            Hexagon hex = board.getHexagons()[i][j];
                            if (!hex.isVoid()) {
                                if (hex.isInHexagonfloat(resolution, e.getX() - hex.getX(),
                                        e.getY() - hex.getY())) {

                                    if (gameState == GameState.INITIALISATION) {
                                        setAllPawn(hex);
                                    } else if (gameState == GameState.PLAYING) {
                                        if (actionTurn != ActionTurn.MOVE_MONSTER 
                                                || board.getExternalPanel().getPawnType() != null) {
                                            inGame(hex);
                                        }
                                    } else if (gameState == GameState.ENDING) {
                                        endGame();
                                    }

                                    actionInfo.displayActionInfo(getGame());
                                    playerInfo.displayPlayerInfo(getGame(), resolution);
                                }
                            }
                        }
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });

        this.board.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (!board.isDisplayExternalPanel()) {
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

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });

        this.actionInfo.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
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
     * @param players
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
    public int getCurrentPlayerIndex() {
        return (this.turnOrder + this.turnNumber) % players.length;
    }

    /**
     * 
     */
    public Game getGame() {
        return this;
    }

    /**
     * 
     * @param hex
     */
    private void setAllPawn(Hexagon hex) {
        int exit = 0;

        if (hex.isTiles() 
                && hex.getExplorerList().isEmpty() 
                && !getCurrentPlayer().getExplorerList().isEmpty()) {
            hex.addPawn(getCurrentPlayer().getExplorerList().get(0));
            getCurrentPlayer().getCurrentExplorerList().add(getCurrentPlayer().getExplorerList().get(0));
            getCurrentPlayer().getExplorerList().remove(0);
            nextTurn();
        }
            
        else if (hex.isSea() 
                && this.board.isNextToLand(hex) 
                && hex.getBoat() == null
                && getCurrentPlayer().getExplorerList().isEmpty() 
                && !getCurrentPlayer().getBoatToSet().isEmpty()) {
            hex.addPawn(getCurrentPlayer().getBoatToSet().get(0));
            getCurrentPlayer().getBoatToSet().remove(0);
            nextTurn();

            for (int x = 0; x < players.length; x++) {
                if (getCurrentPlayer().getBoatToSet().isEmpty()) {
                    exit++;
                }
            }
        }
        if (exit == players.length) {
            gameState = GameState.PLAYING;
            turnNumber = 0;
        }
          
        hex.displayPawns(board);
    }

    /**
     * 
     */
    public void nextActionTurn() {
        if (this.actionTurn == ActionTurn.MOVE_MONSTER) {
            this.turnNumber++;
        }
        this.pawnToMove = null;
        this.destination = null;
        this.usedTile = null;
        this.board.getExternalPanel().setPawnType(null);

        this.actionTurn = this.actionTurn.next();
        /*
         * if(actionTurn == ActionTurn.PLAY_TILE) {
         * if(getCurrentPlayer().getTileList().isEmpty()) {
         * System.out.println(getCurrentPlayer().getPseudo() +
         * " n'a pas de tuiles à jouer !");
         * nextActionTurn();
         * }
         * } else if(actionTurn == ActionTurn.MOVE_PAWNS) {
         * if (!getCurrentPlayer().haveExplorerOnBoard()) {
         * System.out.println(getCurrentPlayer().getPseudo() +
         * " n'a plus d'Explorateur à bouger!");
         * nextActionTurn();
         * }
         * } else if(actionTurn == ActionTurn.MOVE_MONSTER) {
         * if(!board.isSeaSnakeOnBoard()
         * && !board.isSharkOnBoard()
         * && !board.isWhaleOnBoard()) {
         * System.out.println("Il n'y a pas de monstres marins en jeu");
         * nextActionTurn();
         * }
         * }
         */
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

    public Tile getUsedTile(){
        return this.usedTile;
    }

    public void setUsedTile(Tile usedTile){
        this.usedTile = usedTile;
    }


    /**
     * 
     */

    public void startGame() {

        /*
         * for (int i = 0; i < players.length; i++) {
         * // Afficher message "Pose tes pions"
         * players[(this.turnOrder + i) % players.length]
         * .placeAllExplorers(this.board);
         * players[(this.turnOrder + i) % players.length]
         * .placeBoats(this.board);
         * }
         */
    }

    public void inGame(Hexagon hex) {
    	if(actionTurn == ActionTurn.PLAY_TILE) {
    		inGamePlayTile(hex);
    	}else if (actionTurn == ActionTurn.MOVE_PAWNS) {
        	inGameMovePawn(hex);
        } else if (actionTurn == ActionTurn.DISCOVER_TILE) {
            inGameDiscoverTile(hex);
        } else if (actionTurn == ActionTurn.MOVE_MONSTER) {
    	    if (board.getExternalPanel().getPawnType() != null){ 
                inGameMoveMonster(hex);
            }
        }
        // System.out.println("Joueur :"+ turnOrder + "; " +
        // players[turnOrder].getPseudo());
        // System.out.println(actionTurn + "\n");

        if(hex != null) hex.displayPawns(board);
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

    private void inGamePlayTile(Hexagon hex) {
    	if(usedTile != null) {
    		if(firstClic == true) {
    			saveHexa = hex;
    			EffectPawn effect;
    			pawnToMove = null;
    			switch(usedTile.getEffect()) {    		
        		case BOAT_MOVE:
                	if(hex.getBoat() != null && hex.getBoat().isOwnedBy(getCurrentPlayer())) {
                		pawnToMove = hex.getBoat();
            			pawnToMove.findPath(hex, board, 3, hexagonTripletList);
                	}	
        		break;
        		case DOLPHIN_MOVE: 
        			if(hex.nbExplorerColor(getCurrentPlayer().getColor()) == 1) {
                    	for (Explorer e : hex.getExplorerList()) {
        					if(e.getColor() == getCurrentPlayer().getColor()) {
        						pawnToMove = e;
        						break;
        					}
        				}                   	
                    }
        			if (pawnToMove == null) {
                        if (board.getExternalPanel().getSelection() != null) {

                            pawnToMove = (Pawn)board.getExternalPanel().getSelection();
                            board.getExternalPanel().setSelection(null);
                            board.getExternalPanel().setClickedHex(null);
                            inGame(hex);
                        } else if (hex.containsExplorerColor(getCurrentPlayer().getColor())) {
                            board.getExternalPanel().setClickedHex(hex);
                            board.setDisplayExternalPanel(true);
                            board.getExternalPanel().setExternalPanelState(ExternalPanelState.PAWNPANEL);

                        }
                    } else {
                    	Dolphin d = new Dolphin();
                        d.findPath(hex, board, 3, hexagonTripletList);                  
                    }
        			
        			
        		break;
        		case SHARK_LOST:
        			if(!hex.getSharkList().isEmpty()) {
        				pawnToMove = hex.getSharkList().get(0);
            			effect = (EffectPawn) pawnToMove;
            			effect.findPathEffect(hex, board, hexagonTripletList);
        			}
        			
        		break;
        		case SEASNAKE_LOST: 
        			if(!hex.getSeaSnakeList().isEmpty()) {
        				pawnToMove = hex.getSeaSnakeList().get(0);
            			effect = (EffectPawn) pawnToMove;
            			effect.findPathEffect(hex, board, hexagonTripletList);
        			}
        		break;
        		case WHALE_LOST: 
        			if(!hex.getWhaleList().isEmpty()) {
        				pawnToMove = hex.getWhaleList().get(0);
            			effect = (EffectPawn) pawnToMove;
            			effect.findPathEffect(hex, board, hexagonTripletList);
        			}
        		break;
        		default: break;
        		}
    			if(pawnToMove != null) {
    				for (Triplet<Hexagon, Integer, HexagonListType> p : hexagonTripletList) {
                        String s;
                        switch (p.getRight()) {
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
    		else if(firstClic == false  && pawnToMove != null ) {
    			if(hexagonTripletList.getLeftList().contains(hex)) {
        			pawnToMove.move(saveHexa, hex);
        			for (Triplet<Hexagon, Integer, HexagonListType> p : hexagonTripletList) {
                        p.getLeft().setHighlightColor(null);
                        p.getLeft().setHighlight(resolution, board, false, null);
                    }
        			saveHexa.displayPawns(board);
        			getCurrentPlayer().getTileList().remove(usedTile);
        			saveHexa = null;
        			firstClic = true;
        			pawnToMove = null;
        			usedTile = null;
        			nextActionTurn();
        		}
    			else {
    				for (Triplet<Hexagon, Integer, HexagonListType> p : hexagonTripletList) {
                        p.getLeft().setHighlightColor(null);
                        p.getLeft().setHighlight(resolution, board, false, null);
                    }
    				saveHexa = null;
    				firstClic = true;
    				pawnToMove = null;
    			}
    		}
    	}
    }
    
    /**
     * 
     * @param hex
     */
    private void inGameMovePawn(Hexagon hex) {
    	if (hex != null && (!hex.getExplorerList().isEmpty() || hex.getBoat() != null)  && firstClic == true) {
    		Color plColor = getCurrentPlayer().getColor();
            saveHexa = hex;
            if(hex.nbExplorerColor(plColor) + 
            		(hex.getBoat() != null 
            		&& hex.getBoat().isOwnedBy(getCurrentPlayer())?1:0) +
            		(hex.getBoat() != null?hex.getBoat()
            				.nbExplorerColor(plColor):0) == 1) {
            	
            	if(hex.getBoat() != null 
            			&& hex.getBoat().isOwnedBy(getCurrentPlayer())) {
            		
            		pawnToMove = hex.getBoat();
            	}
            	else if(hex.getBoat() != null && hex.getBoat()
        				.nbExplorerColor(plColor) != 0) {
            		
            		for (Explorer e : hex.getBoat().getExplorerList()) {
    					if(e.getColor() == plColor) {
    						pawnToMove = e;
    						break;
    					}
            		}
            	}
            	else {
                	for (Explorer e : hex.getExplorerList()) {
    					if(e.getColor() == plColor) {
    						pawnToMove = e;
    						break;
    					}
    				}
            	}
            }
            
            if (pawnToMove == null) {
                if (board.getExternalPanel().getSelection() != null) {

                    pawnToMove = (Pawn)board.getExternalPanel().getSelection();
                    board.getExternalPanel().setSelection(null);
                    board.getExternalPanel().setClickedHex(null);
                    inGame(hex);
                } else if (hex.containsExplorerColor(plColor) 
                		|| (hex.getBoat() != null 
                		&& hex.getBoat().isOwnedBy(getCurrentPlayer()))) {
                	
                    board.getExternalPanel().setClickedHex(hex);
                    board.setDisplayExternalPanel(true);
                    board.getExternalPanel().setExternalPanelState(ExternalPanelState.PAWNPANEL);

                }
            } else {
                pawnToMove.findPath(hex, board, getCurrentPlayer().getMoveLeft(), hexagonTripletList);
                for (Triplet<Hexagon, Integer, HexagonListType> p : hexagonTripletList) {
                    String s;
                    switch (p.getRight()) {
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
        } else if (firstClic == false) {
            if (hexagonTripletList.getLeftList().contains(hex)) {
            	if(hex == saveHexa && hex.isSea() && hex.getBoat() != null 
            			&& pawnToMove instanceof Explorer 
            			&& ((Explorer)pawnToMove).getStatus() != ExplorerStatus.ONBOAT) {
            		destination = hex.getBoat();

            	} else if(hex.getBoat() == null || !saveHexa.isTiles()) {
            		destination = hex;
            	}
            	
            	if(destination == null 
            			&& (saveHexa.isTiles() 
            			|| (saveHexa.getBoat() != null && saveHexa.getBoat().getExplorerList().contains(pawnToMove))) 
            			&& hex.getBoat() != null) {
            		if (board.getExternalPanel().getSelection() != null) {
                        destination = board.getExternalPanel().getSelection();
                        board.getExternalPanel().setSelection(null);
                        board.getExternalPanel().setClickedHex(null);
                        inGame(hex);
            		} else {
                		board.getExternalPanel().setClickedHex(hex);
                        board.setDisplayExternalPanel(true);
                        board.getExternalPanel().setExternalPanelState(ExternalPanelState.BOATORSEA);
            		}
            	} else if(!board.isDisplayExternalPanel()){
                    if(destination == hex 
                    		&& ((pawnToMove instanceof Explorer 
                    				&& ((Explorer)pawnToMove).getStatus() != ExplorerStatus.ONBOAT)
                    		|| pawnToMove instanceof Boat)) {
                		pawnToMove.move(saveHexa, hex);

                    } else if(destination == hex 
                    		&& ((Explorer)pawnToMove).getStatus() == ExplorerStatus.ONBOAT) {

                    	((Explorer) pawnToMove).move(hex.getBoat(), hex);
                    } else if(destination == hex.getBoat()) {

                    	// A modifier avec monter sur bateau
                    	((Explorer) pawnToMove).move(saveHexa, hex.getBoat(), hex);
                    	//
                    }
                    
                    for(int comp = 0; comp < hexagonTripletList.getLeftList().size();comp++) {
    					if(hexagonTripletList.getLeftList().get(comp) == hex) {
    						getCurrentPlayer().setMoveLeft(getCurrentPlayer().getMoveLeft()-hexagonTripletList.getMiddleList().get(comp));
    						pawnToMove.setMovePoint(pawnToMove.getMovePoint() -hexagonTripletList.getMiddleList().get(comp));
    					}
    				}
                    for (Triplet<Hexagon, Integer, HexagonListType> p : hexagonTripletList) {
                        p.getLeft().setHighlightColor(null);
                        p.getLeft().setHighlight(resolution, board, false, null);
                    }
                    hexagonTripletList.clear();
                    saveHexa.displayPawns(board);
                    firstClic = true;
                    saveHexa = null;
                    pawnToMove = null;
                    destination = null;                  
                    if(getCurrentPlayer().getMoveLeft() == 0) {
    					nextActionTurn();
    					getCurrentPlayer().resetMovePointExplorer();
    				}
            	}
            }
            else {
				for(Triplet<Hexagon, Integer, HexagonListType> p : hexagonTripletList) {
					p.getLeft().setHighlightColor(null);
					p.getLeft().setHighlight(resolution, board, false, null);
				}
				firstClic = true;
				saveHexa = null;
				pawnToMove = null;
				destination = null;
				hexagonTripletList.clear();
			}
        }
    }
    
    /**
     * 
     * @param hex
     */
    private void inGameDiscoverTile(Hexagon hex) {
    	if (hex != null && hex.getTile() != null) {
            if ((hex.getTile().getType() == TilesType.BEACH 
                    || (hex.getTile().getType() == TilesType.FOREST
                            && board.getNbBeach() == 0)
                    || (hex.getTile().getType() == TilesType.MOUNTAINS
                            && board.getNbBeach() == 0
                            && board.getNbForest() == 0))){
            	if(board.canRemoveOutOfSea(hex, hex.getTile().getType()) 
            			|| board.isNextToSea(hex)) {
                    board.decreaseNbTile(hex.getTile().getType());
                    hex.discover(getCurrentPlayer(), board);

                    // ActionTurn est le changement d'action, � mettre en commentaire pour test
                    nextActionTurn();
            	}
            }
        }
    }
    
    /**
     * 
     * @param hex
     */
    public void inGameMoveMonster(Hexagon hex) {
    	if (board.getExternalPanel().getPawnType() == null) {
        	board.setDisplayExternalPanel(true);
        	board.getExternalPanel().setExternalPanelState(ExternalPanelState.DICEPANEL);

        } else if(!board.isDisplayExternalPanel()) {
        	if(board.getExternalPanel().getPawnType() == PawnType.SHARK) {
        		if(!board.isSharkOnBoard()) {
        			System.out.println("Il n'y a pas de requins !");
                    board.getExternalPanel().setPawnType(null);
                    nextTurn();
        		} else if(hex != null && hex.getSharkList().isEmpty() && firstClic == true) {
        			System.out.println("Il n'y a pas de requins sur cette case !");
        			return;
        		}
        	} else if(board.getExternalPanel().getPawnType() == PawnType.SEASNAKE) {
        		if(!board.isSeaSnakeOnBoard()) {
        			System.out.println("Il n'y a pas de serpents de mers !");
                    board.getExternalPanel().setPawnType(null);
                    nextTurn();
        		} else if(hex != null && hex.getSeaSnakeList().isEmpty() && firstClic == true) {
        			System.out.println("Il n'y a pas de serpents de mers sur cette case !");
        			return;
        		}
        	} else if(board.getExternalPanel().getPawnType() == PawnType.WHALE) {
        		if(!board.isWhaleOnBoard()) {
        			System.out.println("Il n'y a pas de baleines !");
                    board.getExternalPanel().setPawnType(null);
                    nextTurn();
        		} else if(hex != null && hex.getWhaleList().isEmpty() && firstClic == true) {
        			System.out.println("Il n'y a pas de baleines sur cette case !");
        			return;
        		}
        	}
        	if (hex != null && firstClic == true) {
        		saveHexa = hex;
        		// --Choix du monstre avec loik
        		switch(board.getExternalPanel().getPawnType()) {
        			case SHARK:
	                    pawnToMove = hex.getSharkList().get(0);
        				break;
        			case SEASNAKE:
	                    pawnToMove = hex.getSeaSnakeList().get(0);
        				break;
        			case WHALE:
	                    pawnToMove = hex.getWhaleList().get(0);
        				break;
					default:
						break;
        		}
                // --

                pawnToMove.findPath(hex, board, 3, hexagonTripletList);
                for (Triplet<Hexagon, Integer, HexagonListType> p : hexagonTripletList) {
                    String s;
                    switch (p.getRight()) {
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

            } else if (firstClic == false) {
                if (hexagonTripletList.getLeftList().contains(hex)) {
                    pawnToMove.move(saveHexa, hex);
                    for (Triplet<Hexagon, Integer, HexagonListType> p : hexagonTripletList) {
                        p.getLeft().setHighlightColor(null);
                        p.getLeft().setHighlight(resolution, board, false, null);
                    }
                    hexagonTripletList.clear();
                    saveHexa.displayPawns(board);
                    firstClic = true;
                    saveHexa = null;
                    pawnToMove = null;
                    board.getExternalPanel().setPawnType(null);

                    // ActionTurn est le changement d'action, à mettre en commentaire pour test
                    nextTurn();
                } else {
    				for(Triplet<Hexagon, Integer, HexagonListType> p : hexagonTripletList) {
    					p.getLeft().setHighlightColor(null);
    					p.getLeft().setHighlight(resolution, board, false, null);
    				}
    				firstClic = true;
    				saveHexa = null;
    				pawnToMove = null;
    				destination = null;
    				hexagonTripletList.clear();
                }
            
            }
        }
    }
    
    /**
	 * @return the actionInfo
	 */
	public ActionInfo getActionInfo() {
		return actionInfo;
	}
}
