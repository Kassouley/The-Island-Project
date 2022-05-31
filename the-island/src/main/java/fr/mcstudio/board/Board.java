package fr.mcstudio.board;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.enums.TilesEffect;
import fr.mcstudio.enums.TilesType;
import fr.mcstudio.game.Game;
import fr.mcstudio.game.Player;
import fr.mcstudio.pawns.Explorer;

@SuppressWarnings("serial")

/**
 * It creates a board for the game.
 */
public class Board extends JLayeredPane{

	// Creating a new Board object with the parameters game and resolution.
	public Board(Game game, final int resolution) {
		super();
		this.game = game;
		setLayer(boardLabel, 0);
		setPanelBoundsFromResolution(resolution);
		setLabel();
		add(boardLabel);
		
		externalPanel = new ExternalPanel(this, resolution);

		List<Tile> tilesList = CreateTiles(resolution);
		Random r = new Random();
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 12; j++) {
				hexagons[i][j] = new Hexagon(this, i, j, resolution);
				if (isInMapBound(i,j)) {

					if ((((i > 2 && j > 3 && i < 10 && j < 8)
							|| (i > 4 && j > 1 && i < 8 && j < 10
							&& (i != 6 || j != 9)))
							&& (i != 6 || j != 5))
							|| (i == 4 && j == 3)
							|| (i == 8 && j == 3)) {
						int n = r.nextInt(tilesList.size());
						hexagons[i][j].setTile(tilesList.get(n));
						tilesList.remove(n);
						hexagons[i][j].setType(HexagonType.TILES);
						hexagons[i][j].getTile().getTypeLabel().setBounds(0, 0, 
								resolution, resolution);
						hexagons[i][j].add(hexagons[i][j].getTile().getTypeLabel());

					} else if (i == 1 && j == 0 || i == 1 && j == 11 ||
							i == 11 && j == 0 || i == 11 && j == 11) {
						hexagons[i][j].setType(HexagonType.ISLAND);
					} else {
						hexagons[i][j].setType(HexagonType.SEA);
					}
				} else {
					hexagons[i][j].setType(HexagonType.VOID);
				}
			}
		}
	}

	// Creating a 2D array of Hexagons.
	private Hexagon[][] hexagons = new Hexagon[13][12];

	// Creating a new JLabel object.
	private JLabel boardLabel = new JLabel();
	
	// Declaring a variable called nbBeach and assigning it the value 16.
	private int nbBeach = 16;

	// Declaring a variable called nbForest and assigning it the value 16.
	private int nbForest = 16;

	// Declaring a variable called nbMountains and assigning it the value 8.
	private int nbMountains = 8;

	// Creating a private variable called game.
	private Game game;
	
	// Creating a new instance of the ExternalPanel class.
	private ExternalPanel externalPanel;
	
	// Declaring a variable called displayExternalPanel and setting it to false.
	private boolean displayExternalPanel = false;

	/**
	 * This function returns the hexagons array
	 * 
	 * @return The array of hexagons.
	 */
	public Hexagon[][] getHexagons() {
		return hexagons;
	}

	/**
	 * This function returns the number of beaches on the board
	 * 
	 * @return The number of beaches.
	 */
	public int getNbBeach() {
		return nbBeach;
	}

	/**
	 * This function returns the number of forests on the board
	 * 
	 * @return The number of forests.
	 */
	public int getNbForest() {
		return nbForest;
	}

	/**
	 * This function returns the number of mountains on the board
	 * 
	 * @return The number of mountains.
	 */
	public int getNbMountains() {
		return nbMountains;
	}

	/**
	 * It decreases the number of tiles of a given type
	 * 
	 * @param type the type of tile to decrease
	 */
	public void decreaseNbTile(TilesType type) {
		switch(type) {
			case BEACH:
				this.nbBeach --;
				break;
			case FOREST:
				this.nbForest --;
				break;
			case MOUNTAINS:
				this.nbMountains --;
				break;
		}
	}
	
	/**
	 *  This function returns the external panel
	 * 
	 * @return The externalPanel object.
	 */
	public ExternalPanel getExternalPanel() {
		return externalPanel;
	}

	/**
	 * Create tiles with their effects
	 * 
	 * @param resolution The resolution of the tiles.
	 * @return A list of tiles.
	 */
	private List<Tile> CreateTiles(int resolution) {
		List<Tile> tiles = new ArrayList<Tile>();

		for (int i = 0; i < 16; i++) {
			Tile tile = new Tile(resolution);
			tile.setType(TilesType.BEACH);
			if (i < 3) {
				tile.setEffect(TilesEffect.WHALE);
			} else if (i >= 3 && i < 6) {
				tile.setEffect(TilesEffect.SHARK);
			} else if (i >= 6 && i < 9) {
				tile.setEffect(TilesEffect.DOLPHIN_MOVE);
			} else if (i >= 9 && i < 11) {
				tile.setEffect(TilesEffect.BOAT_MOVE);
			} else if (i == 11) {
				tile.setEffect(TilesEffect.BOAT);
			} else if (i == 12) {
				tile.setEffect(TilesEffect.SEASNAKE_LOST);
			} else if (i == 13) {
				tile.setEffect(TilesEffect.SHARK_LOST);
			} else if (i == 14) {
				tile.setEffect(TilesEffect.WHALE_LOST);
			} else if (i == 15) {
				tile.setEffect(TilesEffect.SHARK_DEATH);
			}
			tiles.add(tile);
		}
		for (int i = 0; i < 16; i++) {
			Tile tile = new Tile(resolution);
			tile.setType(TilesType.FOREST);
			if (i < 2) {
				tile.setEffect(TilesEffect.WHALE);
			} else if (i >= 2 && i < 4) {
				tile.setEffect(TilesEffect.SHARK);
			} else if (i >= 4 && i < 7) {
				tile.setEffect(TilesEffect.BOAT);
			} else if (i >= 7 && i < 9) {
				tile.setEffect(TilesEffect.WHIRLPOOL);
			} else if (i >= 9 && i < 11) {
				tile.setEffect(TilesEffect.WHALE_DEATH);
			} else if (i == 11) {
				tile.setEffect(TilesEffect.DOLPHIN_MOVE);
			} else if (i == 12) {
				tile.setEffect(TilesEffect.SEASNAKE_LOST);
			} else if (i == 13) {
				tile.setEffect(TilesEffect.SHARK_LOST);
			} else if (i == 14) {
				tile.setEffect(TilesEffect.WHALE_LOST);
			} else if (i == 15) {
				tile.setEffect(TilesEffect.SHARK_DEATH);
			}
			tiles.add(tile);
		}
		for (int i = 0; i < 8; i++) {
			Tile tile = new Tile(resolution);
			tile.setType(TilesType.MOUNTAINS);
			if (i < 4) {
				tile.setEffect(TilesEffect.WHIRLPOOL);
			} else if (i == 4) {
				tile.setEffect(TilesEffect.SHARK);
			} else if (i == 5) {
				tile.setEffect(TilesEffect.VOLCANO);
			} else if (i == 6) {
				tile.setEffect(TilesEffect.SHARK_DEATH);
			} else if (i == 7) {
				tile.setEffect(TilesEffect.WHALE_DEATH);
			}
			tiles.add(tile);
		}
		return tiles;
	}

	/**
	 * It sets the bounds of the panel based on the resolution
	 * 
	 * @param resolution The resolution.
	 */
	private void setPanelBoundsFromResolution(int resolution) {
		
		switch (resolution) {
			case 70:
				setBounds(217, 0, 955, 770);
				break;
			case 80:
				setBounds(248, 0, 1090, 880);
				break;
			case 90:
				setBounds(282, 0, 1230, 990);
				break;
			default:
				break;
		}
	}
	
	/**
	 * It takes the background from the resource folder, scales it to the size of the JPanel, and sets the
	 * JLabel's icon to the scaled image
	 */
	private void setLabel() {
		ImageIcon icone = new ImageIcon(Board.class.getResource("/Map_90.png"));
		Image scaleImage = icone.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);;
		icone.setImage(scaleImage);
		boardLabel.setIcon(icone);
		boardLabel.setBounds(0, 0, getWidth(), getHeight());
	}
	
	/**
	 * Return true if the hexagon is a playable hexagon
	 * 
	 * @param line the line of the map
	 * @param column the column of the map
	 * @return The method is returning a boolean value.
	 */
	public boolean isInMapBound(int line, int column) {
		if (((line != 0 || column != 0)
				&& (line != 1 || column != 0)
				&& (line != 3 || column != 0)
				&& (line != 9 || column != 0)
				&& (line != 11 || column != 0)
				&& (line != 12 || column != 0)
				&& (line != 0 || column != 0)
				&& (line != 0 || column != 1)
				&& (line != 12 || column != 1)
				&& (line != 0 || column != 9)
				&& (line != 12 || column != 9)
				&& (line != 0 || column != 10) 
				&& (line != 12 || column != 10)
				&& column != 11)
				|| (line == 5 && column == 11)
				|| (line == 7 && column == 11)
				|| line == 1 && column == 0 
				|| line == 1 && column == 11 
				|| line == 11 && column == 0 
				|| line == 11 && column == 11) {
			return true;
		}
		
		return false;
	}

	/**
	 * Return the hexagon in the top left corner of the actual hexagon. Otherwise, return null
	 * 
	 * @param actualHexagon the hexagon that is being checked
	 * @return The top left hexagon of the actual hexagon.
	 */
	public Hexagon getTopLeft(Hexagon actualHexagon) {
		if (actualHexagon.getLine() - 1 >= 0 
				&& actualHexagon.getLine() % 2 == 0 
				&& isInMapBound(actualHexagon.getLine() - 1, 
						actualHexagon.getColumn())) {
			return this.getHexagons()[actualHexagon.getLine() - 1]
					[actualHexagon.getColumn()];
		} else if (actualHexagon.getLine() - 1 >= 0
				&& actualHexagon.getColumn() - 1 >= 0
				&& actualHexagon.getLine() % 2 == 1
				&& isInMapBound(actualHexagon.getLine() - 1, 
						actualHexagon.getColumn() - 1)) {
			return this.getHexagons()[actualHexagon.getLine() - 1]
					[actualHexagon.getColumn() - 1];
		} else {
			return null;
		}
	}

	/**
	 * Return the hexagon in the top right corner of the actual hexagon. Otherwise, return null
	 * 
	 * @param actualHexagon the hexagon that is being checked
	 * @return The top right hexagon of the actual hexagon.
	 */
	public Hexagon getTopRight(Hexagon actualHexagon) {
		if (actualHexagon.getLine() - 1 >= 0
				&& actualHexagon.getColumn() + 1 < 12
				&& actualHexagon.getLine() % 2 == 0
				&& isInMapBound(actualHexagon.getLine() - 1, 
						actualHexagon.getColumn() + 1)) {
			return this.getHexagons()[actualHexagon.getLine() - 1][actualHexagon.getColumn() + 1];
		} else if (actualHexagon.getLine() - 1 >= 0 
				&& actualHexagon.getLine() % 2 == 1
				&& isInMapBound(actualHexagon.getLine() - 1, 
						actualHexagon.getColumn())) {
			return this.getHexagons()[actualHexagon.getLine() - 1][actualHexagon.getColumn()];
		} else {
			return null;
		}
	}

	/**
	 * Return the hexagon on the left of the actual hexagon. Otherwise, return null
	 * 
	 * @param actualHexagon the hexagon that is being checked for a neighbor
	 * @return The hexagon to the left of the actual hexagon.
	 */
	public Hexagon getLeft(Hexagon actualHexagon) {
		if (actualHexagon.getColumn() - 1 >= 0 
				&& isInMapBound(actualHexagon.getLine(), 
						actualHexagon.getColumn() - 1)) {
			return this.getHexagons()[actualHexagon.getLine()][actualHexagon.getColumn() - 1];
		} else {
			return null;
		}
	}

	/**
	 * Return the hexagon on the right of the actual hexagon. Otherwise, return null
	 * 
	 * @param actualHexagon the hexagon that is being checked for its neighbors
	 * @return The hexagon to the right of the actual hexagon.
	 */
	public Hexagon getRight(Hexagon actualHexagon) {
		if (actualHexagon.getColumn() + 1 < 12
				&& isInMapBound(actualHexagon.getLine(), 
						actualHexagon.getColumn() + 1)) {
			return this.getHexagons()[actualHexagon.getLine()][actualHexagon.getColumn() + 1];
		} else {
			return null;
		}
	}

	/**
	 * Return the hexagon in the bottom left of the actual hexagon. Otherwise, return null
	 * 
	 * @param actualHexagon the hexagon that is being checked
	 * @return The method returns the hexagon that is located at the bottom left of the actual hexagon.
	 */
	public Hexagon getBottomLeft(Hexagon actualHexagon) {
		if (actualHexagon.getLine() + 1 < 13 
				&& actualHexagon.getLine() % 2 == 0
				&& isInMapBound(actualHexagon.getLine() + 1, 
						actualHexagon.getColumn())) {
			return this.getHexagons()[actualHexagon.getLine() + 1][actualHexagon.getColumn()];
		} else if (actualHexagon.getLine() + 1 < 13
				&& actualHexagon.getColumn() - 1 >= 0
				&& actualHexagon.getLine() % 2 == 1
				&& isInMapBound(actualHexagon.getLine() + 1, 
						actualHexagon.getColumn() - 1)) {
			return this.getHexagons()[actualHexagon.getLine() + 1][actualHexagon.getColumn() - 1];
		} else {
			return null;
		}
	}

	/**
	 * Return the hexagon in the bottom right of the actual hexagon. Otherwise, return null
	 * 
	 * @param actualHexagon the hexagon that is being checked
	 * @return The method returns the hexagon that is located at the bottom right of the actual hexagon.
	 */
	public Hexagon getBottomRight(Hexagon actualHexagon) {
		if (actualHexagon.getLine() + 1 < 13
				&& actualHexagon.getColumn() + 1 < 12
				&& actualHexagon.getLine() % 2 == 0
				&& isInMapBound(actualHexagon.getLine() + 1, 
						actualHexagon.getColumn() + 1)) {
			return this.getHexagons()[actualHexagon.getLine() + 1][actualHexagon.getColumn() + 1];
		} else if (actualHexagon.getLine() + 1 < 13 
				&& actualHexagon.getLine() % 2 == 1
				&& isInMapBound(actualHexagon.getLine() + 1, 
						actualHexagon.getColumn())) {
			return this.getHexagons()[actualHexagon.getLine() + 1][actualHexagon.getColumn()];
		} else {
			return null;
		}
	}
	
	/**
	 * If the hexagon is next to the sea, return true
	 * 
	 * @param actualHexagon The hexagon that you want to check if it's next to the sea.
	 * @return A boolean value.
	 */
	public boolean isNextToSea(Hexagon actualHexagon) {
		if(getTopLeft(actualHexagon).isSea()
				|| getTopRight(actualHexagon).isSea()
				|| getLeft(actualHexagon).isSea()
				|| getRight(actualHexagon).isSea()
				|| getBottomLeft(actualHexagon).isSea()
				|| getBottomRight(actualHexagon).isSea())
			return true;
		
		return false;
	}

	/**
	 * If the hexagon is next to land, return true
	 * 
	 * @param actualHexagon The hexagon that is being checked to see if it is next to land.
	 * @return A boolean value.
	 */
	public boolean isNextToLand(Hexagon actualHexagon) {
		if(getTopLeft(actualHexagon).isTiles()
				|| getTopRight(actualHexagon).isTiles()
				|| getLeft(actualHexagon).isTiles()
				|| getRight(actualHexagon).isTiles()
				|| getBottomLeft(actualHexagon).isTiles()
				|| getBottomRight(actualHexagon).isTiles())
			return true;
		
		return false;
	}
	
	/**
	 * If there is a tile of the given type that is next to the sea, then return false
	 * 
	 * @param actualHexagon The hexagon that the player is trying to place a tile on.
	 * @param tilesType the type of tile you want to check
	 * @return A boolean value.
	 */
	public boolean canRemoveOutOfSea(Hexagon actualHexagon, TilesType tilesType) {
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 12; j++) {
				Hexagon hex = this.getHexagons()[i][j];
				if(hex.getTile() != null && hex.getTile().getType() == tilesType) {
					if(isNextToSea(hex)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * This function returns the board object.
	 * 
	 * @return The board object.
	 */
	public Board getBoard() {
		return this;
	}
	
	/**
	 * This function checks if there are any sharks on the board
	 * 
	 * @return A boolean value.
	 */
	public boolean isSharkOnBoard() {
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 12; j++) {
				if(this.getHexagons()[i][j].getSharkList().size() > 0)
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This function checks if there are any whales on the board
	 * 
	 * @return A boolean value.
	 */
	public boolean isWhaleOnBoard() {
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 12; j++) {
				if(this.getHexagons()[i][j].getWhaleList().size() > 0)
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This function checks if there is a sea snake on the board
	 * 
	 * @return A boolean value.
	 */
	public boolean isSeaSnakeOnBoard() {
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 12; j++) {
				if(this.getHexagons()[i][j].getSeaSnakeList().size() > 0)
					return true;
			}
		}
		
		return false;
	}

	/**
	 * This function returns a boolean value that indicates whether the external panel is displayed
	 * 
	 * @return The value of the displayExternalPanel variable.
	 */
	public boolean isDisplayExternalPanel() {
		return displayExternalPanel;
	}

	/**
	 * This function sets the value of the displayExternalPanel variable to the value of the
	 * displayExternalPanel parameter
	 * 
	 * @param displayExternalPanel This is a boolean value that determines whether or not the external
	 * panel is displayed.
	 */
	public void setDisplayExternalPanel(boolean displayExternalPanel) {
		this.displayExternalPanel = displayExternalPanel;
	}

	/**
	 * This function returns the game object
	 * 
	 * @return The game object.
	 */
	public Game getGame() {
		return game;
	}
	
	
	/**
	 * This function checks if a player has a swimmer on the board.
	 * 
	 * @param p Player
	 * @return A boolean value.
	 */
	public boolean isSwimmerOnBoard(Player p) {
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 12; j++) {
				for(Explorer e : this.getHexagons()[i][j].getExplorerList()) {
					if(e.getStatus() == ExplorerStatus.SWIMMER && e.getColor() == p.getColor()) {
						return true;
					}
				}
					
			}
		}
		
		return false;
	}
	
	
	/**
	 * This function checks if there is a boat on the board
	 * 
	 * @return A boolean value.
	 */
	public boolean isBoatOnBoard() {
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 12; j++) {
				if(this.getHexagons()[i][j].getBoat() != null)
					return true;
			}
		}
		
		return false;
	}
}
