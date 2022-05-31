/*
 * Nom de classe : Shark
 *
 * Description   : Gestion des requins du jeu The Island
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
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.ExternalPanelState;
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.util.Triplet;
import fr.mcstudio.util.TripletList;

@SuppressWarnings("serial")

/**
 * It's a class that represents a shark that can kill explorers
 */
public class Shark extends EffectPawn {

    // The constructor of the class Shark.
    public Shark(Board board) {
        super(2);
        this.board = board;
    }

    // A variable board.
    private Board board;

    /**
     * If the hexagon has explorers on it, then the board will display an animation and all the
     * explorers on the hexagon will die
     * 
     * @param hexagon the hexagon that the shark is on
     */
    public void makeEffect(Hexagon hexagon) {
        if (!hexagon.getExplorerList().isEmpty()) {
            this.board.getExternalPanel().setAnimationType(AnimationType.SHARK_ATTACK);
            this.board.setDisplayExternalPanel(true);
            this.board.getExternalPanel().setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
        }
        for (Explorer e : hexagon.getExplorerList()) {
            e.setStatus(ExplorerStatus.DEAD);
        }
        hexagon.getExplorerList().clear();
    }

    /**
     * It takes a hexagon, a board, a list of hexagons, and a distance, and adds all the hexagons
     * around the given hexagon to the list of hexagons, if they are not already in the list, and if
     * they are sea hexagons
     * 
     * @param actualPosition The current position of the explorer
     * @param board the board
     * @param hexagonTripletList a list of hexagons, their distance from the starting hexagon, and
     * their type (NORMAL, DEATH, or START)
     * @param distance the distance from the starting hexagon
     */
    public void findPathAux(Hexagon actualPosition, Board board, TripletList<Hexagon,Integer,HexagonListType> hexagonTripletList, int distance) {
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
                
                if (!hexagon.getExplorerList().isEmpty()) {
                    hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.DEATH));
                } else {
                    hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.NORMAL));
                }
            }
        }
    }
}