package fr.mcstudio.pawns;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import java.awt.Color;
import java.awt.Image;

import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.enums.ExplorerStatus;

@SuppressWarnings("serial")
public class Pawn extends JPanel {
    /**
     * <p>
     * Constructeur par d�faut.
     * </p>
     */
    public Pawn() {
    }

    private JLabel index;

    private JLabel image;

    /**
     * 
     */
    public void move(Hexagon oldPosition, Hexagon newPosition) {
    }

    /**
     * 
     */
    public void setPosition(int x, int y, int resolution) {
        this.setBounds(x, y, (int) (68 * ((float) resolution / 90)), (int) (68 * ((float) resolution / 90)));
    }

    /**
     * 
     * @param actualPosition
     * @param board
     * @param distance
     * @param listHexagon
     */
    public void findPath(Hexagon actualPosition, Board board, int distance, List<Hexagon> listHexagon) {
        listHexagon.clear();

        // ---------------------------------------------------------- A changer, faut
        // regarder les movePoint et moveCost je pense
        if (this instanceof Explorer) {
            Explorer explorer = (Explorer) this;
            if (explorer.getStatus() == ExplorerStatus.SWIMMER) {
                distance = 1;
            }
        }

        List<Hexagon> tmp = new ArrayList<Hexagon>();
        tmp.add(actualPosition);
        for (int i = 0; i < distance; i++) {
            for (Hexagon hexagon : tmp) {
                this.findPathAux(hexagon, board, listHexagon);
            }
            List<Hexagon> mem = new ArrayList<Hexagon>();
            mem.addAll(tmp);

            tmp.addAll(listHexagon);
            for (Hexagon hexagon : mem) {
                if (tmp.contains(hexagon)) {
                    tmp.remove(hexagon);
                }
            }
        }
    }

    /**
     * 
     * @param actualPosition
     * @param board
     * @param listHexagon
     */
    private void findPathAux(Hexagon actualPosition, Board board, List<Hexagon> listHexagon) {
    }

    public void createPawnImage(int size, int index) {
        ImageIcon icon = null;
        Image scaleImage;
        if (this instanceof Shark) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_requin.png"));

        } else if (this instanceof Whale) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_baleine.png"));

        } else if (this instanceof SeaSnake) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_serpent_de_mer.png"));

        } else if (this instanceof Boat) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_bateau.png"));

        }
        scaleImage = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);

        JLabel textDisplay = new JLabel(Integer.toString(index));

        icon.setImage(scaleImage);

        this.setIcon(icon);
    }

}