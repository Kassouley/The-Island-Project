/*
 * Nom de classe : Explorer
 *
 * Description   : Gestion des explorateurs du jeu The Island 
 *
 * Version       : 2.0
 *
 * Date          : 07/05/2022
 * 
 * Copyright     : Lucas Neto
 */

package fr.mcstudio.pawns;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.Image;

import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.util.Triplet;
import fr.mcstudio.util.TripletList;

/**
 * <p>
 * Gestion des explorateurs du jeu The Island
 * </p>
 *
 * @version 2.0
 *
 * @see Pawn.java
 * @author Lucas Neto
 */
@SuppressWarnings("serial")
public class Explorer extends Pawn {

    /**
     * <p>
     * Constructeur par d�faut
     * </p>
     */
    public Explorer(Color color, int treasureValue) {
        super(3);
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
                this.status = ExplorerStatus.SWIMMER;
                this.setMovePoint(1);
                newPosition.addPawn(this);
                if (!newPosition.getSeaSnakeList().isEmpty()) {
                    newPosition.getSeaSnakeList().get(0).makeEffect(newPosition);
                } 
                break;
            case ISLAND:
                this.status = ExplorerStatus.SAVED;
                newPosition.addPawn(this);
                break;
            default:
                System.out.println("Error : Hexagon type doesn't set.");
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
        this.setMovePoint(0);
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
                    this.setMovePoint(0);
                    newPosition.addPawn(this);
                }
                break;
            case ISLAND:
                this.status = ExplorerStatus.SAVED;
                newPosition.addPawn(this);
                break;
            default:
                System.out.println("Error : Hexagon type doesn't set.");
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

    public void findPath(Hexagon actualPosition, Board board, int movePointLeft, TripletList<Hexagon,Integer,HexagonListType> hexagonTripletList) {
        hexagonTripletList.clear();
        
        int distance = Math.min(movePointLeft, this.getMovePoint());

        List<Hexagon> tmp = new ArrayList<Hexagon>();
        tmp.add(actualPosition);
        hexagonTripletList.add(new Triplet<Hexagon, Integer, HexagonListType>(actualPosition, 1, HexagonListType.BOAT));
        
        for (int i = 1; i <= distance; i++) {
            for (Hexagon hexagon : tmp) {
                this.findPathAux(hexagon, board, hexagonTripletList, i);
            }
            
            List<Hexagon> mem = new ArrayList<Hexagon>();
            List<Hexagon> hexagonList = hexagonTripletList.getLeftList();
            mem.addAll(tmp);

            tmp.clear();
            for (Hexagon hexagon : hexagonList) {
                int index = hexagonList.indexOf(hexagon);
                if ((this.getStatus() == ExplorerStatus.SWIMMER
                        || hexagon.getType() != HexagonType.SEA)
                        && hexagonTripletList.get(index).getRight() != HexagonListType.DEATH) {
                        
                    tmp.add(hexagon);
                }
            }

            for (Hexagon hexagon : mem) {
                if (tmp.contains(hexagon)) {
                    tmp.remove(hexagon);
                }
            }
        }

        if (actualPosition.getBoat() != null) {
            if (actualPosition.getBoat().getExplorerList().contains(this)) {
                if (!actualPosition.getSharkList().isEmpty()
                        || !actualPosition.getSeaSnakeList().isEmpty()) {
                    hexagonTripletList.remove(0);
                    hexagonTripletList.add(new Triplet<Hexagon, Integer, HexagonListType>(actualPosition, 1, HexagonListType.DEATH));
                } else {
                    hexagonTripletList.remove(0);
                    if (distance > 0) {
                        hexagonTripletList.add(new Triplet<Hexagon, Integer, HexagonListType>(actualPosition, 1, HexagonListType.NORMAL));
                    }
                }
            } else {
                if (!actualPosition.getBoat().isFull()) {
                    if (!actualPosition.getWhaleList().isEmpty()
                            || !actualPosition.getSeaSnakeList().isEmpty()) {
                        hexagonTripletList.remove(0);
                        hexagonTripletList.add(new Triplet<Hexagon, Integer, HexagonListType>(actualPosition, 1, HexagonListType.DEATH));
                    } else {
                        if (distance < 1) {
                            hexagonTripletList.remove(0);
                        }
                    }
                } else {
                    hexagonTripletList.remove(0);
                }
            }
        } else {
            hexagonTripletList.remove(0);
        }
    }

    public void findPathAux(Hexagon actualPosition, Board board, TripletList<Hexagon,Integer,HexagonListType> hexagonTripletList, int distance) {
        List<Hexagon> tmp = new ArrayList<Hexagon>();

        tmp.add(board.getTopLeft(actualPosition));
        tmp.add(board.getTopRight(actualPosition));
        tmp.add(board.getLeft(actualPosition));
        tmp.add(board.getRight(actualPosition));
        tmp.add(board.getBottomLeft(actualPosition));
        tmp.add(board.getBottomRight(actualPosition));

        for (Hexagon hexagon : tmp) {
            if (hexagon != null
                    && !hexagonTripletList.containsInTriplet(hexagon)) {

                if (actualPosition.getBoat() != null
                        && actualPosition.getBoat().getExplorerList().contains(this)) {
                    if (hexagon.getBoat() != null
                            && !hexagon.getBoat().isFull()) {
                        if (!hexagon.getWhaleList().isEmpty()
                                || !hexagon.getSeaSnakeList().isEmpty()) {
                            hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.DEATH));
                        } else {
                            hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.BOAT));
                        }
                    } else if (hexagon.getType() != HexagonType.SEA) {
                        hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.NORMAL));
                    }
                } else {
                    if (hexagon.getBoat() != null && !hexagon.getBoat().isFull()
                            && actualPosition.getType() != HexagonType.SEA) {
                        hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.BOAT));
                    } else {
                        if (hexagon.getSharkList().isEmpty()
                                && hexagon.getSeaSnakeList().isEmpty()) {

                            hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.NORMAL));
                        } else {
                            hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.DEATH));
                        }
                    }
                }
            }
        }
    }

    public void createImage(int resolution) {

        ImageIcon icon = null;
        Image scaleImage;

        String explorerPath = "/pion_";
        String explorerColor = null;
        switch (this.getColor()) {
            case RED:
                explorerColor = "rouge";
                break;
            case BLUE:
                explorerColor = "bleu";
                break;
            case YELLOW:
                explorerColor = "jaune";
                break;
            case GREEN:
                explorerColor = "vert";
                break;

        }
        explorerPath = explorerPath + explorerColor + ".png";

        icon = new ImageIcon(Pawn.class.getResource(explorerPath));
        scaleImage = icon.getImage().getScaledInstance(resolution, resolution, Image.SCALE_SMOOTH);
        icon.setImage(scaleImage);

        this.image.setIcon(icon);
    }
}