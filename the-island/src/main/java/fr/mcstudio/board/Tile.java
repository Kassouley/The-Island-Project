
package fr.mcstudio.board;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import fr.mcstudio.enums.AnimationType;
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.ExternalPanelState;
import fr.mcstudio.enums.GameState;
import fr.mcstudio.enums.TilesEffect;
import fr.mcstudio.enums.TilesType;
import fr.mcstudio.game.Player;
import fr.mcstudio.pawns.Boat;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.Shark;
import fr.mcstudio.pawns.Whale;

@SuppressWarnings("serial")

/**
 * Tile is a JLayeredPane that contains a JLabel for the type of tile and a JLabel for the effect of
 * the tile
 */
public class Tile extends JLayeredPane {

	// The constructor of Tile.
	public Tile(int resolution) {
		this.resolution = resolution;
		this.typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}

	// Declaring a variable called `type` of type `TilesType` and initializing it to `null`.
	private TilesType type = null;

	// Declaring a variable called `effect` of type `TilesEffect` and initializing it to `null`.
	private TilesEffect effect = null;

	// A variable that is used to store the resolution of the tile.
	private int resolution;

	// Creating a new JLabel object and assigning it to the variable `typeLabel`.
	private JLabel typeLabel = new JLabel();
	
	// Creating a new JLabel object and assigning it to the variable `effectLabel`.
	private JLabel effectLabel = new JLabel();

	/**
	 * This function returns the type of the tile
	 * 
	 * @return The type of the tile.
	 */
	public TilesType getType() {
			return this.type;
	}

	/**
	 * It sets the type of the tile and changes the image of the tile accordingly
	 * 
	 * @param type the type of the tile
	 */
	public void setType(TilesType type) {
		ImageIcon icone = null;
		Image scaleImage;
		this.type = type;
		if (type == TilesType.BEACH) {
			icone = new ImageIcon(Tile.class.getResource("/Plage.png"));
		} else if (type == TilesType.FOREST) {
			icone = new ImageIcon(Tile.class.getResource("/Foret.png"));
		} else if (type == TilesType.MOUNTAINS) {
			icone = new ImageIcon(Tile.class.getResource("/Montagne.png"));
		}
		
		scaleImage = icone.getImage().getScaledInstance(resolution, 
														resolution,
														Image.SCALE_SMOOTH);
		icone.setImage(scaleImage);
		
		this.typeLabel.setIcon(icone);
	}

	/**
	 * This function returns the effect of the tile.
	 * 
	 * @return The effect of the tile.
	 */
	public TilesEffect getEffect() {
		return this.effect;
	}

	/**
	 * It sets the effect of the tile and create the corresponding label
	 * 
	 * @param effect the effect to be displayed on the tile
	 */
	public void setEffect(TilesEffect effect) {
		ImageIcon icone = null;
		Image scaleImage;
		this.effect = effect;
		
		switch(effect) {
			case BOAT_MOVE:
				icone = new ImageIcon(Tile.class
						.getResource("/Bateau_move.png"));
				break;
			case BOAT:
				icone = new ImageIcon(Tile.class
						.getResource("/Bateau.png"));
				break;
			case DOLPHIN_MOVE:
				icone = new ImageIcon(Tile.class
						.getResource("/Dauphin_move.png"));
				break;
			case SEASNAKE_LOST:
				icone = new ImageIcon(Tile.class
						.getResource("/Serpent_de_mer.png"));
				break;
			case SHARK:
				icone = new ImageIcon(Tile.class
						.getResource("/Requin.png"));
				break;
			case SHARK_DEATH:
				icone = new ImageIcon(Tile.class
						.getResource("/Requin_mort.png"));
				break;
			case SHARK_LOST:
				icone = new ImageIcon(Tile.class
						.getResource("/Requin_perdu.png"));
				break;
			case WHIRLPOOL:
				icone = new ImageIcon(Tile.class
						.getResource("/Tourbillon.png"));
				break;
			case VOLCANO:
				icone = new ImageIcon(Tile.class
						.getResource("/Volcan.png"));
				break;
			case WHALE:
				icone = new ImageIcon(Tile.class
						.getResource("/Baleine.png"));
				break;
			case WHALE_DEATH:
				icone = new ImageIcon(Tile.class
						.getResource("/Baleine_mort.png"));
				break;
			case WHALE_LOST:
				icone = new ImageIcon(Tile.class
						.getResource("/Baleine_perdu.png"));
				break;
		}
		
		scaleImage = icone.getImage().getScaledInstance(resolution, 
														resolution,
														Image.SCALE_SMOOTH);
		icone.setImage(scaleImage);
		
		this.effectLabel.setIcon(icone);
	}

	/**
	 * It removes the tile from the hexagon, and then adds it to the player's tile list and all the explorers on the hexagon become swimmers
	 * 
	 * @param hexagon the hexagon that the player clicked on
	 * @param p the player who is flipping the tile
	 * @param board the board object
	 */
	public void flipTile(Hexagon hexagon, Player p, Board board) {
		for(Explorer e : hexagon.getExplorerList()) {
			e.setStatus(ExplorerStatus.SWIMMER);
			e.setMovePoint(1);
		}
		if(hexagon.getTile().getEffect().getType() == "Verte") {
			hexagon.getTile().applyEffect(hexagon,board);
		} else {
			  p.getTileList().add(hexagon.getTile());
		}
		
		hexagon.remove(hexagon.getTile().getTypeLabel());
		hexagon.revalidate();
		hexagon.repaint();
		hexagon.removeTile();
		 
	}
	
	/**
	 * The function takes a hexagon and a board as parameters and applies the effect of the tile to the
	 * hexagon
	 * 
	 * @param hex the hexagon that the player is on
	 * @param board the board object
	 */
	public void applyEffect(Hexagon hex,Board board) {
		switch(this.effect) {		
		case BOAT : this.boatEffect(hex, board);break;
		case SHARK : this.sharkEffect(hex,board);break;
		case WHIRLPOOL : this.whirlpoolEffect(hex,board); break;
		case VOLCANO : this.volcanoEffect(board); break;
		case WHALE : this.whaleEffect(hex,board); break;
		default : break;
		}
	}
	
	/**
	 * It creates a boat, adds it to the tile, and if there are more than 3 explorers on the tile, it
	 * opens a panel to let the player choose which explorers to board the boat. If there are less than 3
	 * explorers, it automatically boards all of them
	 * 
	 * @param tilePosition The hexagon that the boat is being summoned on.
	 * @param board the board object
	 */
	public void boatEffect(Hexagon tilePosition, Board board) {
		Boat b = new Boat(resolution);
        b.setPosition(0, 0, resolution, 68);
        b.createImage(resolution);
		tilePosition.addPawn(b);
		if(tilePosition.getExplorerList().size() > 3) {
	        board.setDisplayExternalPanel(true);
            board.getExternalPanel().setClickedHex(tilePosition);
	        board.getExternalPanel()
	        		.setExternalPanelState(ExternalPanelState.BOARDINGPANEL);
			
		} else {
			board.getExternalPanel()
					.setAnimationType(AnimationType.BOAT_SUMMON);
	        board.setDisplayExternalPanel(true);
	        board.getExternalPanel()
	        		.setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
	        for (int i = tilePosition
	        		.getExplorerList().size() - 1; i >= 0; i--) {
	        	Explorer e = tilePosition.getExplorerList().get(i);
	        	e.move(tilePosition, b, tilePosition);
			}
		}
	}

	/**
	 * The sharkEffect function is called when a player flip a whale tile. It creates a new shark pawn
	 * and adds it to the hexagon and set the animation
	 * 
	 * @param tilePosition The tile that the shark is being summoned on.
	 * @param board The board object
	 */
	public void sharkEffect(Hexagon tilePosition, Board board) {
		board.getExternalPanel()
				.setAnimationType(AnimationType.SHARK_SUMMON);
        board.setDisplayExternalPanel(true);
        board.getExternalPanel()
        		.setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
		Shark s = new Shark(board);
		tilePosition.addPawn(s);
	}

	/**
	 * It removes all pawns from the hexagon and its sea neighbors
	 * 
	 * @param tilePosition the position of the tile that was clicked
	 * @param board the board object
	 */
	public void whirlpoolEffect(Hexagon tilePosition,Board board) {
		board.getExternalPanel()
				.setAnimationType(AnimationType.WHIRLPOOL);
        board.setDisplayExternalPanel(true);
        board.getExternalPanel()
        		.setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
		Hexagon[][] hexagons = board.getHexagons();
		List<Hexagon> listNeighbors = new ArrayList<Hexagon>();

       
		listNeighbors = findNeighbors(tilePosition, board);		
		listNeighbors.add(tilePosition);
		for(Hexagon hexagon :listNeighbors ) {
			hexagons[hexagon.getLine()]
					[hexagon.getColumn()].removeAllPawn();
			hexagons[hexagon.getLine()]
					[hexagon.getColumn()].displayPawns(board);
		}										
	}

	/**
	 * The function sets the animation type to volcano, sets the external panel state to animation panel,
	 * and ends the game
	 * 
	 * @param board The board object that the game is being played on.
	 */
	public void volcanoEffect(Board board) {
		board.getExternalPanel().setAnimationType(AnimationType.VOLCANO);
        board.setDisplayExternalPanel(true);
        board.getExternalPanel()
        		.setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
        
        board.getGame().setGameState(GameState.ENDING);
        board.getGame().endGame();
        
		//endgame
	}

	/**
	 * The whaleEffect function is called when a player flip a whale tile. It creates a new whale pawn
	 * and adds it to the hexagon and set the animation
	 * 
	 * @param tilePosition The tile that the whale will be placed on.
	 * @param board the board object
	 */
	public void whaleEffect(Hexagon tilePosition, Board board) {
		board.getExternalPanel()
				.setAnimationType(AnimationType.WHALE_SUMMON);
        board.setDisplayExternalPanel(true);
        board.getExternalPanel()
        		.setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
		Whale w = new Whale(board);
		tilePosition.addPawn(w);	
	}
	
	/**
	 * It returns a list of all the hexagons that are adjacent to the given hexagon, and are also sea
	 * hexagons
	 * 
	 * @param actualPosition The current position of the ship
	 * @param board the board object
	 * @return A list of hexagons.
	 */
	public List<Hexagon> findNeighbors(Hexagon actualPosition, Board board) {
        List<Hexagon> tmp = new ArrayList<Hexagon>();
        List<Hexagon> listHexagon = new ArrayList<Hexagon>();
        
        tmp.add(board.getTopLeft(actualPosition));
        tmp.add(board.getTopRight(actualPosition));
        tmp.add(board.getLeft(actualPosition));
        tmp.add(board.getRight(actualPosition));
        tmp.add(board.getBottomLeft(actualPosition));
        tmp.add(board.getBottomRight(actualPosition));

        for (Hexagon hexagon : tmp) {
            if (hexagon != null
                   && hexagon.isSea()) {
                listHexagon.add(hexagon);
            }
        }
		return listHexagon;
    }

	/**
	 * This function returns the effectLabel
	 * 
	 * @return The effectLabel is being returned.
	 */
	public JLabel getEffectLabel() {
		return effectLabel;
	}
	
	/**
	 * This function returns the typeLabel
	 * 
	 * @return The typeLabel is being returned.
	 */
	public JLabel getTypeLabel() {
		return typeLabel;
	}

	/**
	 * If the tile has an effect that requires a certain pawn to be on the board, then check if that
	 * pawns is on the board
	 * 
	 * @param board the board object
	 * @param p the player who is playing the tile
	 * @return A boolean value.
	 */
	public boolean checkPlayTile(Board board,Player p) {
		switch(this.effect) {
		case BOAT_MOVE : 
			if(board.isBoatOnBoard()) {
				return true;
			}
		case DOLPHIN_MOVE : 
			if(board.isSwimmerOnBoard(p)) {
				return true;
			}
		case SHARK_LOST : 
			if(board.isSharkOnBoard()) {
				return true;
			}
		case SEASNAKE_LOST : 		
			if(board.isSeaSnakeOnBoard()) {
				return true;
			}
		case WHALE_LOST : 
			if(board.isWhaleOnBoard()) {
				return true;
			}
		default : break;
		}
		return false;
	}
	
}
