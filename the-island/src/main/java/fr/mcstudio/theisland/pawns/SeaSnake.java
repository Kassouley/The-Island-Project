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

package fr.mcstudio.theisland.pawns;

import fr.mcstudio.theisland.board.Hexagon;

/**
 * <p>
 * Gestion des serpents de mer du jeu The Island
 * </p>
 *
 * @version 2.0
 * 
 * @see EffectPawn.java
 * @author Lucas Neto
 */
public class SeaSnake extends EffectPawn {

    /**
     * <p>
     * Constructeur par défaut
     * </p>
     */
    public SeaSnake() {
    }

    /**
     * <p>
     * Réalise l'effet du serpent de mer, coule les bateaux et tue les explorateurs.
     * </p>
     * 
     * @param hexagon Case dans laquel est réalisé l'effet.
     * @since 1.0
     * 
     */
    public void makeEffect(Hexagon hexagon) {
        Whale whaleEffect = new Whale();
        Shark sharkEffect = new Shark();
        whaleEffect.makeEffect(hexagon);
        sharkEffect.makeEffect(hexagon);
    }

}