package fr.mcstudio.board;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.game.Player;
import fr.mcstudio.pawns.Boat;
import fr.mcstudio.pawns.EffectPawn;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.Pawn;
import fr.mcstudio.pawns.SeaSnake;
import fr.mcstudio.pawns.Shark;
import fr.mcstudio.pawns.Whale;

@SuppressWarnings("serial")
/**
 * Hexagon is a JLayeredPane that contains a Tile, a Boat, and a list of Pawns
 */
public class Hexagon extends JLayeredPane {

   // Creating a hexagon object and adding it to a JLayeredPane.
    public Hexagon(JLayeredPane boardPane, 
    		final int line, 
    		final int column, 
    		int resolution) {
    	
        super();
        this.line = line;
        this.column = column;
        this.resolution = resolution;

        this.setLayout(null);
        this.setBounds(this.returnPosX(resolution),
                this.returnPosY(resolution),
                resolution,
                resolution);

        boardPane.setLayer(this, 1);
        setOpaque(false);
        boardPane.add(this);
    }

    // Declaring a variable called resolution.
    private int resolution;

    // Declaring a variable called line.
    private int line;

    // Declaring a variable called column.
    private int column;

    // Declaring a variable called type.
    private Tile tile = null;

    // Declaring a variable called boat.
    private HexagonType type;

    // Declaring a private boolean variable called highlight.
    private boolean highlight;

    // Creating a new JLabel object.
    private JLabel highlightLabel = new JLabel();

    // Declaring a variable called highlightColor and initializing it to null.
    private String highlightColor = null;

    // Creating a new ArrayList of Pawns.
    private List<Pawn> pawnsToDisplay = new ArrayList<Pawn>();

    // Creating a new ArrayList of type Explorer.
    private List<Explorer> explorerList = new ArrayList<Explorer>();

    // Creating a new ArrayList of Shark objects.
    private List<Shark> sharkList = new ArrayList<Shark>();

    // Creating a new ArrayList of Whale objects.
    private List<Whale> whaleList = new ArrayList<Whale>();

    // Creating a new ArrayList of SeaSnake objects.
    private List<SeaSnake> seaSnakeList = new ArrayList<SeaSnake>();

    // Declaring a variable called boat of type Boat.
    private Boat boat = null;

    /**
     * If the tile is null, return null, otherwise return the tile
     * 
     * @return The tile object.
     */
    public Tile getTile() {
            return this.tile;
    }

    /**
     * This function sets the tile of the current object to the tile passed in as a parameter
     * 
     * @param tile The tile that the player is currently on.
     */
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    /**
     * This function sets the type of the hexagon to the type passed in
     * 
     * @param type The type of the hexagon.
     */
    public void setType(HexagonType type) {
        this.type = type;
    }

    
    /**
     * This function returns the type of the hexagon
     * 
     * @return The type of the hexagon.
     */
    public HexagonType getType() {
        return this.type;
    }

   /**
    * This function returns true if the type of the hexagon is TILES, and false otherwise.
    * 
    * @return The boolean value if the hexagon's type is a HexagonType.TILES.
    */
    public boolean isTiles() {
        return this.type == HexagonType.TILES;
    }

    /**
     * Returns true if the hexagon is a sea hexagon.
     * 
     * @return The boolean value if the hexagon's type is a HexagonType.SEA.
     */
    public boolean isSea() {
        return this.type == HexagonType.SEA;
    }

    /**
     * "This function returns true if the hexagon is of type VOID, and false otherwise."
     * 
     * @return The boolean value if the hexagon's type is a HexagonType.VOID
     */
    public boolean isVoid() {
        return this.type == HexagonType.VOID;
    }

    /**
     * Returns true if the hexagon is an island.
     * 
     * @return The type of the hexagon.
     */
    public boolean isIsland() {
        return this.type == HexagonType.ISLAND;
    }

    /**
     * This function adds an explorer to the list of explorers
     * 
     * @param e Explorer
     */
    public void addPawn(Explorer e) {
        this.explorerList.add(e);
    }

    /**
     * This function adds a shark to the list of sharks
     * 
     * @param s Shark
     */
    public void addPawn(Shark s) {
        this.sharkList.add(s);
    }

   /**
    * This function adds a whale to the whaleList
    * 
    * @param w The whale object that is being added to the list.
    */
    public void addPawn(Whale w) {
        this.whaleList.add(w);
    }

    /**
     * This function adds a SeaSnake object to the seaSnakeList ArrayList
     * 
     * @param ss SeaSnake
     */
    public void addPawn(SeaSnake ss) {
        this.seaSnakeList.add(ss);
    }

    /**
     * Adding a boat to the hexagon
     * 
     * @param b Boat
     */
    public void addPawn(Boat b) {
        this.boat = b;
    }

    /**
     * This function adds a pawn to the appropriate list
     * 
     * @param ef EffectPawn
     */
    public void addPawn(EffectPawn ef) {
        if (ef instanceof Shark) {
            this.sharkList.add((Shark) ef);
        } else if (ef instanceof Whale) {
            this.whaleList.add((Whale) ef);
        } else if (ef instanceof SeaSnake) {
            this.seaSnakeList.add((SeaSnake) ef);
        }
    }

    /**
     * This function removes an explorer from the explorer list
     * 
     * @param e The explorer to be removed from the list.
     */
    public void removePawn(Explorer e) {
        this.explorerList.remove(e);
    }

    /**
     * This function removes a shark from the sharkList
     * 
     * @param s The Shark to be removed from the list.
     */
    public void removePawn(Shark s) {
        this.sharkList.remove(s);
    }

    /**
     * This function removes a whale from the whaleList
     * 
     * @param w The whale to be removed from the list.
     */
    public void removePawn(Whale w) {
        this.whaleList.remove(w);
    }

    /**
     * This function removes a SeaSnake from the seaSnakeList
     * 
     * @param ss The SeaSnake to be removed from the list.
     */
    public void removePawn(SeaSnake ss) {
        this.seaSnakeList.remove(ss);
    }

    /**
     * Removes a boat from the hexagon
     * 
     * @param b The boat to be removed
     */
    public void removePawn(Boat b) {
        this.boat = null;
    }

    /**
     * This function removes a pawn from the hexagon
     * 
     * @param ef EffectPawn
     */
    public void removePawn(EffectPawn ef) {
        if (ef instanceof Shark) {
            this.sharkList.remove((Shark) ef);
        } else if (ef instanceof Whale) {
            this.whaleList.remove((Whale) ef);
        } else if (ef instanceof SeaSnake) {
            this.seaSnakeList.remove((SeaSnake) ef);
        }
    }

    /**
     * This function returns a list of explorers
     * 
     * @return The list of explorers.
     */
    public List<Explorer> getExplorerList() {
        return this.explorerList;
    }

    /**
     * This function returns a list of sharks
     * 
     * @return The list of sharks.
     */
    public List<Shark> getSharkList() {
        return this.sharkList;
    }
    
    /**
     * This function returns a list of whales
     * 
     * @return The whaleList is being returned.
     */
    public List<Whale> getWhaleList() {
        return this.whaleList;
    }

    /**
     * This function returns a list of sea snakes
     * 
     * @return The list of sea snakes.
     */
    public List<SeaSnake> getSeaSnakeList() {
        return this.seaSnakeList;
    }

    /**
     * This function returns the boat object
     * 
     * @return The boat object.
     */
    public Boat getBoat() {
        return this.boat;
    }

    /**
     * Return true if the click is in the hexagon.
     * 
     * @param resolution the size of the hexagon
     * @param clickx x coordinate of the click
     * @param clicky the y coordinate of the click
     * @return A boolean value.
     */
    public boolean isInHexagonfloat(int resolution, float clickx, float clicky) {

        switch (resolution) {
            case 70:
                if (isInDemiPlan(35, 0, 0, 15, clickx, clicky)
                        && isInDemiPlan(69, 15, 35, 0, clickx, clicky)
                        && isInDemiPlan(0, 54, 35, 69, clickx, clicky)
                        && isInDemiPlan(35, 69, 69, 54, clickx, clicky)
                        && isInDemiPlan(0, 15, 0, 54, clickx, clicky) 
                        && isInDemiPlan(69, 54, 69, 15, clickx, clicky)) {
                    return true;
                } else {
                    return false;
                }
            case 80:
                if (isInDemiPlan(40, 0, 0, 17, clickx, clicky) 
                        && isInDemiPlan(79, 17, 40, 0, clickx, clicky)
                        && isInDemiPlan(0, 62, 40, 79, clickx, clicky)
                        && isInDemiPlan(40, 79, 79, 62, clickx, clicky)
                        && isInDemiPlan(0, 17, 0, 62, clickx, clicky) 
                        && isInDemiPlan(79, 62, 79, 17, clickx, clicky)) {
                    return true;
                } else {
                    return false;
                }
            case 90:
                if (isInDemiPlan(45, 0, 0, 20, clickx, clicky) 
                        && isInDemiPlan(89, 20, 45, 0, clickx, clicky)
                        && isInDemiPlan(0, 69, 45, 89, clickx, clicky)
                        && isInDemiPlan(45, 89, 89, 69, clickx, clicky)
                        && isInDemiPlan(0, 20, 0, 69, clickx, clicky) 
                        && isInDemiPlan(89, 69, 89, 20, clickx, clicky)) {
                    return true;
                } else {
                    return false;
                }
            default:
                break;
        }
        return false;

    }
    
    /**
     * If the point is on the left side of the line, then it is in the demi-plane
     * 
     * @param ax x coordinate of the first point of the line
     * @param ay y coordinate of the first point of the line
     * @param bx x coordinate of the first point of the line
     * @param by y coordinate of the second point of the line
     * @param clickx x coordinate of the click
     * @param clicky the y coordinate of the click
     * @return a boolean value.
     */
    public boolean isInDemiPlan(float ax, float ay, 
    		float bx, float by, 
    		float clickx, float clicky) {
    	
        float d = (bx - ax) * (clicky - ay) - (by - ay) * (clickx - ax);
        if (d <= 0)
            return true;
        else
            return false;
    }

    /**
     * This function returns the line number of the current object
     * 
     * @return The line number of the cell.
     */
    public int getLine() {
        return line;
    }

    /**
     * This function returns the column of the current object
     * 
     * @return The column of the cell.
     */
    public int getColumn() {
        return column;
    }

    /**
     * This function returns a boolean value that indicates whether the highlight is on or off
     * 
     * @return The value of the variable highlight.
     */
    public boolean isHighlight() {
        return highlight;
    }

    /**
     * It sets the highlight of a tile to true or false, and if it's true, it sets the color of the
     * highlight to the color passed in the function
     * 
     * @param resolution the size of the tile
     * @param boardPane the JLayeredPane that contains the tiles
     * @param highlight boolean, true if the tile should be highlighted, false if not
     * @param color the color of the highlight
     */
    public void setHighlight(int resolution, 
    		JLayeredPane boardPane, 
    		boolean highlight, 
    		String color) {
    	
        this.highlight = highlight;
        remove(highlightLabel);
        revalidate();
        repaint();

        if (this.highlight) {
            ImageIcon icone = null;
            Image scaleImage;
            if (color == "white") {
                icone = new ImageIcon(Tile.class
                		.getResource("/HexagonBlanc.png"));
            } else if (color == "yellow") {
                icone = new ImageIcon(Tile.class
                		.getResource("/HexagonJaune.png"));
                highlightColor = "yellow";
            } else if (color == "red") {
                icone = new ImageIcon(Tile.class
                		.getResource("/HexagonRouge.png"));
                highlightColor = "red";
            } else if (color == "purple") {
                icone = new ImageIcon(Tile.class
                		.getResource("/HexagonViolet.png"));
                highlightColor = "purple";
            }
            switch (resolution) {
                case 70:
                    scaleImage = icone.getImage()
                    		.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    icone.setImage(scaleImage);
                    break;
                case 80:
                    scaleImage = icone.getImage()
                    		.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    icone.setImage(scaleImage);
                    break;
                default:
                    break;
            }

            highlightLabel.setBounds(0, 0, resolution, resolution);
            setLayer(highlightLabel, 4);
            this.highlightLabel.setIcon(icone);
            this.highlightLabel.setVisible(true);
            add(highlightLabel);
        } else {
            this.highlightLabel.setVisible(false);
            remove(highlightLabel);
        }
    }

    /**
     * This function sets the highlight color of the hexagon
     * 
     * @param highlightColor The color of the highlight.
     */
    public void setHighlightColor(String highlightColor) {
        this.highlightColor = highlightColor;
    }

    /**
     * This function returns the highlight color of the hexagon.
     * 
     * @return The highlightColor variable is being returned.
     */
    public String getHighlightColor() {
        return highlightColor;
    }

    /**
     * It removes all pawns from the hexagon
     */
    public void removeAllPawn() {
        if (!this.explorerList.isEmpty()) {
            for (Explorer explorer : explorerList) {
                explorer.setStatus(ExplorerStatus.DEAD);
            }
            this.explorerList.clear();
        }
        if (!this.seaSnakeList.isEmpty()) {
            this.seaSnakeList.clear();
        }
        if (!this.sharkList.isEmpty()) {
            this.sharkList.clear();
        }
        if (!this.whaleList.isEmpty()) {
            this.whaleList.clear();
        }
        this.boat = null;
    }

    /**
     * This function removes the tile from the hexagon and sets the type to sea
     */
    public void removeTile() {
        this.setTile(null);
        this.setType(HexagonType.SEA);
    }

    /**
     * It returns the x position of a hexagon on the screen, based on the resolution and
     * the line and column of the hexagon
     * 
     * @param resolution the size of the hexagon
     * @return The position of the hexagon in the x-axis.
     */
    public int returnPosX(int resolution) {
        if (this.line % 2 == 0) {
            switch (resolution) {
                case 70:
                    return 92 + 70 * this.column;
                case 80:
                    return 105 + 80 * this.column;
                case 90:
                    return 120 + 90 * this.column;
                default:
                    break;
            }
        } else {
            switch (resolution) {
                case 70:
                    return 57 + 70 * this.column;
                case 80:
                    return 65 + 80 * this.column;
                case 90:
                    return 75 + 90 * this.column;
                default:
                    break;
            }
        }
        return 0;
    }

    /**
     * It returns the y position of a hexagon on the screen, based on the resolution and
     * the line and column of the hexagon
     * 
     * @param resolution the resolution of the screen
     * @return The position of the tile on the y axis.
     */
    public int returnPosY(int resolution) {
        if (this.line % 2 == 0) {
            switch (resolution) {
                case 70:
                    return 26 + 54 * this.line;
                case 80:
                    return 29 + 62 * this.line;
                case 90:
                    return 31 + 70 * this.line;
                default:
                    break;
            }
        } else {
            switch (resolution) {
                case 70:
                    return 26 + 54 * this.line;
                case 80:
                    return 28 + 62 * this.line;
                case 90:
                    return 31 + 70 * this.line;
                default:
                    break;
            }
        }
        return 0;
    }

    /**
     * It displays the pawns on the hexagon
     * 
     * @param board the board object
     */
    public void displayPawns(Board board) {
        for (Pawn p : pawnsToDisplay) {
            remove(p);
        }
        revalidate();
        repaint();
        pawnsToDisplay.clear();
        List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();
        List<Integer> index = new ArrayList<Integer>();
        int imageSize = 0;

        if (!this.sharkList.isEmpty()) {
            Shark s = new Shark(board);
            index.add(this.sharkList.size());
            pawnsToDisplay.add(s);
        }
        if (!this.whaleList.isEmpty()) {
            Whale w = new Whale(board);
            index.add(this.whaleList.size());
            pawnsToDisplay.add(w);
        }
        if (!this.seaSnakeList.isEmpty()) {
            SeaSnake ss = new SeaSnake(board);
            index.add(this.seaSnakeList.size());
            pawnsToDisplay.add(ss);
        }
        if (this.boat != null) {
            Boat b = new Boat(resolution);
            index.add(1);
            pawnsToDisplay.add(b);
        }
        if (!this.explorerList.isEmpty()) {
            if (containsExplorerColor(Color.GREEN)) {
                Explorer e = new Explorer(Color.GREEN, 0);
                index.add(nbExplorerColor(Color.GREEN));
                pawnsToDisplay.add(e);
            }
            if (containsExplorerColor(Color.RED)) {
                Explorer e = new Explorer(Color.RED, 0);
                index.add(nbExplorerColor(Color.RED));
                pawnsToDisplay.add(e);
            }
            if (containsExplorerColor(Color.BLUE)) {
                Explorer e = new Explorer(Color.BLUE, 0);
                index.add(nbExplorerColor(Color.BLUE));
                pawnsToDisplay.add(e);
            }
            if (containsExplorerColor(Color.YELLOW)) {
                Explorer e = new Explorer(Color.YELLOW, 0);
                index.add(nbExplorerColor(Color.YELLOW));
                pawnsToDisplay.add(e);
            }
        }
        float rate = ((float) resolution / (float) 90);
        switch (pawnsToDisplay.size()) {
            case 1:
                x.add((int) (11 * rate));
                y.add((int) (11 * rate));
                imageSize = 68;
                break;
            case 2:
                x.add((int) (1 * rate));
                y.add((int) (23 * rate));
                x.add((int) (46 * rate));
                y.add((int) (23 * rate));
                imageSize = 44;

                break;
            case 3:
                x.add((int) (27 * rate));
                x.add((int) (9 * rate));
                x.add((int) (45 * rate));
                y.add((int) (3 * rate));
                y.add((int) (39 * rate));
                y.add((int) (39 * rate));
                imageSize = 36;

                break;
            case 4:
                x.add((int) (15 * rate));
                x.add((int) (15 * rate));
                x.add((int) (49 * rate));
                x.add((int) (49 * rate));
                y.add((int) (15 * rate));
                y.add((int) (45 * rate));
                y.add((int) (15 * rate));
                y.add((int) (45 * rate));
                imageSize = 30;

                break;
            case 5:

                x.add((int) (30 * rate));
                x.add((int) (10 * rate));
                x.add((int) (50 * rate));
                x.add((int) (10 * rate));
                x.add((int) (50 * rate));
                y.add((int) (30 * rate));
                y.add((int) (10 * rate));
                y.add((int) (10 * rate));
                y.add((int) (50 * rate));
                y.add((int) (50 * rate));
                imageSize = 30;

                break;
            case 6:
                x.add((int) (20 * rate));
                x.add((int) (45 * rate));
                x.add((int) (10 * rate));
                x.add((int) (55 * rate));
                x.add((int) (20 * rate));
                x.add((int) (45 * rate));
                y.add((int) (5 * rate));
                y.add((int) (5 * rate));
                y.add((int) (30 * rate));
                y.add((int) (30 * rate));
                y.add((int) (55 * rate));
                y.add((int) (55 * rate));
                imageSize = 25;

                break;

            default:
                break;
        }
        for (int i = 0; i < pawnsToDisplay.size(); i++) {

            pawnsToDisplay.get(i).setPosition(x.get(i),
                    y.get(i), resolution, imageSize);
            pawnsToDisplay.get(i).createPawnImage();
            if (pawnsToDisplay.get(i) instanceof Boat)
                ((Boat) pawnsToDisplay.get(i))
                		.displayBoatPawns(this.boat, 
                				resolution, 
                				pawnsToDisplay.size(), 
                				this);

            if (index.get(i) > 1)
                pawnsToDisplay.get(i).addIndex(index.get(i), imageSize);

            setLayer(pawnsToDisplay.get(i), 1);
            add(pawnsToDisplay.get(i));
        }
    }

    /**
     * "If the color is present in the list, return true, otherwise return false."
     * 
     * The function is called "containsExplorerColor" and it takes a single parameter, a color
     * 
     * @param color The color to check for
     * @return A boolean value.
     */
    public boolean containsExplorerColor(final Color color) {
        return explorerList.stream().filter(o -> o.getColor()
        		.equals(color)).findFirst().isPresent();
    }

    /**
     * This function returns the number of explorers with a given color
     * 
     * @param color the color of the explorers to count
     * @return The number of explorers with the given color.
     */
    public int nbExplorerColor(final Color color) {
        int nb = 0;
        for (Explorer e : explorerList) {
            if (e.getColor() == color) {
                nb++;
            }
        }
        return nb;
    }

    /**
     * The function is called when a player wants to discover a tile. If there is a tile on the case,
     * the tile is flipped.
     * 
     * @param p the player who is playing
     * @param board the board object
     */
    public void discover(Player p, Board board) {
        if (this.tile != null) {
            this.tile.flipTile(this, p, board);
        } 
    }
}
