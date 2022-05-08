package fr.koraizon.theisland;

public class EffectPawn extends Pawn {

    /**
     * <p>
     * Constructeur par d�faut
     * </p>
     */
    public EffectPawn() {

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
     * D�place le pion en r�alisant son effet
     * </p>
     * 
     * @param oldPosition Ancienne case du pion
     * @param newPosition Nouvelle case du pion
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
