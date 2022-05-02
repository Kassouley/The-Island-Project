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
    public Player returnOwner() {
        Player boatOwner = new Player();
        int i = 0;
        return boatOwner;
        // TODO implement here
    }

}