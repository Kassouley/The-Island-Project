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
 * <p>
 * Gestion des bateaux du jeu The Island
 * </p>
 *
 * @version 1.0
 *
 * @see Pawn.java
 * @author Lucas Neto
 */
public class Boat extends Pawn {

    /**
     * <p>
     * Constructeur par défaut
     * </p>
     */
    public Boat() {
        this.explorerList = new ArrayList<Explorer>();
    }

    /**
     * <p>
     * Liste des explorateur présent sur le bateau.
     * </p>
     * 
     * @see Explorer.java
     */
    public List<Explorer> explorerList;

    /**
     * <p>
     * Ajoute un explorateur sur le bateau
     * </p>
     * 
     * @param explorer l'explorateur à ajouter sur le bateau
     * @since 1.0
     * @see Explorer.java
     */
    public void addExplorer(Explorer explorer) {
        this.explorerList.add(explorer);
    }

    /**
     * <p>
     * Retire un explorateur du bateau
     * </p>
     * 
     * @param explorer l'explorateur à retirer du bateau
     * @since 1.0
     * @see Explorer.java
     */
    public void removeExplorer(Explorer explorer) {
        this.explorerList.remove(explorer);
    }

    /**
     * <p>
     * Permet de savoir qui peut controler le bateau.
     * </p>
     * <p>
     * La méthode compte le nombre d'explorateur d'un joueur sur le bateau.
     * Si ce nombre est maximum alors il peut controler le bateau.
     * </p>
     * 
     * @param player joueur qui souhaite controler le bateau
     * @return Vrai, si le nombre d'exploreur de la couleur du joueur est maximum,
     *         faux sinon.
     * @since 1.0
     * @see Player.java
     */
    public Boolean isOwnedBy(Player player) {
        int[] colorCount = new int[4];
        int i = 0;
        int maxAt = 0;

        for (i = 0; i < this.explorerList.size(); i++) {
            colorCount[this.explorerList.get(i).getColor().ordinal()] += 1;
        }

        for (i = 0; i < colorCount.length; i++) {
            maxAt = colorCount[i] > colorCount[maxAt] ? i : maxAt;
        }

        return (colorCount[player.getColor().ordinal()] >= colorCount[maxAt]);
    }

    /**
     * <p>
     * Fait couler un bateau choisit et retire tout les explorateurs à bord.
     * </p>
     * 
     * @param board Plateau dans lequel le bateau est retiré du jeu
     * @since 1.0
     * @see Board.java
     * @see Explorer.java
     */
    public void sunk(Board board, Hexagon BoatPosition) {
        for (Explorer explorer : this.explorerList) {
            explorer.move(this, BoatPosition);
        }
        this.removeFromBoard(board);
    }
}