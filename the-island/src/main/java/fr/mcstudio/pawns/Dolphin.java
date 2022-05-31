package fr.mcstudio.pawns;

import java.util.ArrayList;
import java.util.List;

import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.util.Triplet;
import fr.mcstudio.util.TripletList;

@SuppressWarnings("serial")

// It's creating a new class called Dolphin, which is a subclass of Pawn.
public class Dolphin extends Pawn {
    
    // Default constructor : It's calling the constructor of the parent class, which is Pawn.
    public Dolphin() {
        super(3);
    }

   /**
    * It takes a hexagon, a board, a list of hexagons, and a distance, and adds the hexagon to the list
    * of hexagons if it's not already in the list, and if it's a sea hexagon
    * 
    * @param actualPosition The current position of the player
    * @param board the board
    * @param hexagonTripletList A list of Triplets, which are a combination of a Hexagon, an Integer,
    * and a HexagonListType.
    * @param distance the distance from the starting point
    */
    public void findPathAux(Hexagon actualPosition, Board board, 
    		TripletList<Hexagon,Integer,HexagonListType> hexagonTripletList, 
    		int distance) {
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
                
                if (!hexagon.getSharkList().isEmpty()
                        || !hexagon.getSeaSnakeList().isEmpty()) {
                    hexagonTripletList.add(
                    		new Triplet<Hexagon,Integer,HexagonListType>
                    		(hexagon, distance, HexagonListType.DEATH));
                } else {
                    hexagonTripletList.add(
                    		new Triplet<Hexagon,Integer,HexagonListType>
                    		(hexagon, distance, HexagonListType.NORMAL));
                }
            }
        }
    }
}
