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

}
