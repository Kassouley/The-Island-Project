package fr.mcstudio.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import fr.mcstudio.board.ActionInfo;
import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.board.PlayerInfo;
import fr.mcstudio.board.Tile;
import fr.mcstudio.enums.ActionTurn;
import fr.mcstudio.enums.AnimationType;
import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.ExternalPanelState;
import fr.mcstudio.enums.GameState;
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.enums.PawnType;
import fr.mcstudio.enums.TilesEffect;
import fr.mcstudio.enums.TilesType;
import fr.mcstudio.menu.Accueil;
import fr.mcstudio.pawns.Boat;
import fr.mcstudio.pawns.Dolphin;
import fr.mcstudio.pawns.EffectPawn;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.Pawn;
import fr.mcstudio.pawns.SeaSnake;
import fr.mcstudio.util.Triplet;
import fr.mcstudio.util.TripletList;

/**
 * The Game class is used to store information about a game.
 */
public class Game {

    // The constructor of the Game class.
    public Game(int resolution, Accueil accueil, ArrayList<Player> players) {
        this.resolution = resolution;
        this.accueil = accueil;
        this.contentPane = accueil.getLayeredPane();
        this.players = players;
        this.turnNumber = 0;
        this.turnOrder = (int) (Math.random() * players.size());
        this.actionTurn = ActionTurn.PLAY_TILE;
        this.gameState = GameState.INITIALISATION;

        initializeBoard();
        actionInfo.displayActionInfo(getGame());
        playerInfo.displayPlayerInfo(getGame(), resolution);
    }

    // Creating a private variable called accueil of type Accueil.
    private Accueil accueil;

    // Creating a variable called board that is of type Board.
    private Board board;

    // Creating a new instance of the PlayerInfo class.
    private PlayerInfo playerInfo;

	// Creating a new instance of the ActionInfo class.
    private ActionInfo actionInfo;
    
	// Creating a JLayeredPane object called contentPane.
    private JLayeredPane contentPane;

    // Creating a variable called gameState of type GameState.
    private GameState gameState;

    // Declaring a variable called resolution and initializing it to 0.
    private int resolution;

    // Creating an ArrayList of Player objects.
    private ArrayList<Player> players;

    // Declaring a variable called turnNumber and initializing it to 0.
    private int turnNumber;

    // Declaring a private variable called turnOrder.
    private int turnOrder;

    // Creating a new instance of the ActionTurn class.
    private ActionTurn actionTurn;
    
    // Creating a boolean variable called firstClic and setting it to true.
    private boolean firstClic = true;

    // Declaring a variable called pawnToMove of type Pawn.
    private Pawn pawnToMove;
    
    // Creating a list of booleans.
    private List<Boolean> checkJ = new ArrayList<Boolean>();
    
    // Creating a list of booleans called playJ.
    private List<Boolean> playJ = new ArrayList<Boolean>();
    
    // Creating a JLayeredPane object called destination.
    private JLayeredPane destination;
    
    // Creating a private variable called usedTile.
    private Tile usedTile;

    // Creating a private variable called saveHexa.
    private Hexagon saveHexa;

    // Creating a new TripletList object.
    private TripletList<Hexagon, Integer, HexagonListType> 
    hexagonTripletList = new TripletList<Hexagon, Integer, HexagonListType>();

    /**
     * It creates a new board and initialize it.
     * It adds a SeaSnake to a Hexagon, and then displays the pawns on the Hexagon
     */
    public void initializeBoard() {
        playerInfo = new PlayerInfo(resolution);
        contentPane.add(playerInfo);

        board = new Board(this, resolution);
        contentPane.add(board);
        boardClickAction();

        actionInfo = new ActionInfo(this, resolution);
        contentPane.add(actionInfo);

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
     * It's a mouse listener that listens a click on the board
     * It does all the actions that are needed when a click is done on the board
     * like moving a pawn, displaying the action info, etc.
     */
    public void boardClickAction() {

        this.board.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {

                if (SwingUtilities.isLeftMouseButton(e)) {
                	if(board.isDisplayExternalPanel()
                			&& board.getExternalPanel()
                			.getExternalPanelState() != 
                			ExternalPanelState.BOARDINGPANEL
                			&& board.getExternalPanel()
                			.getExternalPanelState() != 
                			ExternalPanelState.TILEEFFECTDEFENSEPANEL) {

        				board.setDisplayExternalPanel(false);
                        actionInfo.displayActionInfo(getGame());
                        playerInfo.displayPlayerInfo(getGame(), resolution);
                        if(board.getExternalPanel()
                        		.getExternalPanelState() != ExternalPanelState
                        		.ANIMATIONPANEL)
                        	inGame(null);
        				board.getExternalPanel()
        				.setExternalPanelState(ExternalPanelState.VOID);
                	} else if(!board.isDisplayExternalPanel()){

                		for (int i = 0; i < 13; i++) {
                            for (int j = 0; j < 12; j++) {
                                Hexagon hex = board.getHexagons()[i][j];
                                if (!hex.isVoid()) {
                                    if (hex.isInHexagonfloat(resolution, 
                                    		e.getX() - hex.getX(),
                                            e.getY() - hex.getY())) {

                                        actionInfo.displayActionInfo(
                                        		getGame());
                                        playerInfo.displayPlayerInfo(getGame(), 
                                        		resolution);
                                        if (gameState == GameState
                                        		.INITIALISATION) {
                                            setAllPawn(hex);
                                        } else if (gameState == GameState
                                        		.PLAYING) {
                                            if (actionTurn != ActionTurn
                                            		.MOVE_MONSTER 
                                                    || board.getExternalPanel()
                                                    .getPawnType() != null) {
                                                inGame(hex);
                                            }
                                        } else if (gameState == GameState
                                        		.ENDING) {
                                            endGame();
                                        }

                                    }
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
                                if (hex.isInHexagonfloat(resolution, 
                                		e.getX() - hex.getX(), 
                                		e.getY() - hex.getY())) {
                                    if (!hex.isHighlight())
                                        hex.setHighlight(resolution, 
                                        		board, 
                                        		true, 
                                        		"white");

                                } else {
                                    if (hex.isHighlight() 
                                    		&& hex
                                    		.getHighlightColor() == null) {
                                        hex.setHighlight(resolution, 
                                        		board, 
                                        		false, 
                                        		null);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * This function sets the board of the game
     * 
     * @param board The board that the player is playing on.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * This function returns the board object
     * 
     * @return The board object.
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * This function returns the players arraylist
     * 
     * @return The players arraylist.
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * This function increments the turn number, resets the move points of the current player and the
     * boat, and sets the action turn to play tile
     */
    public void nextTurn() {
        this.turnNumber++;
		this.getCurrentPlayer().resetMovePointExplorer();
		this.resetMovePointBoat();
        this.getCurrentPlayer().setMoveLeft(3);
        this.actionTurn = ActionTurn.PLAY_TILE;
    }

    /**
     * "Get the player whose turn it is by adding the turn order to the turn number and modding it by
     * the number of players."
     * 
     * The turn order is the index of the player who goes first. The turn number is the number of turns
     * that have passed
     * 
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return this.players.get((this.turnOrder + this.turnNumber) % 
        		players.size());
    }

    /**
     * "The current player index is the turn order plus the turn number, modulo the number of players."
     * 
     * The turn order is the index of the player who goes first. The turn number is the number of turns
     * that have passed. The modulo operator (%) returns the remainder of a division
     * 
     * @return The current player's index in the players array.
     */
    public int getCurrentPlayerIndex() {
        return (this.turnOrder + this.turnNumber) % players.size();
    }

    /**
     * It returns the game object
     * 
     * @return The game object.
     */
    public Game getGame() {
        return this;
    }

    /**
     * This function returns the current game state
     * 
     * @return The gameState variable is being returned.
     */
    public GameState getGameState() {
        return this.gameState;
    }
    
    /**
     * This function sets the game state to the game state passed in
     * 
     * @param gameState The current state of the game.
     */
    public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

    /**
     * It's a function that sets all the pawns on the board at the beginning of the game
     * 
     * @param hex the hexagon that was clicked
     */
    private void setAllPawn(Hexagon hex) {
        int exit = 0;

        if (hex.isTiles() 
                && hex.getExplorerList().isEmpty() 
                && !getCurrentPlayer().getExplorerList().isEmpty()) {
            hex.addPawn(getCurrentPlayer().getExplorerList().get(0));
            getCurrentPlayer().getCurrentExplorerList()
            		.add(getCurrentPlayer().getExplorerList().get(0));
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

            for (int x = 0; x < players.size(); x++) {
                if (getCurrentPlayer().getBoatToSet().isEmpty()) {
                    exit++;
                }
            }
        }
        if (exit == players.size()) {
            gameState = GameState.PLAYING;
            actionInfo.displayActionInfo(getGame());
            playerInfo.displayPlayerInfo(getGame(), resolution);
            turnNumber = 0;
        }

    	actionInfo.displayActionInfo(getGame());
        playerInfo.displayPlayerInfo(getGame(), resolution);
        hex.displayPawns(board);
    }

    
    /**
     * Turn to the next action turn
     * If the action turn is MOVE_MONSTER, then increment the turn number
     */
    public void nextActionTurn() {
        if (this.actionTurn == ActionTurn.MOVE_MONSTER) {
            this.turnNumber++;
        }
        this.pawnToMove = null;
        this.destination = null;
        this.usedTile = null;
        this.board.getExternalPanel().setPawnType(null);
        if(isEnd()) {
        	gameState = GameState.ENDING;
        }
        this.actionTurn = this.actionTurn.next();
    }


    /**
     * This function returns the action turn of the player
     * 
     * @return The actionTurn variable is being returned.
     */
    public ActionTurn getActionTurn() {
        return this.actionTurn;
    }

    /**
     * This function returns the turn order of the player
     * 
     * @return The turnOrder variable is being returned.
     */
    public int getTurnOrder() {
        return this.turnOrder;
    }

    /**
     * This function returns the turn number
     * 
     * @return The turn number.
     */
    public int getTurn() {
        return this.turnNumber;
    }

    /**
     * This function returns the current round number by dividing the current turn number by the number
     * of players.
     * 
     * @return The turn number divided by the size of the players array.
     */
    public int getRound() {
        return this.turnNumber / players.size();
    }

    /**
     * This function returns the tile used
     * 
     * @return The usedTile variable is being returned.
     */
    public Tile getUsedTile(){
        return this.usedTile;
    }

    /**
     * This function sets the usedTile variable to the tile that is passed in
     * 
     * @param usedTile The tile that the player is currently using.
     */
    public void setUsedTile(Tile usedTile){
        this.usedTile = usedTile;
    }

    /**
     * If the actionTurn is playing a tile, then play a tile. If the actionTurn is moving a pawn, then move a
     * pawn. If the actionTurn is discovering a tile, then discover a tile. If the actionTurn is moving a
     * monster, then move a monster
     * 
     * @param hex the hexagon that was clicked
     */
    public void inGame(Hexagon hex) {
    	if(actionTurn == ActionTurn.PLAY_TILE) {
    		inGamePlayTile(hex);
    	} else if (actionTurn == ActionTurn.MOVE_PAWNS) {
        	inGameMovePawn(hex);
        } else if (actionTurn == ActionTurn.DISCOVER_TILE) {
            inGameDiscoverTile(hex);
        } else if (actionTurn == ActionTurn.MOVE_MONSTER) {
    	    if (board.getExternalPanel().getPawnType() != null){
                inGameMoveMonster(hex);
            }
        }

        if(hex != null) {
             hex.displayPawns(board);
        }
    }

    /**
     * "If any player has an explorer on the board, return false, otherwise return true."
     * 
     * The function is called "isEnd" and it returns a boolean. It takes no parameters
     * 
     * @return The method isEnd() returns a boolean value.
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
     * End the game and go to menu
     */
    public void endGame() {
        accueil.reinitialize();
        accueil.welcomeMenu();
        
    }

    /**
     * The function is called when a player clicks on a tile in his hand. It checks if the tile is
     * usable and if so, he uses it and the tile is remove from the player's hand
     * 
     * @param hex the hexagon that was clicked
     */
    private void inGamePlayTile(Hexagon hex) {
    	if(usedTile != null) {
    		if(firstClic == true) {
    			saveHexa = hex;
    			EffectPawn effect;
    			switch(usedTile.getEffect()) {    		
        		case BOAT_MOVE:
        			
                	if(hex.getBoat() != null && hex.getBoat()
                			.isOwnedBy(getCurrentPlayer())) {
                		pawnToMove = hex.getBoat();
            			pawnToMove.findPath(hex, board, 3, hexagonTripletList);
                	}	
                	board.getExternalPanel()
                			.setAnimationType(AnimationType.WIND);
        	        board.setDisplayExternalPanel(true);
        	        board.getExternalPanel()
        	        		.setExternalPanelState(ExternalPanelState
        	        				.ANIMATIONPANEL);
        		break;
        		case DOLPHIN_MOVE: 
        			
        			if(hex.nbExplorerColor(getCurrentPlayer()
        					.getColor()) == 1) {
                    	for (Explorer e : hex.getExplorerList()) {
        					if(e.getColor() == getCurrentPlayer()
        							.getColor() 
        							&& e.getStatus() == ExplorerStatus
        							.SWIMMER) {
        						pawnToMove = e;
        						break;
        					}
        				}                   	
                    }
        			if (pawnToMove == null) {
                        if (board.getExternalPanel()
                        		.getSelection() != null) {         	
                            pawnToMove = (Pawn)board.getExternalPanel()
                            		.getSelection();
                            board.getExternalPanel().setSelection(null);
                            board.getExternalPanel().setClickedHex(null);
                            inGame(hex);
                        } else if (hex.containsExplorerColor(getCurrentPlayer()
                        		.getColor())) {
                            board.getExternalPanel().setClickedHex(hex);
                            board.setDisplayExternalPanel(true);
                            board.getExternalPanel()
                            		.setExternalPanelState(ExternalPanelState
                            				.PAWNPANEL);
                        }
                    } else {
                    	Dolphin d = new Dolphin();
                        d.findPath(hex, board, 3, hexagonTripletList); 
                        board.getExternalPanel()
                        		.setAnimationType(AnimationType
                        				.DOLPHIN_SUMMON);
            	        board.setDisplayExternalPanel(true);
            	        board.getExternalPanel()
            	        		.setExternalPanelState(ExternalPanelState
            	        				.ANIMATIONPANEL);
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
    				for (Triplet<Hexagon, Integer, HexagonListType> p : 
    						hexagonTripletList) {
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

                    checkJ.clear();
                    playJ.clear();
        			defWithTile(hex);
        			
        			for (Triplet<Hexagon, Integer, HexagonListType> p : 
        					hexagonTripletList) {
                        p.getLeft().setHighlightColor(null);
                        p.getLeft().setHighlight(resolution, board, false, null);
                    }
        			saveHexa.displayPawns(board);
        			getCurrentPlayer().getTileList().remove(usedTile);
        			saveHexa = null;
        			firstClic = true;
        			pawnToMove = null;
        			usedTile = null;
        			if(board.isDisplayExternalPanel()) {
                        actionInfo.displayActionInfo(getGame());
                        playerInfo.displayPlayerInfo(getGame(), resolution);
                        nextActionTurn();
                    }
                    else {
                   	 nextActionTurn();
                   	 actionInfo.displayActionInfo(getGame());
                   	 playerInfo.displayPlayerInfo(getGame(), resolution);
                    }
        		}
    			else {
    				for (Triplet<Hexagon, Integer, HexagonListType> p : 
    						hexagonTripletList) {
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
     * It's a function that allows the player to move his pawns on the board
     * 
     * @param hex the hexagon that was clicked
     */
    private void inGameMovePawn(Hexagon hex) {
    	if (hex != null 
    			&& (!hex.getExplorerList().isEmpty() 
    					|| hex.getBoat() != null)  
    			&& firstClic == true) {
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

                    pawnToMove = (Pawn)board.getExternalPanel()
                    		.getSelection();
                    board.getExternalPanel().setSelection(null);
                    board.getExternalPanel().setClickedHex(null);
                    inGame(hex);
                } else if (hex.containsExplorerColor(plColor) 
                		|| (hex.getBoat() != null 
                		&& hex.getBoat().isOwnedBy(getCurrentPlayer()))) {
                	
                    board.getExternalPanel().setClickedHex(hex);
                    board.setDisplayExternalPanel(true);
                    board.getExternalPanel()
                    		.setExternalPanelState(ExternalPanelState
                    				.PAWNPANEL);

                }
            } else {
                pawnToMove.findPath(hex, 
                		board, 
                		getCurrentPlayer().getMoveLeft(), 
                		hexagonTripletList);
                for (Triplet<Hexagon, Integer, HexagonListType> p : 
                		hexagonTripletList) {
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
            	if((hex == saveHexa && hex.getBoat() != null 
            			&& pawnToMove instanceof Explorer 
            			&& ((Explorer)pawnToMove).getStatus() != ExplorerStatus
            				.ONBOAT) 
            			|| (hex.getBoat() != null && saveHexa.getBoat() != null
    					&& pawnToMove instanceof Explorer 
            			&& ((Explorer)pawnToMove).getStatus() == ExplorerStatus
            				.ONBOAT )) {
            		destination = hex.getBoat();

            	} else if(hex.getBoat() == null || !saveHexa.isTiles()) {
            		destination = hex;
            	}
            	
            	if(destination == null 
            			&& (saveHexa.isTiles() 
            			|| (saveHexa.getBoat() != null && saveHexa.getBoat()
            				.getExplorerList().contains(pawnToMove))) 
            			&& hex.getBoat() != null) {
            		if (board.getExternalPanel().getSelection() != null) {
                        destination = board.getExternalPanel().getSelection();
                        board.getExternalPanel().setSelection(null);
                        board.getExternalPanel().setClickedHex(null);
                        inGame(hex);
            		} else {
                		board.getExternalPanel().setClickedHex(hex);
                        board.setDisplayExternalPanel(true);
                        board.getExternalPanel()
                        .setExternalPanelState(ExternalPanelState.BOATORSEA);
            		}
            	} else if(!board.isDisplayExternalPanel()){
                    if(destination == hex 
                    		&& ((pawnToMove instanceof Explorer 
                    		&& ((Explorer)pawnToMove).getStatus() != 
                    			ExplorerStatus.ONBOAT)
                    		|| pawnToMove instanceof Boat)) {
                		pawnToMove.move(saveHexa, hex);
                        checkJ.clear();
                        playJ.clear();
                		defWithTile(hex);

                    } else if(destination == hex 
                    		&& ((Explorer)pawnToMove).getStatus() == 
                    		ExplorerStatus.ONBOAT) {
                    	((Explorer) pawnToMove).move(saveHexa.getBoat(), 
                    			hex);
                    } else if(destination == hex.getBoat() 
                    		&& ((Explorer)pawnToMove).getStatus() != 
                    		ExplorerStatus.ONBOAT) {
                    	((Explorer) pawnToMove).move(saveHexa, hex.getBoat(), 
                    			hex);
                    } else if(destination == hex.getBoat() 
                    		&& ((Explorer)pawnToMove).getStatus() == 
                    		ExplorerStatus.ONBOAT) {
                    	((Explorer) pawnToMove).move(saveHexa.getBoat(), 
                    			hex.getBoat(), 
                    			hex);
                    }
                    
                    for(int comp = 0; comp < hexagonTripletList.getLeftList()
                    		.size();comp++) {
    					if(hexagonTripletList.getLeftList().get(comp) == hex) {
    						getCurrentPlayer().setMoveLeft(getCurrentPlayer()
    								.getMoveLeft() - hexagonTripletList
    								.getMiddleList().get(comp));
    						pawnToMove.setMovePoint(pawnToMove.getMovePoint()
    								- hexagonTripletList.getMiddleList()
    								.get(comp));
    					}
    				}
                    for (Triplet<Hexagon, Integer, HexagonListType> p : 
                    		hexagonTripletList) {
                        p.getLeft().setHighlightColor(null);
                        p.getLeft().setHighlight(resolution, 
                        		board, 
                        		false, 
                        		null);
                    }
                    hexagonTripletList.clear();
                    saveHexa.displayPawns(board);
                    firstClic = true;
                    saveHexa = null;
                    pawnToMove = null;
                    destination = null;                  
                    if(getCurrentPlayer().getMoveLeft() == 0) {
                    	 if(board.isDisplayExternalPanel()) {
                             actionInfo.displayActionInfo(getGame());
                             playerInfo.displayPlayerInfo(getGame(), 
                            		 resolution);
                             nextActionTurn();
                         }
                         else {
                        	 nextActionTurn();
                        	 actionInfo.displayActionInfo(getGame());
                        	 playerInfo.displayPlayerInfo(getGame(), 
                        			 resolution);
                         }
    				}
            	}
            }
            else {
				for(Triplet<Hexagon, Integer, HexagonListType> p : 
						hexagonTripletList) {
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
     * The function inGameDiscoverTile() is called when the player clicks on a hexagon. It checks if
     * the hexagon is not null and if it has a tile. If it does, it checks if the tile is a beach, a
     * forest or a mountain. If it is, it checks if the tile can be removed (next to the sea).
     * If it is, it decreases the number of tiles of the type of the tile, discovers
     * the hexagon, calls the function defWithTile() and then calls the nextActionTurn() function
     * 
     * @param hex the hexagon that the player clicked on
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
                    defWithTile(hex); 
                    
                    if(board.isDisplayExternalPanel()) {
                        actionInfo.displayActionInfo(getGame());
                        playerInfo.displayPlayerInfo(getGame(), resolution);
                        nextActionTurn();
                    }
                    else {
                        nextActionTurn();
                    	actionInfo.displayActionInfo(getGame());
                        playerInfo.displayPlayerInfo(getGame(), resolution);
                    }
            	}
            }
        }
    }
    
    
    /**
     * After rolling the dice, the player can choose a monster and move it.
     * 
     * @param hex the hexagon that was clicked
     */
    public void inGameMoveMonster(Hexagon hex) {
    	if (board.getExternalPanel().getPawnType() == null) {
        	board.setDisplayExternalPanel(true);
        	board.getExternalPanel()
        		.setExternalPanelState(ExternalPanelState.DICEPANEL);

        } else if(!board.isDisplayExternalPanel()) {
        	if(board.getExternalPanel().getPawnType() == PawnType.SHARK) {
        		if(!board.isSharkOnBoard()) {
        			System.out.println("Il n'y a pas de requins !");
                    nextTurn();
        		} else if(hex != null 
        				&& hex.getSharkList().isEmpty() 
        				&& firstClic == true) {
        			return;
        		}
        	} else if(board.getExternalPanel()
        			.getPawnType() == PawnType.SEASNAKE) {
        		if(!board.isSeaSnakeOnBoard()) {
                    board.getExternalPanel().setPawnType(null);
                    nextTurn();
        		} else if(hex != null 
        				&& hex.getSeaSnakeList().isEmpty() 
        				&& firstClic == true) {
        			return;
        		}
        	} else if(board.getExternalPanel()
        			.getPawnType() == PawnType.WHALE) {
        		if(!board.isWhaleOnBoard()) {
                    board.getExternalPanel().setPawnType(null);
                    nextTurn();
        		} else if(hex != null 
        				&& hex.getWhaleList().isEmpty() 
        				&& firstClic == true) {
        			return;
        		}
        	}
        	if (hex != null && firstClic == true) {
        		saveHexa = hex;
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

                pawnToMove.findPath(hex, board, 3, hexagonTripletList);
                for (Triplet<Hexagon, Integer, HexagonListType> p : 
                		hexagonTripletList) {
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
                    checkJ.clear();
                    playJ.clear();
                    defWithTile(hex); 
        			
                    for (Triplet<Hexagon, Integer, HexagonListType> p : 
                    		hexagonTripletList) {
                        p.getLeft().setHighlightColor(null);
                        p.getLeft().setHighlight(resolution, 
                        		board, 
                        		false, 
                        		null);
                    }
                    hexagonTripletList.clear();
                    saveHexa.displayPawns(board);
                    firstClic = true;
                    saveHexa = null;
                    pawnToMove = null;
                    board.getExternalPanel().setPawnType(null);

                    if(board.isDisplayExternalPanel()) {
                        actionInfo.displayActionInfo(getGame());
                        playerInfo.displayPlayerInfo(getGame(), resolution);
                        nextTurn();
                    }
                    else {
                        nextTurn();
                    	actionInfo.displayActionInfo(getGame());
                        playerInfo.displayPlayerInfo(getGame(), resolution);
                    }
                } else {
    				for(Triplet<Hexagon, Integer, HexagonListType> p : 
    						hexagonTripletList) {
    					p.getLeft().setHighlightColor(null);
    					p.getLeft().setHighlight(resolution, 
    							board, 
    							false, 
    							null);
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
     * This function is used to reset the move point of the boat to 3
     */
    private void resetMovePointBoat() {
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 12; j++) {
				Hexagon[][] hexagons = board.getHexagons();
				if(hexagons[i][j].getBoat() != null) {
					hexagons[i][j].getBoat().setMovePoint(3);
				}
			}
		}
    }
    
	/**
     * > This function returns the action info
     * 
     * @return The actionInfo object.
     */
    public ActionInfo getActionInfo() {
		return actionInfo;
	}

	/**
     * If the player has a tile that can counter the effect of the shark or whale, he can use it
     * 
     * @param hex the hexagon where the pawn is
     */
    @SuppressWarnings("removal")
	public void defWithTile(Hexagon hex) {
		if(checkJ.isEmpty() && playJ.isEmpty()) {
			for(@SuppressWarnings("unused") Player p : players) {
				checkJ.add(new Boolean(true));
				playJ.add(new Boolean(false));
			}
			if(!hex.getExplorerList().isEmpty() 
					&& !hex.getSharkList().isEmpty()) {
				for(Player p : players) {
					if(hex.containsExplorerColor(p.getColor())) {
						for(Tile t : p.getTileList()) {
							if(t.getEffect() == TilesEffect.SHARK_DEATH) {
								System.out.println("haha counter !");
								checkJ.set(players.indexOf(p), false);
							}
						}	
					}			
				}
			}else if(hex.getBoat() != null 
					&& !hex.getBoat().getExplorerList().isEmpty() 
					&& !hex.getWhaleList().isEmpty()) {					
				for(Player p : players) {	
					if(hex.containsExplorerColor(p.getColor())) {
						for(Tile t : p.getTileList()) {
							if(t.getEffect() == TilesEffect.WHALE_DEATH) {
								checkJ.set(players.indexOf(p), false);
							}
						}
					}
				}
			}
		}
		
		if(checkJ.stream().anyMatch(o -> o.booleanValue() == false) 
				|| playJ.stream().anyMatch(o -> o.booleanValue() == true)) {
			Boolean b = null;
			if(checkJ.stream().anyMatch(o -> o.booleanValue() == false))
					b = checkJ.stream().filter(o -> o.booleanValue() == false)
					.findFirst().get();
			if(!playJ.stream().anyMatch(o -> o.booleanValue() == true)) {
				if (board.getExternalPanel().getChoice()!= null) {   
					
                    playJ.set(checkJ.indexOf(b), board.getExternalPanel()
                    		.getChoice());
                    checkJ.set(checkJ.indexOf(b), true);
                    board.getExternalPanel().setChoice(null);
            		board.getExternalPanel().setClickedHex(null);
                    defWithTile(hex);
                } else {
                	
                    board.setDisplayExternalPanel(true);
            		board.getExternalPanel().setClickedHex(hex);
                    board.getExternalPanel()
                    .setExternalPanelState(ExternalPanelState
                    		.TILEEFFECTDEFENSEPANEL);
                }
			} else {
				if(!hex.getSharkList().isEmpty() 
						&& !hex.getExplorerList().isEmpty()) {
					
					hex.getSharkList().clear();
		            this.board.getExternalPanel()
		            		.setAnimationType(AnimationType.SHARK_COUNTER);
		            this.board.setDisplayExternalPanel(true);
		            this.board.getExternalPanel()
		            		.setExternalPanelState(ExternalPanelState
		            				.ANIMATIONPANEL);
				}
	            if(!hex.getWhaleList().isEmpty() 
	            		&& hex.getBoat() != null 
	            		&& !hex.getBoat().getExplorerList().isEmpty()) {
	            	
					hex.getWhaleList().clear();
	                this.board.getExternalPanel()
	                		.setAnimationType(AnimationType.WHALE_COUNTER);
	                this.board.setDisplayExternalPanel(true);
	                this.board.getExternalPanel()
	                		.setExternalPanelState(ExternalPanelState
	                				.ANIMATIONPANEL);
	            }
	            hex.displayPawns(board);
			}
		} else {
			if(!hex.getSharkList().isEmpty()) {
		      hex.getSharkList().get(0).makeEffect(hex);
			}
            if(!hex.getWhaleList().isEmpty()) {
              hex.getWhaleList().get(0).makeEffect(hex);
            }
            hex.displayPawns(board);
		}
	}

    /**
     * This function returns the playerInfo object
     * 
     * @return The playerInfo object.
     */
    public PlayerInfo getPlayerInfo() {
		return playerInfo;

	}
    
    /**
     * This function returns the value of the variable accueil
     * 
     * @return The accueil object.
     */
    public Accueil getAccueil() {
		return accueil;
	}
}
