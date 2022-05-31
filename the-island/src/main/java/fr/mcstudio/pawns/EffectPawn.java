package fr.mcstudio.pawns;

import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.util.Triplet;
import fr.mcstudio.util.TripletList;

@SuppressWarnings("serial")

/**
 * It creates a class called EffectPawn that extends the Pawn class.
 */
public class EffectPawn extends Pawn {

    // The constructor of EffectPawn.
    public EffectPawn(int movePoint) {
        super(movePoint);
    }

    /**
     * Make the effect of the effect pawn
     * 
     * @param hexagon The hexagon that the effect is being applied to.
     */
    public void makeEffect(Hexagon hexagon) {
    }

    /**
     * Move the pawn from oldPosition to newPosition and make the effect of the effect pawn
     * 
     * @param oldPosition The hexagon the pawn is currently on.
     * @param newPosition The new position of the pawn
     */
    public void move(Hexagon oldPosition, Hexagon newPosition) {
        oldPosition.removePawn(this);
        newPosition.addPawn(this);
        if( this instanceof SeaSnake 
        		&& !newPosition.getExplorerList().isEmpty() 
        		|| newPosition.getBoat() != null ) {
        	this.makeEffect(newPosition);
        }
    }

    /**
     * It takes a hexagon, a board, and a list of hexagons, and adds all the hexagons on the board that
     * are empty
     * 
     * @param actualPosition The hexagon that the player is currently on.
     * @param board the board object
     * @param hexagonTripletList This is a list of hexagons that are available to move to.
     */
    public void findPathEffect(Hexagon actualPosition, Board board, 
    		TripletList<Hexagon,Integer,HexagonListType> hexagonTripletList) {
        hexagonTripletList.clear();
        Hexagon[][] hexagons = board.getHexagons();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 12; j++) {
                if (hexagons[i][j].getExplorerList().isEmpty()
                        && hexagons[i][j].getSharkList().isEmpty()
                        && hexagons[i][j].getWhaleList().isEmpty()
                        && hexagons[i][j].getSeaSnakeList().isEmpty()
                        && hexagons[i][j].getBoat() == null
                        && hexagons[i][j].getType() == HexagonType.SEA) {
                    hexagonTripletList.add(
                    		new Triplet<Hexagon,Integer,HexagonListType>
                    		(hexagons[i][j], 1, HexagonListType.NORMAL));
                }
            }
        }
    }
}