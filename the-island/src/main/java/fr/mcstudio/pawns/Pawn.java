package fr.mcstudio.pawns;

import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.util.Triplet;
import fr.mcstudio.util.TripletList;

@SuppressWarnings("serial")


/**
 * > A `Pawn` is a `JLayeredPane` that can move on the `Board`.
 */
public class Pawn extends JLayeredPane {

    // The constructor of the class Pawn.
    public Pawn(int movePoint) {
    	this.setLayout(null);
    	this.setOpaque(false);
        this.movePoint = movePoint;
    }

    // A variable that is used to display the index of the pawn.
    protected JLabel index;

    // Creating a new JLabel object and assigning it to the variable image.
    protected JLabel image = new JLabel();

	// A variable that is used to store the number of moves that the pawn can make.
    private int movePoint;

    /**
     * This function returns the movePoint of the player
     * 
     * @return The movePoint variable is being returned.
     */
    public int getMovePoint() {
        return this.movePoint;
    }

    /**
     * This function sets the movePoint variable to the value of the movePoint parameter
     * 
     * @param movePoint The number of points the player can move.
     */
    public void setMovePoint(int movePoint) {
        this.movePoint = movePoint;
    }

    /**
     * Move the pawn to the given hexagon from an old hexagon.
     * 
     * @param oldPosition The hexagon that the unit is currently on.
     * @param newPosition The new position of the piece.
     */
    public void move(Hexagon oldPosition, Hexagon newPosition) {
    }

    /**
     * This function sets the bounds of the object to the given x and y coordinates, and the size of
     * the object to the given size, but the size is scaled based on the resolution.
     * 
     * @param x The x position of the button
     * @param y The y position of the button
     * @param resolution The resolution of the screen.
     * @param size The size of the button in pixels.
     */
    public void setPosition(int x, int y, int resolution, int size) {
        this.setBounds(x, y, (int) (size * ((float) resolution / 90)), (int) (size * ((float) resolution / 90)));
    }

    /**
     * It finds all the hexagons that are reachable from the actual position, and it stores them in a
     * list
     * 
     * @param actualPosition The current position of the player
     * @param board the board
     * @param movePointLeft the number of moves left
     * @param hexagonTripletList a list of hexagons, their distance from the starting hexagon, and
     * their type (land, water, death)
     */
    public void findPath(Hexagon actualPosition, Board board, int movePointLeft, TripletList<Hexagon,Integer,HexagonListType> hexagonTripletList) {
        hexagonTripletList.clear();
        
        int distance = Math.min(movePointLeft, this.getMovePoint());
        
        List<Hexagon> tmp = new ArrayList<Hexagon>();
        tmp.add(actualPosition);
        hexagonTripletList.add(new Triplet<Hexagon, Integer, HexagonListType>(actualPosition, 1, HexagonListType.BOAT));
        for (int i = 1; i <= distance; i++) {
            for (Hexagon hexagon : tmp) {
                this.findPathAux(hexagon, board, hexagonTripletList, i);
            }
            
            List<Hexagon> mem = new ArrayList<Hexagon>();
            List<Hexagon> hexagonList = hexagonTripletList.getLeftList();
            mem.addAll(tmp);

            tmp.clear();
            for (Hexagon hexagon : hexagonList) {
                int index = hexagonList.indexOf(hexagon);
                if (hexagonTripletList.get(index).getRight() != HexagonListType.DEATH) {
                        
                    tmp.add(hexagon);
                }
            }

            for (Hexagon hexagon : mem) {
                if (tmp.contains(hexagon)) {
                    tmp.remove(hexagon);
                }
            }
        }

        hexagonTripletList.remove(0);
    }

    
    /**
     * It takes a hexagon, a board, a list of hexagons, and a distance, and adds to the list of
     * hexagons all the hexagons that are adjacent to the given hexagon, and the distance to them
     * 
     * @param actualPosition The current position of the unit.
     * @param board The board that the path is being found on.
     * @param hexagonTripletList A list of hexagons, their distance from the starting hexagon, and the
     * type of hexagon list they are in.
     * @param distance the distance from the starting hexagon
     */
    public void findPathAux(Hexagon actualPosition, Board board, TripletList<Hexagon,Integer,HexagonListType> hexagonTripletList, int distance) {
    }

    /**
     * It creates an image for the pawn, depending on its type
     */
    public void createPawnImage() {
        ImageIcon icon = null;
        Image scaleImage;
        if (this instanceof Shark) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_requin.png"));

        } else if (this instanceof Whale) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_baleine.png"));

        } else if (this instanceof SeaSnake) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_serpent_de_mer.png"));

        } else if (this instanceof Boat) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_bateau.png"));

        } else if (this instanceof Explorer) {
        	if(((Explorer)this).getColor() == Color.RED) {
                icon = new ImageIcon(Pawn.class.getResource("/pion_rouge.png"));
        	} else if(((Explorer)this).getColor() == Color.GREEN) {
                icon = new ImageIcon(Pawn.class.getResource("/pion_vert.png"));
        	} else if(((Explorer)this).getColor() == Color.BLUE) {
                icon = new ImageIcon(Pawn.class.getResource("/pion_bleu.png"));
        	} else if(((Explorer)this).getColor() == Color.YELLOW) {
                icon = new ImageIcon(Pawn.class.getResource("/pion_jaune.png"));
        	}
        }
        scaleImage = icon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        icon.setImage(scaleImage);
        this.image.setIcon(icon);
        this.add(this.image);
        this.image.setBounds(0, 0, this.getWidth(), this.getHeight());
    }

	/**
     * This function adds a JLabel to the JPanel that contains the index of the pawn
     * 
     * @param index the index of the pawn
     * @param size the size of the font
     */
    public void addIndex(int index, int size) {
		this.index = new JLabel(Integer.toString(index));
		this.index.setFont(new Font("Tahoma", Font.BOLD,  size/2));
		this.index.setForeground(java.awt.Color.WHITE);
		int indexWidth = this.index.getFontMetrics(this.index.getFont()).stringWidth(this.index.getText());
		int indexHeight = this.index.getFontMetrics(this.index.getFont()).getHeight();
		this.index.setBounds(getWidth()/2 - indexWidth/2, getHeight()/2 - indexHeight/4, size/2, size/2);
		this.setLayer(this.index, 2);
		add(this.index);	
	}

	/**
     * Get the image of the pawn
     * 
     * @return The image of the pawn.
     */
    public JLabel getImage() {
		return this.image;
	}

	/**
     * This function sets the image of the pawn
     * 
     * @param image The image to be displayed
     */
    public void setImage(JLabel image) {
		this.image = image;
	}

}