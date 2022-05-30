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

    private Board board;

    /**
     * <p>
     * Constructeur par défaut
     * </p>
     */
    public Whale(Board board) {
        super(3);
        this.board = board;
    }

    /**
     * <p>
     * Réalise l'effet de la baleine et coule les bateaux.
     * </p>
     * 
     * @param hexagon Case dans laquel est réalisé l'effet.
     * @since 1.0
     * 
     */
    public void makeEffect(Hexagon hexagon) {
        if (hexagon.getBoat() != null) {
            if (!hexagon.getBoat().getExplorerList().isEmpty()) {
                this.board.getExternalPanel().setAnimationType(AnimationType.WHALE_ATTACK);
                this.board.setDisplayExternalPanel(true);
                this.board.getExternalPanel().setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
                hexagon.getBoat().sunk(hexagon);
            }
        }
        if (!hexagon.getSharkList().isEmpty()) {
            hexagon.getSharkList().get(0).makeEffect(hexagon);
        }
    }
    
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
                    && hexagon.isSea()) {
                
                if (hexagon.getBoat() != null && !hexagon.getBoat().isEmpty()) {
                    hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.DEATH));
                } else {
                    hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.NORMAL));
                }
            }
        }
    }

}