package fr.mcstudio.pawns;

import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.HexagonListType;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.util.Triplet;
import fr.mcstudio.util.TripletList;

@SuppressWarnings("serial")
public class Pawn extends JLayeredPane {
    /**
     * <p>
     * Constructeur par d�faut.
     * </p>
     */
    public Pawn(int movePoint) {
    	this.setLayout(null);
    	this.setOpaque(false);
        this.movePoint = movePoint;
    }

    protected JLabel index;

    protected JLabel image = new JLabel();

	private int movePoint;

    public int getMovePoint() {
        return this.movePoint;
    }

    public void setMovePoint(int movePoint) {
        this.movePoint = movePoint;
    }

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
    public void findPath(Hexagon actualPosition, Board board, int movePointLeft, TripletList<Hexagon,Integer,HexagonListType> hexagonTripletList) {
        hexagonTripletList.clear();

        int distance = Math.min(movePointLeft, this.getMovePoint());

        List<Hexagon> tmp = new ArrayList<Hexagon>();
        tmp.add(actualPosition);
        hexagonTripletList.add(new Triplet<Hexagon, Integer, HexagonListType>(actualPosition, 1, HexagonListType.BOAT));
        for (int i = 1; i <= distance; i++) {
            for (Hexagon hexagon : tmp) {
                this.findPathAux(hexagon, board, hexagonTripletList, distance);
            }
            List<Hexagon> mem = new ArrayList<Hexagon>();
            List<Hexagon> hexagonList = hexagonTripletList.getLeftList();
            mem.addAll(tmp);
            Explorer explorer = (Explorer) this;

            tmp.clear();
            for (Hexagon hexagon : hexagonList) {
                int index = hexagonList.indexOf(hexagon);
                if ((this instanceof Explorer
                        && explorer.getStatus() == ExplorerStatus.SWIMMER
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

        if (this instanceof Explorer
                && actualPosition.getBoat() != null) {
            if (actualPosition.getBoat().getExplorerList().contains(this)) {
                if (!actualPosition.getWhaleList().isEmpty()
                        || !actualPosition.getSeaSnakeList().isEmpty()) {
                    hexagonTripletList.remove(0);
                    hexagonTripletList.add(new Triplet<Hexagon, Integer, HexagonListType>(actualPosition, 1, HexagonListType.DEATH));
                } else {
                    hexagonTripletList.remove(0);
                    hexagonTripletList.add(new Triplet<Hexagon, Integer, HexagonListType>(actualPosition, 1, HexagonListType.NORMAL));
                }
            } else {
                if (!actualPosition.getBoat().isFull()) {
                    if (!actualPosition.getWhaleList().isEmpty()
                            || !actualPosition.getSeaSnakeList().isEmpty()) {
                        hexagonTripletList.remove(0);
                        hexagonTripletList.add(new Triplet<Hexagon, Integer, HexagonListType>(actualPosition, 1, HexagonListType.DEATH));
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
     * 
     * @param actualPosition
     * @param board
     * @param listHexagon
     */
    public void findPathAux(Hexagon actualPosition, Board board, TripletList<Hexagon,Integer,HexagonListType> hexagonTripletList, int distance) {
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

	public void addIndex(int index, int size) {
		this.index = new JLabel(Integer.toString(index));
		this.index.setFont(new Font("Tahoma", Font.BOLD,  size/2));
		this.index.setForeground(java.awt.Color.WHITE);
		int indexWidth = this.index.getFontMetrics(this.index.getFont()).stringWidth(this.index.getText());
		int indexHeight = this.index.getFontMetrics(this.index.getFont()).getHeight();
		this.index.setBounds(getWidth()/2 - indexWidth/2, getHeight()/2 - indexHeight/4, size/2, size/2);
		this.setLayer(this.index, 2);
		add(this.index);
		
	}

	public JLabel getImage() {
		return image;
	}

	public void setImage(JLabel image) {
		this.image = image;
	}

}