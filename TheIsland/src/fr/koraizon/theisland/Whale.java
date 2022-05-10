package fr.koraizon.theisland;

public class Whale extends EffectPawn {

    /**
     * <p>
     * Constructeur par d�faut
     * </p>
     */
    public Whale() {
    }

    /**
     * <p>
     * R�alise l'effet de la baleine et coule les bateaux.
     * </p>
     * 
     * @param hexagon Case dans laquel est r�alis� l'effet.
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
}
