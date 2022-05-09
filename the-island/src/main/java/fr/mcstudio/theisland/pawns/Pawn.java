package fr.mcstudio.theisland.pawns;

import javax.swing.JLabel;
import fr.mcstudio.theisland.board.Hexagon;

@SuppressWarnings("serial")
public class Pawn extends JLabel {
    /**
     * Default constructor
     */
    public Pawn() {
    }

    /**
     * 
     */
    public void move(Hexagon oldPosition, Hexagon newPosition) {
    }

    public void setImage(int nbPawns) {
        if (this instanceof Shark) {

        } else if (this instanceof Whale) {

        } else if (this instanceof SeaSnake) {

        } else if (this instanceof Explorer) {

        }
    }
}
