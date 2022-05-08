package fr.koraizon.theisland;

import fr.koraizon.theisland.enums.Color;
import fr.koraizon.theisland.enums.ExplorerStatus;

public class Explorer extends Pawn {

    /**
     * <p>
     * Constructeur par d�faut
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
     * Valeur du tr�sor de l'explorateur.
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
     * Accesseur de la valeur de tr�sor de l'explorateur.
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
     * D�place l'explorateur d'une case vers une autre case.
     * </p>
     * 
     * @param oldPosition case o� se trouvait l'explorateur.
     * @param newPosition case vers laquel est d�plac� l'explorateur.
     * @since 2.0
     */
    public void move(Hexagon oldPosition, Hexagon newPosition) {
        oldPosition.removePawn(this);
        switch (newPosition.getType()) {
            case TILES:
                this.status = ExplorerStatus.NORMAL;
                newPosition.addPawn(this);
                break;
            case SEA:
                if (!newPosition.getSeaSnakeList().isEmpty() ||
                        !newPosition.getSharkList().isEmpty()) {
                    this.status = ExplorerStatus.DEAD;
                } else {
                    this.status = ExplorerStatus.SWIMMER;
                    newPosition.addPawn(this);
                }
                break;
            case ISLAND:
                this.status = ExplorerStatus.SAVED;
                newPosition.addPawn(this);
                break;
            default:
                break;
        }
    }

    /**
     * <p>
     * D�place l'explorateur d'une case vers un bateau.
     * </p>
     * 
     * @param oldPosition  case o� se trouvait l'explorateur.
     * @param boat         bateau sur lequel est d�plac� l'explorateur.
     * @param boatPosition Case o� se situe le bateau destination.
     * @since 2.0
     */
    public void move(Hexagon oldPosition, Boat boat, Hexagon boatPosition) {
        oldPosition.removePawn(this);
        boat.addExplorer(this);
        this.status = ExplorerStatus.ONBOAT;

        if (!boatPosition.getWhaleList().isEmpty()) {
            boatPosition.getWhaleList().get(0).makeEffect(boatPosition);
        }
        if (!boatPosition.getSeaSnakeList().isEmpty()) {
            boatPosition.getSeaSnakeList().get(0).makeEffect(boatPosition);
        }
    }

    /**
     * <p>
     * D�place l'explorateur d'un bateau vers une position.
     * </p>
     * 
     * @param boat        bateau dans lequel est retir� l'explorateur.
     * @param newPosition case vers lequel est d�plac� l'explorateur.
     * @since 1.0
     */
    public void move(Boat boat, Hexagon newPosition) {
        boat.removeExplorer(this);
        switch (newPosition.getType()) {
            case TILES:
                // Move impossible
                this.status = ExplorerStatus.NORMAL;
                newPosition.addPawn(this);
                break;
            case SEA:
                if (!newPosition.getSharkList().isEmpty()) {
                    this.status = ExplorerStatus.DEAD;
                } else {
                    this.status = ExplorerStatus.SWIMMER;
                    newPosition.addPawn(this);
                }
                break;
            case ISLAND:
                this.status = ExplorerStatus.SAVED;
                newPosition.addPawn(this);
                break;
            default:
                break;
        }
    }

    /**
     * <p>
     * D�place l'explorateur d'un bateau vers un autre bateau.
     * </p>
     * 
     * @param oldBoat         bateau dans lequel est retir� l'explorateur.
     * @param newBoat         bateau vers lequel se d�place l'explorateur.
     * @param newBoatPosition Case o� se situe le bateau destination.
     * @since 2.0
     */
    public void move(Boat oldBoat, Boat newBoat, Hexagon newBoatPosition) {
        oldBoat.removeExplorer(this);
        newBoat.addExplorer(this);
        if (!newBoatPosition.getWhaleList().isEmpty()) {
            newBoatPosition.getWhaleList().get(0).makeEffect(newBoatPosition);
        }
        if (!newBoatPosition.getSeaSnakeList().isEmpty()) {
            newBoatPosition.getSeaSnakeList().get(0).makeEffect(newBoatPosition);
        }
    }

}
