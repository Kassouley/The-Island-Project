/*
 * Nom de classe : Explorer
 *
 * Description   : Gestion des explorateurs du jeu The Island 
 *
 * Version       : 1.0
 *
 * Date          : 01/05/2022
 * 
 * Copyright     : Lucas Neto
 */

import java.util.*;

/**
 * <p>
 * Gestion des explorateurs du jeu The Island
 * </p>
 *
 * @version 1.0
 *
 * @see Pawn.java
 * @author Lucas Neto
 */
public class Explorer extends Pawn {

    /**
     * <p>
     * Constructeur par défaut
     * </p>
     */
    public Explorer(Color color, int treasureValue) {
        this.color = color;
        this.treasureValue = treasureValue;
        this.status = ExplorerStatus.NORMAL;
    }

    /**
     * <p>
     * Couleur de l'explorateur.
     * </p>
     * 
     * @see Color.java
     */
    private Color color;

    /**
     * <p>
     * Valeur du trésor de l'explorateur.
     * </p>
     */
    private int treasureValue;

    /**
     * <p>
     * Status de l'explorateur
     * </p>
     * 
     * @see ExplorerStatus.java
     */
    private ExplorerStatus status;

    /**
     * <p>
     * Mutateur de la couleur de l'explorateur.
     * </p>
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * <p>
     * Accesseur de la couleur de l'explorateur.
     * </p>
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * <p>
     * Mutateur de la valeur de trésor de l'explorateur.
     * </p>
     */
    public void setTreasureValue(int treasureValue) {
        this.treasureValue = treasureValue;
    }

    /**
     * <p>
     * Accesseur de la valeur de trésor de l'explorateur.
     * </p>
     */
    public int getTreasureValue() {
        return this.treasureValue;
    }

    /**
     * <p>
     * Mutateur du status de l'explorateur.
     * </p>
     */
    public void setStatus(ExplorerStatus newStatus) {
        this.status = newStatus;
    }

    /**
     * <p>
     * Accesseur du status de l'explorateur.
     * </p>
     * 
     * @since 1.0
     */
    public ExplorerStatus getStatus() {
        return this.status;
    }

    /**
     * <p>
     * Place l'explorateur sur un bateau.
     * </p>
     * 
     * @param boat bateau sur lequel est placé l'explorateur.
     * @since 1.0
     */
    public void getOn(Boat boat) {
        // todo
    }

    /**
     * <p>
     * Retire l'explorateur d'un bateau.
     * </p>
     * 
     * @param boat bateau dans lequel est retiré l'explorateur.
     * @since 1.0
     */
    public void getOff(Boat boat) {
        // todo
    }

}
