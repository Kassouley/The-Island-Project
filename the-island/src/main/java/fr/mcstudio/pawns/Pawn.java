package fr.mcstudio.pawns;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.ExplorerStatus;

@SuppressWarnings("serial")
public class Pawn extends JPanel {
    /**
     * <p>
     * Constructeur par d�faut.
     * </p>
     */
    public Pawn() {
    	this.setLayout(null);
    	this.setOpaque(false);
    }

    protected JLabel index;

    protected JLabel image = new JLabel();

    /**
     * 
     */
    public void move(Hexagon oldPosition, Hexagon newPosition) {
    }

    /**
     * 
     */
    public void setPosition(int x, int y, int resolution, int size) {
        this.setBounds(x, y, (int) (size * ((float) resolution / 90)), (int) (size * ((float) resolution / 90)));
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

    public void createPawnImage(Hexagon hex) {
        ImageIcon icon = null;
        Image scaleImage;
        int index = 0;
        if (this instanceof Shark) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_requin.png"));

        } else if (this instanceof Whale) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_baleine.png"));

        } else if (this instanceof SeaSnake) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_serpent_de_mer.png"));

        } else if (this instanceof Boat) {
            icon = new ImageIcon(Pawn.class.getResource("/pion_bateau.png"));

        } else if (this instanceof Explorer) {
        	if(((Explorer)this).getColor() == Color.RED) {
                icon = new ImageIcon(Pawn.class.getResource("/pion_rouge.png"));
        	} else if(((Explorer)this).getColor() == Color.GREEN) {
                icon = new ImageIcon(Pawn.class.getResource("/pion_vert.png"));
        	} else if(((Explorer)this).getColor() == Color.BLUE) {
                icon = new ImageIcon(Pawn.class.getResource("/pion_bleu.png"));
        	} else if(((Explorer)this).getColor() == Color.YELLOW) {
                icon = new ImageIcon(Pawn.class.getResource("/pion_jaune.png"));
        	}
        }
        scaleImage = icon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);

       	//this.index = new JLabel(Integer.toString(index));

        icon.setImage(scaleImage);

        this.image.setIcon(icon);
        this.add(this.image);
        this.image.setBounds(0, 0, this.getWidth(), this.getHeight());
    }

}