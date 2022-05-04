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
     * Accesseur de la couleur de l'explorateur.
     * </p>
     */
    public Color getColor() {
        return this.color;
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
     * Déplace l'explorateur d'une case vers une autre case.
     * </p>
     * 
     * @param oldPosition case où se trouvait l'explorateur.
     * @param newPosition case vers laquel est déplacé l'explorateur.
     * @since 1.0
     */
    public void move(Hexagon oldPosition, Hexagon newPosition) {
        oldPosition.getPawnsList().remove(this);
        switch (newPosition.type) {
            case TILES:
                this.status = ExplorerStatus.NORMAL;
                break;
            case SEA:
                this.status = ExplorerStatus.SWIMMER;
            default:
                break;
        }
        newPosition.getPawnsList().add(this);
    }

    /**
     * <p>
     * Déplace l'explorateur d'une case vers un bateau.
     * </p>
     * 
     * @param oldPosition case où se trouvait l'explorateur.
     * @param boat        bateau sur lequel est déplacé l'explorateur.
     * @since 1.0
     */
    public void move(Hexagon oldPosition, Boat boat) {
        oldPosition.getPawnsList().remove(this);
        this.status = ExplorerStatus.ONBOAT;
        boat.addExplorer(this);
    }

    /**
     * <p>
     * Déplace l'explorateur d'un bateau vers une position.
     * </p>
     * 
     * @param boat        bateau dans lequel est retiré l'explorateur.
     * @param newPosition case vers lequel est déplacé l'explorateur.
     * @since 1.0
     */
    public void move(Boat boat, Hexagon newPosition) {
        boat.removeExplorer(this);
        switch (newPosition.type) {
            case TILES:
                this.status = ExplorerStatus.NORMAL;
                break;
            case SEA:
                this.status = ExplorerStatus.SWIMMER;
                break;
            case ISLAND:
                this.status = ExplorerStatus.SAVED;
                break;
            default:
                break;
        }
        newPosition.getPawnsList().add(this);
    }

    /**
     * <p>
     * Déplace l'explorateur d'un bateau vers un autre bateau.
     * </p>
     * 
     * @param oldBoat bateau dans lequel est retiré l'explorateur.
     * @param newBoat bateau vers lequel se déplace l'explorateur.
     * @since 1.0
     */
    public void move(Boat oldBoat, Boat newBoat) {
        oldBoat.removeExplorer(this);
        newBoat.addExplorer(this);
    }

}
