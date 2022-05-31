/*
 * Nom de classe : SeaSnake
 *
 * Description   : Gestion des serpents de mer du jeu The Island
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
 * It's a class that represents a sea snake, which is a pawn that can kill explorers and sink boats
 */
public class SeaSnake extends EffectPawn {

    // It's a constructor for SeaSnake.
    public SeaSnake(Board board) {
        super(1);
        this.board = board;
    }

    // It's a private variable that is used to store the board of the game.
    private Board board;

    /**
     * If there is a boat or an explorer on the hexagon, then the boat sinks and the explorer dies
     * 
     * @param hexagon the hexagon that the sea snake is on
     */
    public void makeEffect(Hexagon hexagon) {
        if ((hexagon.getBoat() != null
                && !hexagon.getBoat().getExplorerList().isEmpty())
                || !hexagon.getExplorerList().isEmpty()) {
            this.board.getExternalPanel().setAnimationType(AnimationType.SEASNAKE_ATTACK);
            this.board.setDisplayExternalPanel(true);
            this.board.getExternalPanel().setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
        }
        if (hexagon.getBoat() != null) {
            if (!hexagon.getBoat().getExplorerList().isEmpty()) {
                hexagon.getBoat().sunk(hexagon);
            }
        }
        for (Explorer e : hexagon.getExplorerList()) {
            e.setStatus(ExplorerStatus.DEAD);
        }
        hexagon.getExplorerList().clear();
    }


    /**
     * It takes a hexagon, a board, a list of hexagons, and a distance, and adds the hexagon to the
     * list of hexagons if it's not already in the list, and if it's a sea hexagon
     * 
     * @param actualPosition The current position of the explorer
     * @param board the board of the game
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
                
                if (!hexagon.getExplorerList().isEmpty()
                        && hexagon.getBoat() != null && !hexagon.getBoat().isEmpty()) {
                    hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.DEATH));
                } else {
                    hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.NORMAL));
                }
            }
        }
    }
}