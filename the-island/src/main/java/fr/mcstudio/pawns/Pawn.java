package fr.mcstudio.pawns;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.enums.ExplorerStatus;

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

    public void findPath(Hexagon actualPosition, Board board, int distance, List<Hexagon> listHexagon) {
        listHexagon.clear();

        //---------------------------------------------------------- A changer, faut regarder les movePoint et moveCost je pense
        if (this instanceof Explorer) {
            Explorer explorer = (Explorer) this;
            if (explorer.getStatus() == ExplorerStatus.SWIMMER) {
                distance = 1;
            }
        }

        List<Hexagon> tmp = new ArrayList<Hexagon>();
        tmp.add(actualPosition);
        for (int i = 0; i < distance; i++) {
            for (Hexagon hexagon : tmp) {
                this.findPathAux(hexagon, board, listHexagon);
            }
            List<Hexagon> mem = new ArrayList<Hexagon>();
            mem.addAll(tmp);
            
            tmp.addAll(listHexagon);
            for (Hexagon hexagon : mem) {
                if (tmp.contains(hexagon)) {
                    tmp.remove(hexagon);
                }
            }
        }
    }
    
    private void findPathAux(Hexagon actualPosition, Board board, List<Hexagon> listHexagon) {
    }
}
