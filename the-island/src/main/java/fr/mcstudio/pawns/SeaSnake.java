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

package fr.mcstudio.pawns;

import java.util.ArrayList;
import java.util.List;

import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.util.Triplet;
import fr.mcstudio.util.TripletList;

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
@SuppressWarnings("serial")
public class SeaSnake extends EffectPawn {

    /**
     * <p>
     * Constructeur par défaut
     * </p>
     */
    public SeaSnake() {
        super(1);
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
                    && !hexagonTripletList.containsInTriplet(hexagon)
                    && hexagon.getType() == HexagonType.SEA) {
                
                if (!hexagon.getExplorerList().isEmpty()
                        && hexagon.getBoat() != null && !hexagon.getBoat().isEmpty()) {
                    hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.DEATH));
                } else {
                    hexagonTripletList.add(new Triplet<Hexagon,Integer,HexagonListType>(hexagon, distance, HexagonListType.NORMAL));
                }
            }
        }
    }

    /*public void setImage() {
        this.setIcon(new ImageIcon(Pawn.class.getResource("/pion_serpent_de_mer.png")));
    }*/

}