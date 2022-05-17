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

import fr.mcstudio.board.Hexagon;
import fr.mcstudio.enums.ExplorerStatus;

/**
 * <p>
 * Gestion des requins du jeu The Island
 * </p>
 *
 * @version 2.0
 * 
 * @see EffectPawn.java
 * @author Lucas Neto
 */
@SuppressWarnings("serial")
public class Shark extends EffectPawn {

    /**
     * <p>
     * Constructeur par défaut
     * </p>
     */
    public Shark() {
    }

    /**
     * <p>
     * Réalise l'effet du requin et tue les explorateurs.
     * </p>
     * 
     * @param hexagon Case dans laquel est réalisé l'effet.
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