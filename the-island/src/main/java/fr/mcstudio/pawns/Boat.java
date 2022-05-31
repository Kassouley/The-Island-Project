package fr.mcstudio.pawns;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.game.Player;
import fr.mcstudio.util.Triplet;
import fr.mcstudio.util.TripletList;

@SuppressWarnings("serial")

/**
 * It creates a class called Boat that extends the Pawn class.
 */
public class Boat extends Pawn {

    // A constructor.
    public Boat(int resolution) {
      super(3);
      this.createImage(resolution);
    }
    
    // Creating a new list of explorers.
    private List<Explorer> explorerList = new ArrayList<Explorer>();

    // A list of explorers to display.
    private List<Explorer> explorerToDisplay = new ArrayList<Explorer>();

    
    /**
     * This function returns a list of explorers
     * 
     * @return The list of explorers.
     */
    public List<Explorer> getExplorerList() {
        return explorerList;
    }
  
    /**
     * This function adds an explorer to the list of explorers
     * 
     * @param explorer The explorer to add to the list.
     */
    public void addExplorer(Explorer explorer) {
        this.explorerList.add(explorer);
    }

    /**
     * This function removes an explorer from the list of explorers
     * 
     * @param explorer The explorer to be removed from the list.
     */
    public void removeExplorer(Explorer explorer) {
        this.explorerList.remove(explorer);
    }

    /**
     * This function checks if the explorerList is full
     * 
     * @return The method isFull() returns a boolean value.
     */
    public boolean isFull() {
        return (this.explorerList.size() >= 3);
    }

    /**
     * This function returns true if the explorerList is empty, and false if it is not
     * 
     * @return The method isEmpty() is being returned.
     */
    public boolean isEmpty() {
        return this.explorerList.isEmpty();
    }
    
    /**
     * "If the number of explorers of a given color is greater than or equal to the number of explorers
     * of any other color, then the tile is owned by that color."
     * 
     * The function is a little more complicated than that, but that's the gist of it
     * 
     * @param player The player who is trying to claim the tile
     * @return The method isOwnedBy() returns a boolean value.
     */
    public boolean isOwnedBy(Player player) {
        int[] colorCount = new int[4];
        int i = 0;
        int maxAt = 0;

        for (i = 0; i < this.explorerList.size(); i++) {
            colorCount[this.explorerList.get(i).getColor().ordinal()] += 1;
        }

        for (i = 0; i < colorCount.length; i++) {
            maxAt = colorCount[i] > colorCount[maxAt] ? i : maxAt;
        }

        return (colorCount[player.getColor().ordinal()] >= colorCount[maxAt]);
    }

    /**
     * "When a boat sinks, all the explorers on board become swimmers and are placed on the hexagon
     * where the boat sank."
     * 
     * The function is called `sunk` and it takes a single parameter, `boatPosition`, which is the
     * hexagon where the boat sank
     * 
     * @param boatPosition The hexagon that the boat is currently on.
     */
    public void sunk(Hexagon boatPosition) {
        for (Explorer explorer : this.explorerList) {
            boatPosition.addPawn(explorer);
            explorer.setStatus(ExplorerStatus.SWIMMER);
        }
        this.explorerList.clear();
        boatPosition.removePawn(this);
    }
    
    /**
     * The function move() is used to move a pawn from one hexagon to another.
     * 
     * @param oldPosition The hexagon the pawn is currently on
     * @param newPosition Hexagon
     */
    public void move(Hexagon oldPosition, Hexagon newPosition) {
        oldPosition.removePawn(this);
        newPosition.addPawn(this);
        if (!this.explorerList.isEmpty()) {
            if (!newPosition.getWhaleList().isEmpty()) {
                newPosition.getWhaleList().get(0).makeEffect(newPosition);
            }
            if (!newPosition.getSeaSnakeList().isEmpty()) {
                newPosition.getSeaSnakeList().get(0).makeEffect(newPosition);
            }
        }
    }

    /**
     * It takes a hexagon, a board, a list of hexagons, and a distance, and adds the hexagon to the
     * list of hexagons if it's not already in the list, and if it's a sea hexagon
     * 
     * @param actualPosition The current position of the explorer
     * @param board the board
     * @param hexagonTripletList a list of hexagons, their distance from the starting hexagon, and
     * their type (normal or death)
     * @param distance the distance from the starting point
     */
    public void findPathAux(Hexagon actualPosition, Board board, 
            TripletList<Hexagon,Integer,HexagonListType> hexagonTripletList, int distance) {

        List<Hexagon> tmp = new ArrayList<Hexagon>();

        tmp.add(board.getTopLeft(actualPosition));
        tmp.add(board.getTopRight(actualPosition));
        tmp.add(board.getLeft(actualPosition));
        tmp.add(board.getRight(actualPosition));
        tmp.add(board.getBottomLeft(actualPosition));
        tmp.add(board.getBottomRight(actualPosition));

        for (Hexagon hexagon : tmp) {
            if (hexagon != null
                    && !hexagonTripletList.containsInTriplet(hexagon)
                    && hexagon.getType() == HexagonType.SEA) {

                if (this.explorerList.isEmpty()
                        || (hexagon.getSeaSnakeList().isEmpty()
                        && hexagon.getWhaleList().isEmpty())) {
                            
                    hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.NORMAL));
                } else {
                    hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.DEATH));
                }
            }
        }
    }

	/**
     * It displays the pawns on the boat
     * 
     * @param boat the boat to display
     * @param resolution the size of the hexagon
     * @param nbPawn the number of pawns on the boat
     * @param hex the hexagon where the boat is
     */
    public void displayBoatPawns(Boat boat, int resolution, int nbPawn, Hexagon hex) {
		List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();
        explorerToDisplay.clear();
		for(Explorer e : boat.explorerList) {
			explorerToDisplay.add(new Explorer(e.getColor(), 0));
		}
		switch (explorerToDisplay.size()) {
		case 3:
            x.add((int) (0));
            y.add((int) (3*this.getHeight()/10));
        case 2:
            x.add((int) (3*this.getWidth()/10));
            y.add((int) (3*this.getHeight()/10));
        case 1:
            x.add((int) (3*this.getWidth()/5));
            y.add((int) (3*this.getHeight()/10));
		}
		for (int i = 0; i < explorerToDisplay.size(); i++) {
			explorerToDisplay.get(i).setBounds(x.get(i),
					y.get(i), 2*this.getWidth()/5, 2*this.getHeight()/5);

			createPawnBoatImage(explorerToDisplay.get(i));
    		
            setLayer(explorerToDisplay.get(i), 4);
            add(explorerToDisplay.get(i));
		}
	}

	/**
     * It creates an image for a pawn in a boat, depending on its color
     * 
     * @param e Explorer
     */
    private void createPawnBoatImage(Explorer e) {
		ImageIcon icon = null;
        Image scaleImage;
		if((e).getColor() == Color.RED) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_rouge.png"));
    	} else if((e).getColor() == Color.GREEN) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_vert.png"));
    	} else if((e).getColor() == Color.BLUE) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_bleu.png"));
    	} else if((e).getColor() == Color.YELLOW) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_jaune.png"));
    	}

		scaleImage = icon.getImage().getScaledInstance(e.getWidth(), e.getHeight(), Image.SCALE_SMOOTH);
        icon.setImage(scaleImage);

        e.getImage().setIcon(icon);
        e.add(e.getImage());
        e.getImage().setBounds(0, 0, e.getWidth(), e.getHeight());
	}
	
	/**
     * It creates an image icon for the boat, scales it, and then sets the image icon to the image
     * 
     * @param resolution the size of the image
     */
    public void createImage(int resolution) {

        ImageIcon icon = null;
        Image scaleImage;

        icon = new ImageIcon(Pawn.class.getResource("/pion_bateau.png"));
        scaleImage = icon.getImage().getScaledInstance(resolution, resolution, Image.SCALE_SMOOTH);
        icon.setImage(scaleImage);

        this.image.setIcon(icon);
    }
	
	/**
     * "Returns true if the explorerList contains an explorer with the given color."
     * 
     * @param color The color to check for
     * @return A boolean value.
     */
    public boolean containsExplorerColor(final Color color) {
        return explorerList.stream().filter(o -> o.getColor().equals(color)).findFirst().isPresent();
    }

   /**
    * It returns the number of explorers of a given color
    * 
    * @param color the color of the explorer
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

}