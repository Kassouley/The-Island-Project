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

@SuppressWarnings("serial")


/**
 * It creates a new class called Explorer that extends the Pawn class.
 */
public class Explorer extends Pawn {

    // The above code is creating a constructor for the Explorer class.
    public Explorer(Color color, int treasureValue) {
        super(3);
        this.color = color;
        this.treasureValue = treasureValue;
        this.status = ExplorerStatus.NORMAL;
    }

    // Declaring a variable for the color of the pawn.
    private Color color;

    // Declaring a variable for the treasure value of the explorer.
    private int treasureValue;

    // Declaring a variable called status of type ExplorerStatus.
    private ExplorerStatus status;

    /**
     * This function returns the color of the current object
     * 
     * @return The color of the explorer.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * This function returns the value of the treasure
     * 
     * @return The treasure value of the explorer object.
     */
    public int getTreasureValue() {
        return this.treasureValue;
    }

    /**
     * This function sets the status of the explorer to the new status
     * 
     * @param newStatus The new status of the explorer.
     */
    public void setStatus(ExplorerStatus newStatus) {
        this.status = newStatus;
    }

    /**
     * This function returns the status of the explorer
     * 
     * @return The status of the explorer.
     */
    public ExplorerStatus getStatus() {
        return this.status;
    }
   
    /**
     * The function is called when a pawn is moved from one hexagon to another
     * 
     * @param oldPosition Hexagon
     * @param newPosition Hexagon
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
                    newPosition.getSeaSnakeList().get(0)
                    		.makeEffect(newPosition);
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
     * The function is called when an explorer is moved from one hexagon to a boat
     * 
     * @param oldPosition the hexagon the explorer is currently on
     * @param boat Boat object
     * @param boatPosition the hexagon the boat is on
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
     * The function move() is used to move an explorer from a boat to a hexagon
     * 
     * @param boat the boat the explorer is on
     * @param newPosition Hexagon
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
     * This function removes the explorer from the old boat, adds the explorer to the new boat, and
     * then checks if there are any whales or sea snakes on the new boat position. If there are, it
     * makes the effect of the whale or sea snake on the new boat position
     * 
     * @param oldBoat the boat the explorer is currently on
     * @param newBoat The boat that the explorer is moving to
     * @param newBoatPosition the hexagon the boat is moving to
     */
    public void move(Boat oldBoat, Boat newBoat, Hexagon newBoatPosition) {
        oldBoat.removeExplorer(this);
        newBoat.addExplorer(this);
        if (!newBoatPosition.getWhaleList().isEmpty()) {
            newBoatPosition.getWhaleList().get(0)
            		.makeEffect(newBoatPosition);
        }
        if (!newBoatPosition.getSeaSnakeList().isEmpty()) {
            newBoatPosition.getSeaSnakeList().get(0)
            		.makeEffect(newBoatPosition);
        }
    }

    /**
     * It finds all the hexagons that are reachable from the current hexagon, and adds them to a list
     * 
     * @param actualPosition The current position of the explorer
     * @param board the board of the game
     * @param movePointLeft the number of moves the explorer can make
     * @param hexagonTripletList a list of hexagons, the distance from the starting hexagon, and the
     * type of hexagon (normal, boat, death)
     */
    public void findPath(Hexagon actualPosition, Board board, 
    		int movePointLeft, 
    		TripletList<Hexagon,Integer,HexagonListType> hexagonTripletList) {
        hexagonTripletList.clear();
        
        int distance = Math.min(movePointLeft, this.getMovePoint());

        List<Hexagon> tmp = new ArrayList<Hexagon>();
        tmp.add(actualPosition);
        hexagonTripletList.add(new Triplet<Hexagon, Integer, 
        		HexagonListType>(actualPosition, 1, HexagonListType.BOAT));
        
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
                        && hexagonTripletList.get(index)
                        		.getRight() != HexagonListType.DEATH) {
                        
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
                    hexagonTripletList.add(
                    		new Triplet<Hexagon, Integer, HexagonListType>
                    		(actualPosition, 1, HexagonListType.DEATH));
                } else {
                    hexagonTripletList.remove(0);
                    if (distance > 0) {
                        hexagonTripletList.add(
                        		new Triplet<Hexagon, Integer, HexagonListType>
                        		(actualPosition, 1, HexagonListType.NORMAL));
                    }
                }
            } else {
                if (!actualPosition.getBoat().isFull()) {
                    if (!actualPosition.getWhaleList().isEmpty()
                            || !actualPosition.getSeaSnakeList().isEmpty()) {
                        hexagonTripletList.remove(0);
                        hexagonTripletList.add(
                        		new Triplet<Hexagon, Integer, HexagonListType>
                        		(actualPosition, 1, HexagonListType.DEATH));
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

    /**
     * It takes a hexagon, a board, a list of hexagons, and a distance, and adds to the list of
     * hexagons all the hexagons that are adjacent to the given hexagon, and the distance to them
     * 
     * @param actualPosition The current position of the explorer
     * @param board the board
     * @param hexagonTripletList a list of Triplets, each containing a Hexagon, an Integer, and a
     * HexagonListType.
     * @param distance the distance from the starting hexagon
     */
    public void findPathAux(Hexagon actualPosition, Board board, 
    		TripletList<Hexagon,Integer,HexagonListType> hexagonTripletList, 
    		int distance) {
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
                        && actualPosition.getBoat()
                        .getExplorerList().contains(this)) {
                    if (hexagon.getBoat() != null
                            && !hexagon.getBoat().isFull()) {
                        if (!hexagon.getWhaleList().isEmpty()
                                || !hexagon.getSeaSnakeList().isEmpty()) {
                            hexagonTripletList.add(
                            		new Triplet<Hexagon,Integer,HexagonListType>
                            		(hexagon, distance, HexagonListType.DEATH));
                        } else {
                            hexagonTripletList.add(
                            		new Triplet<Hexagon,Integer,HexagonListType>
                            		(hexagon, distance, HexagonListType.BOAT));
                        }
                    } else if (hexagon.getType() != HexagonType.SEA) {
                        hexagonTripletList.add(
                        		new Triplet<Hexagon,Integer,HexagonListType>
                        		(hexagon, distance, HexagonListType.NORMAL));
                    }
                } else {
                    if (hexagon.getBoat() != null && !hexagon.getBoat().isFull()
                            && actualPosition.getType() != HexagonType.SEA) {
                        hexagonTripletList.add(
                        		new Triplet<Hexagon,Integer,HexagonListType>
                        		(hexagon, distance, HexagonListType.BOAT));
                    } else {
                        if (hexagon.getSharkList().isEmpty()
                                && hexagon.getSeaSnakeList().isEmpty()) {

                            hexagonTripletList.add(
                            		new Triplet<Hexagon,Integer,HexagonListType>
                            		(hexagon, distance, HexagonListType.NORMAL));
                        } else {
                            hexagonTripletList.add(
                            		new Triplet<Hexagon,Integer,HexagonListType>
                            		(hexagon, distance, HexagonListType.DEATH));
                        }
                    }
                }
            }
        }
    }

    /**
     * It creates an image for a pawn
     * 
     * @param resolution the size of the resolution
     */
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
        scaleImage = icon.getImage().getScaledInstance(resolution, 
        		resolution, 
        		Image.SCALE_SMOOTH);
        
        icon.setImage(scaleImage);

        this.image.setIcon(icon);
    }
}