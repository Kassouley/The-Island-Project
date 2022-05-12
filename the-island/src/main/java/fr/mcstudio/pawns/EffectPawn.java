/*
 * Nom de classe : EffectPawn
 *
 * Description   : Gestion des pions à effet du jeu The Island
 *
 * Version       : 1.0
 *
 * Date          : 06/05/2022
 * 
 * Copyright     : Lucas Neto
 */

package fr.mcstudio.pawns;

import java.util.ArrayList;
import java.util.List;

import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;

/**
 * <p>
 * Gestion des pions à effet du jeu The Island
 * </p>
 *
 * @version 1.0
 *
 * @see Pawn.java
 * @author Lucas Neto
 */
public class EffectPawn extends Pawn {

    /**
     * <p>
     * Constructeur par défaut
     * </p>
     */
    public EffectPawn() {

    }

    /**
     * <p>
     * Réalise l'effet du pion à effet.
     * </p>
     * 
     * @param hexagon Case dans laquel est réalisé l'effet.
     * @since 1.0
     * 
     */
    public void makeEffect(Hexagon hexagon) {
    }

    /**
     * <p>
     * Déplace le pion en réalisant son effet.
     * </p>
     * 
     * @param oldPosition Ancienne case du pion.
     * @param newPosition Nouvelle case du pion.
     * @since 1.0
     */
    public void move(Hexagon oldPosition, Hexagon newPosition) {
        oldPosition.removePawn(this);
        newPosition.addPawn(this);
        if (!newPosition.getExplorerList().isEmpty() || newPosition.getBoat() != null) {
            this.makeEffect(newPosition);
        }
    }

    protected void findPathAux(Hexagon actualPosition, Board board, List<Hexagon> listHexagon) {
        List<Hexagon> tmp = new ArrayList<Hexagon>();

        tmp.add(board.getTopLeft(actualPosition));
        tmp.add(board.getTopRight(actualPosition));
        tmp.add(board.getLeft(actualPosition));
        tmp.add(board.getRight(actualPosition));
        tmp.add(board.getBottomLeft(actualPosition));
        tmp.add(board.getBottomRight(actualPosition));

        for (Hexagon hexagon : tmp) {
            if (hexagon != null
                    && !listHexagon.contains(hexagon)) {
                listHexagon.add(hexagon);
            }
        }
    }
}
