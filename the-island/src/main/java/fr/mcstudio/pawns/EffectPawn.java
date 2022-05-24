/*
 * Nom de classe : EffectPawn
 *
 * Description   : Gestion des pions � effet du jeu The Island
 *
 * Version       : 1.0
 *
 * Date          : 06/05/2022
 * 
 * Copyright     : Lucas Neto
 */

package fr.mcstudio.pawns;

import fr.mcstudio.board.Hexagon;

/**
 * <p>
 * Gestion des pions � effet du jeu The Island
 * </p>
 *
 * @version 1.0
 *
 * @see Pawn.java
 * @author Lucas Neto
 */
@SuppressWarnings("serial")
public class EffectPawn extends Pawn {

    /**
     * <p>
     * Constructeur par d�faut
     * </p>
     */
    public EffectPawn(int movePoint) {
        super(movePoint);
    }

    /**
     * <p>
     * R�alise l'effet du pion � effet.
     * </p>
     * 
     * @param hexagon Case dans laquel est r�alis� l'effet.
     * @since 1.0
     * 
     */
    public void makeEffect(Hexagon hexagon) {
    }

    /**
     * <p>
     * D�place le pion en r�alisant son effet.
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
}