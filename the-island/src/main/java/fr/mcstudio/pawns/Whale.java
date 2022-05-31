/*
 * Nom de classe : Whale
 *
 * Description   : Gestion des baleine du jeu The Island
 *
 * Version       : 2.0
 *
 * Date          : 07/05/2022
 * 
 * Copyright     : Lucas Neto
 */

package fr.mcstudio.pawns;

import java.util.ArrayList;
import java.util.List;

import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.enums.AnimationType;
import fr.mcstudio.enums.ExternalPanelState;
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.util.Triplet;
import fr.mcstudio.util.TripletList;

/**
 * <p>
 * Gestion des baleine du jeu The Island
 * </p>
 *
 * @version 2.0
 * 
 * @see EffectPawn.java
 * @author Lucas Neto
 */
@SuppressWarnings("serial")
public class Whale extends EffectPawn {

    // The constructor of the class Whale. It takes a Board as a parameter, and calls the constructor
    // of the superclass, EffectPawn, with the parameter 3.
    public Whale(Board board) {
        super(3);
        this.board = board;
    }
    
    // A variable that is used to store the board.
    private Board board;

   
    /**
     * If the hexagon has a boat, and the boat has an explorer, then the boat is sunk
     * 
     * @param hexagon the hexagon that the whale is on
     */
    public void makeEffect(Hexagon hexagon) {
        if (hexagon.getBoat() != null) {
            if (!hexagon.getBoat()
            		.getExplorerList().isEmpty()) {
                this.board.getExternalPanel()
                .setAnimationType(AnimationType.WHALE_ATTACK);
                this.board.setDisplayExternalPanel(true);
                this.board.getExternalPanel()
                .setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
                hexagon.getBoat().sunk(hexagon);
            }
        }
        if (!hexagon.getSharkList().isEmpty()) {
            hexagon.getSharkList().get(0).makeEffect(hexagon);
        }
    }
    
    /**
     * It takes a hexagon, a board, a list of hexagons, and a distance, and adds all the hexagons
     * around the given hexagon to the list of hexagons, if they are not already in the list, and if
     * they are sea hexagons
     * 
     * @param actualPosition The current position of the boat
     * @param board the board
     * @param hexagonTripletList A list of hexagons, their distance from the starting hexagon, and
     * their type (NORMAL or DEATH).
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
                
                if (hexagon.getBoat() != null && !hexagon.getBoat().isEmpty()) {
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