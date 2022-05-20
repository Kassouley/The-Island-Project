/*
 * Nom de classe : Boat
 *
 * Description   : Gestion des bateaux du jeu The Island 
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
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.game.Player;
import fr.mcstudio.util.Pair;
import fr.mcstudio.util.PairList;

/**
 * <p>
 * Gestion des bateaux du jeu The Island
 * </p>
 *
 * @version 2.0
 *
 * @see Pawn.java
 * @author Lucas Neto
 */
@SuppressWarnings("serial")
public class Boat extends Pawn {

    /**
     * <p>
     * Constructeur par d�faut
     * </p>
     */
    public Boat() {
        this.explorerList = new ArrayList<Explorer>();
    }

    /**
     * <p>
     * Liste des explorateur pr�sent sur le bateau.
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
     * @param explorer l'explorateur � ajouter sur le bateau
     * @since 1.0
     * @see Explorer.java
     */
    public void addExplorer(Explorer explorer) {
        this.explorerList.add(explorer);
    }

    /**
     * <p>
     * V�rifie si le bateau est plein ou non
     * </p>
     * 
     * @return vrai si le nombre d'exploreur dessus est >= 3, faux sinon.
     * @since 1.0
     */
    public boolean isFull() {
        return (this.explorerList.size() >= 3);
    }

    public boolean isEmpty() {
        return this.explorerList.isEmpty();
    }

    /**
     * <p>
     * Retire un explorateur du bateau.
     * </p>
     * 
     * @param explorer l'explorateur � retirer du bateau.
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
     * La m�thode compte le nombre d'explorateur d'un joueur sur le bateau.
     * Si ce nombre est maximum alors il peut controler le bateau.
     * </p>
     * 
     * @param player joueur qui souhaite controler le bateau
     * @return Vrai, si le nombre d'exploreur de la couleur du joueur est maximum,
     *         faux sinon.
     * @since 1.0
     * @see Player.java
     */
    public boolean isOwnedBy(Player player) {
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
     * Fait couler un bateau choisit et retire tout les explorateurs � bord.
     * </p>
     * 
     * @param boatPosition Case o� se situe le bateau � retir� du jeu
     * @since 2.0
     * @see Explorer.java
     */
    public void sunk(Hexagon boatPosition) {
        for (Explorer explorer : this.explorerList) {
            boatPosition.addPawn(explorer);
            explorer.setStatus(ExplorerStatus.SWIMMER);
        }
        this.explorerList.clear();
        boatPosition.removePawn(this);
    }

    /**
     * <p>
     * D�place le bateau d'une case vers une autre case.
     * </p>
     * 
     * @param oldPosition case o� se trouvait le bateau.
     * @param newPosition case vers laquel est d�plac� le bateau.
     * @since 2.0
     */
    public void move(Hexagon oldPosition, Hexagon newPosition) {
        oldPosition.removePawn(this);
        newPosition.addPawn(this);
        if (!this.explorerList.isEmpty()) {
            if (!newPosition.getWhaleList().isEmpty()) {
                newPosition.getWhaleList().get(0).makeEffect(newPosition);
            }
            if (!newPosition.getSeaSnakeList().isEmpty()) {
                newPosition.getSeaSnakeList().get(0).makeEffect(newPosition);
            }
        }
    }

    public void findPathAux(Hexagon actualPosition, Board board, PairList<Hexagon,HexagonListType> hexagonPairList) {
        List<Hexagon> tmp = new ArrayList<Hexagon>();

        tmp.add(board.getTopLeft(actualPosition));
        tmp.add(board.getTopRight(actualPosition));
        tmp.add(board.getLeft(actualPosition));
        tmp.add(board.getRight(actualPosition));
        tmp.add(board.getBottomLeft(actualPosition));
        tmp.add(board.getBottomRight(actualPosition));

        for (Hexagon hexagon : tmp) {
            if (hexagon != null
                    && !hexagonPairList.containsInPair(hexagon)
                    && hexagon.getType() == HexagonType.SEA) {

                if (this.explorerList.isEmpty()
                        || (hexagon.getSeaSnakeList().isEmpty()
                        && hexagon.getWhaleList().isEmpty())) {
                            
                    hexagonPairList.add(new Pair<Hexagon,HexagonListType>(hexagon, HexagonListType.NORMAL));
                } else {
                    hexagonPairList.add(new Pair<Hexagon,HexagonListType>(hexagon, HexagonListType.DEATH));
                }
            }
        }
    }

}