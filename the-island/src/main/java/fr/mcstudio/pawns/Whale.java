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
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.util.Pair;
import fr.mcstudio.util.PairList;

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

    /**
     * <p>
     * Constructeur par défaut
     * </p>
     */
    public Whale() {
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
            if (!hexagon.getBoat().explorerList.isEmpty()) {
                hexagon.getBoat().sunk(hexagon);
            }
        }
        if (!hexagon.getSharkList().isEmpty()) {
            hexagon.getSharkList().get(0).makeEffect(hexagon);
        }
    }
    
    public void findPathAux(Hexagon actualPosition, Board board, PairList<Hexagon,HexagonListType> hexagonPairList) {
        List<Hexagon> tmp = new ArrayList<Hexagon>();

        tmp.add(board.getTopLeft(actualPosition));
        tmp.add(board.getTopRight(actualPosition));
        tmp.add(board.getLeft(actualPosition));
        tmp.add(board.getRight(actualPosition));
        tmp.add(board.getBottomLeft(actualPosition));
        tmp.add(board.getBottomRight(actualPosition));

        for (Hexagon hexagon : tmp) {
            if (hexagon != null
                    && !hexagonPairList.containsInPair(hexagon)
                    && hexagon.getType() == HexagonType.SEA) {
                
                if (hexagon.getBoat() != null && !hexagon.getBoat().isEmpty()) {
                    hexagonPairList.add(new Pair<Hexagon,HexagonListType>(hexagon, HexagonListType.DEATH));
                } else {
                    hexagonPairList.add(new Pair<Hexagon,HexagonListType>(hexagon, HexagonListType.NORMAL));
                }
            }
        }
    }

}