package fr.koraizon.theisland;

import fr.koraizon.theisland.enums.ExplorerStatus;

public class Shark extends EffectPawn{
	 /**
     * <p>
     * Constructeur par d�faut
     * </p>
     */
    public Shark() {
    }

    /**
     * <p>
     * R�alise l'effet du requin et tue les explorateurs.
     * </p>
     * 
     * @param hexagon Case dans laquel est r�alis� l'effet.
     * @since 1.0
     * 
     */
    public void makeEffect(Hexagon hexagon) {
        for (Explorer e : hexagon.getExplorerList()) {
            e.setStatus(ExplorerStatus.DEAD);
        }
        hexagon.getExplorerList().clear();
    }
}
