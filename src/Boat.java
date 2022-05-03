/*
 * Nom de classe : Boat
 *
 * Description   : Gestion des bateaux du jeu The Island 
 *
 * Version       : 1.0
 *
 * Date          : 01/05/2022
 * 
 * Copyright     : Lucas Neto
 */

import java.util.*;

/**
 * Gestion des bateaux du jeu The Island
 * explication supplémentaire si nécessaire
 * 
 * @version 1.0
 *
 * @see Pawn.java
 * @author Lucas Neto
 */
public class Boat extends Pawn {

    /**
     * Default constructor
     */
    public Boat() {
        this.explorerList = new ArrayList<Explorer>();
    }

    /**
     * 
     */
    public List<Explorer> explorerList;

    /**
     * 
     */
    public void addExplorer(Explorer explorer) {
        this.explorerList.add(explorer);
    }

    /**
     * 
     */
    public void removeExplorer(Explorer explorer) {
        this.explorerList.remove(explorer);
    }

    /**
     * 
     */
    public Boolean isOwnedBy(Player player) {
        int[] color = new int[4];
        int i = 0;
        int maxAt = 0;

        for (i = 0; i < this.explorerList.size(); i++) {
            color[this.explorerList.get(i).getColor()] += 1;
        }

        for (i = 0; i < color.length; i++) {
            maxAt = color[i] > color[maxAt] ? i : maxAt;
        }

        return (color[player.color] >= color[maxAt]);
    }

    /**
     * 
     */
    public void sunk(Board board) {
        for (Explorer explorer : this.explorerList) {
            explorer.getOff(this);
        }
        this.removeFromBoard(board);
    }
}