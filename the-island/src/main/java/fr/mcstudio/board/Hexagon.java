package fr.mcstudio.board;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.game.Player;
import fr.mcstudio.pawns.Boat;
import fr.mcstudio.pawns.EffectPawn;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.Pawn;
import fr.mcstudio.pawns.SeaSnake;
import fr.mcstudio.pawns.Shark;
import fr.mcstudio.pawns.Whale;
import fr.mcstudio.tiles.Tile;

@SuppressWarnings("serial")
public class Hexagon extends JLayeredPane {

    /**
     * <p>
     * Constructeur par défaut
     * </p>
     */
    Hexagon hexagon = this;

    public Hexagon(JLayeredPane boardPane, final int line, final int column, int resolution) {
        super();
        this.line = line;
        this.column = column;
        this.resolution = resolution;

        this.setLayout(null);
        this.setBounds(this.returnPosX(resolution),
                this.returnPosY(resolution),
                resolution,
                resolution);

        boardPane.setLayer(this, 1);
        setOpaque(false);
        boardPane.add(this);
    }

    private int resolution;

    /**
     * <p>
     * Ligne de l'hexagone dans le board
     * </p>
     */
    private int line;

    /**
     * <p>
     * Colonne de l'hexagone dans le board
     * </p>
     */
    private int column;

    /**
     * <p>
     * tuile de l'hexagone dans le board
     * </p>
     */
    private Tile tile = null;

    /**
     * 
     */
    private HexagonType type;

    private boolean highlight;

    private JLabel highlightLabel = new JLabel();

    private String highlightColor = null;

    private List<Pawn> pawnsToDisplay = new ArrayList<Pawn>();

    /**
     * <p>
     * Liste des exploreurs presents sur l'hexagone dans le board
     * </p>
     */
    private List<Explorer> explorerList = new ArrayList<Explorer>();

    /**
     * <p>
     * Liste des requins presents sur l'hexagone dans le board
     * </p>
     */
    private List<Shark> sharkList = new ArrayList<Shark>();

    /**
     * <p>
     * Liste des baleines presents sur l'hexagone dans le board
     * </p>
     */
    private List<Whale> whaleList = new ArrayList<Whale>();

    /**
     * <p>
     * Liste des serpents de mer presents sur l'hexagone dans le board
     * </p>
     */
    private List<SeaSnake> seaSnakeList = new ArrayList<SeaSnake>();

    /**
     * <p>
     * 
     * </p>
     */
    private Boat boat = null;

    /**
     * <p>
     * 
     * </p>
     */
    public Tile getTile() {
        if (this.tile == null) {
            return null;
        } else {
            return this.tile;
        }

    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    /**
     * <p>
     * 
     * </p>
     */
    public void setType(HexagonType type) {
        this.type = type;
    }

    /**
     * <p>
     * 
     * </p>
     */
    public HexagonType getType() {
        return this.type;
    }

    /**
     * 
     */
    public boolean isTiles() {
        return this.type == HexagonType.TILES;
    }

    /**
     * 
     */
    public boolean isSea() {
        return this.type == HexagonType.SEA;
    }

    /**
     * 
     */
    public boolean isVoid() {
        return this.type == HexagonType.VOID;
    }

    /**
     * 
     */
    public boolean isIsland() {
        return this.type == HexagonType.ISLAND;
    }

    /**
     * <p>
     * Ajoute un explorateur à l'hexagone
     * </p>
     * 
     * @return
     */
    public void addPawn(Explorer e) {
        this.explorerList.add(e);
    }

    /**
     * <p>
     * Ajoute un requin à l'hexagone
     * </p>
     * 
     * @return
     */
    public void addPawn(Shark s) {
        this.sharkList.add(s);
    }

    /**
     * <p>
     * Ajoute une baleine à l'hexagone
     * </p>
     * 
     * @return
     */
    public void addPawn(Whale w) {
        this.whaleList.add(w);
    }

    /**
     * <p>
     * Ajoute un serpent de mer à l'hexagone
     * </p>
     * 
     * @return
     */
    public void addPawn(SeaSnake ss) {
        this.seaSnakeList.add(ss);
    }

    /**
     * <p>
     * Ajoute un bateau à l'hexagone
     * </p>
     * 
     * @return
     */
    public void addPawn(Boat b) {
        this.boat = b;
    }

    /**
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    public void addPawn(EffectPawn ef) {
        if (ef instanceof Shark) {
            this.sharkList.add((Shark) ef);
        } else if (ef instanceof Whale) {
            this.whaleList.add((Whale) ef);
        } else if (ef instanceof SeaSnake) {
            this.seaSnakeList.add((SeaSnake) ef);
        }
    }

    /**
     * <p>
     * Retire l'exploreur de l'hexagone
     * </p>
     * 
     * @return
     */
    public void removePawn(Explorer e) {
        this.explorerList.remove(e);
    }

    /**
     * <p>
     * Retire le requin de l'hexagone
     * </p>
     * 
     * @return
     */
    public void removePawn(Shark s) {
        this.sharkList.remove(s);
    }

    /**
     * <p>
     * Retire la baleine de l'hexagone
     * </p>
     * 
     * @return
     */
    public void removePawn(Whale w) {
        this.whaleList.remove(w);
    }

    /**
     * <p>
     * Retire le serpent de mer de l'hexagone
     * </p>
     * 
     * @return
     */
    public void removePawn(SeaSnake ss) {
        this.seaSnakeList.remove(ss);
    }

    /**
     * <p>
     * Retire le bateau de l'hexagone
     * </p>
     * 
     * @return
     */
    public void removePawn(Boat b) {
        this.boat = null;
    }

    /**
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    public void removePawn(EffectPawn ef) {
        if (ef instanceof Shark) {
            this.sharkList.remove((Shark) ef);
        } else if (ef instanceof Whale) {
            this.whaleList.remove((Whale) ef);
        } else if (ef instanceof SeaSnake) {
            this.seaSnakeList.remove((SeaSnake) ef);
        }
    }

    /**
     * <p>
     * Accesseur au(x) explorateur(s) dans l'hexagone
     * </p>
     */
    public List<Explorer> getExplorerList() {
        return this.explorerList;
    }

    /**
     * <p>
     * Accesseur au(x) requin(s) dans l'hexagone
     * </p>
     */
    public List<Shark> getSharkList() {
        return this.sharkList;
    }

    /**
     * <p>
     * Accesseur au(x) baleine(s) dans l'hexagone
     * </p>
     */
    public List<Whale> getWhaleList() {
        return this.whaleList;
    }

    /**
     * <p>
     * Accesseur au(x) serpent(s) de mer dans l'hexagone
     * </p>
     */
    public List<SeaSnake> getSeaSnakeList() {
        return this.seaSnakeList;
    }

    /**
     * <p>
     * Accesseur au bateau dans l'hexagone
     * </p>
     */
    public Boat getBoat() {
        return this.boat;
    }

    public boolean isInHexagonfloat(int resolution, float clickx, float clicky) {

        switch (resolution) {
            case 70:
                if (isInDemiPlan(35, 0, 0, 15, clickx, clicky) &&
                        isInDemiPlan(69, 15, 35, 0, clickx, clicky)
                        &&
                        isInDemiPlan(0, 54, 35, 69, clickx, clicky)
                        &&
                        isInDemiPlan(35, 69, 69, 54, clickx,
                                clicky)
                        &&
                        isInDemiPlan(0, 15, 0, 54, clickx, clicky) &&
                        isInDemiPlan(69, 54, 69, 15, clickx,
                                clicky)) {
                    return true;
                } else {
                    return false;
                }
            case 80:
                if (isInDemiPlan(40, 0, 0, 17, clickx, clicky) &&
                        isInDemiPlan(79, 17, 40, 0, clickx, clicky)
                        &&
                        isInDemiPlan(0, 62, 40, 79, clickx, clicky)
                        &&
                        isInDemiPlan(40, 79, 79, 62, clickx,
                                clicky)
                        &&
                        isInDemiPlan(0, 17, 0, 62, clickx, clicky) &&
                        isInDemiPlan(79, 62, 79, 17, clickx,
                                clicky)) {
                    return true;
                } else {
                    return false;
                }
            case 90:
                if (isInDemiPlan(45, 0, 0, 20, clickx, clicky) &&
                        isInDemiPlan(89, 20, 45, 0, clickx, clicky)
                        &&
                        isInDemiPlan(0, 69, 45, 89, clickx, clicky)
                        &&
                        isInDemiPlan(45, 89, 89, 69, clickx,
                                clicky)
                        &&
                        isInDemiPlan(0, 20, 0, 69, clickx, clicky) &&
                        isInDemiPlan(89, 69, 89, 20, clickx,
                                clicky)) {
                    return true;
                } else {
                    return false;
                }
            default:
                break;
        }
        return false;

    }

    /**
     * <p>
     * 
     * </p>
     * @since1.0
     */
    public boolean isInDemiPlan(float ax, float ay, float bx, float by, float clickx, float clicky) {
        float d = (bx - ax) * (clicky - ay) - (by - ay) * (clickx - ax);

        if (d <= 0)
            return true;
        else
            return false;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(int resolution, JLayeredPane boardPane, boolean highlight, String color) {
        this.highlight = highlight;

        remove(highlightLabel);
        revalidate();
        repaint();

        if (this.highlight) {
            // boardPane.remove(highlightLabel);
            ImageIcon icone = null;
            Image scaleImage;
            if (color == "white") {
                icone = new ImageIcon(Tile.class.getResource("/HexagonBlanc.png"));
            } else if (color == "yellow") {
                icone = new ImageIcon(Tile.class.getResource("/HexagonJaune.png"));
                highlightColor = "yellow";
            } else if (color == "red") {
                icone = new ImageIcon(Tile.class.getResource("/HexagonRouge.png"));
                highlightColor = "red";
            } else if (color == "purple") {
                icone = new ImageIcon(Tile.class.getResource("/HexagonViolet.png"));
                highlightColor = "purple";
            }
            switch (resolution) {
                case 70:
                    scaleImage = icone.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    icone.setImage(scaleImage);
                    break;
                case 80:
                    scaleImage = icone.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    icone.setImage(scaleImage);
                    break;
                default:
                    break;
            }

            highlightLabel.setBounds(0, 0, resolution, resolution);
            setLayer(highlightLabel, 4);
            this.highlightLabel.setIcon(icone);
            this.highlightLabel.setVisible(true);
            add(highlightLabel);
        } else {
            this.highlightLabel.setVisible(false);
            remove(highlightLabel);
        }
    }

    public void setHighlightColor(String highlightColor) {
        this.highlightColor = highlightColor;
    }

    public String getHighlightColor() {
        return highlightColor;
    }

    /**
     * <p>
     * Vide la case de tous ses pions
     * </p>
     * @since2.0
     */
    public void removeAllPawn() {
        if (!this.explorerList.isEmpty()) {
            for (Explorer explorer : explorerList) {
                explorer.setStatus(ExplorerStatus.DEAD);
            }
            this.explorerList.clear();
        }
        if (!this.seaSnakeList.isEmpty()) {
            this.seaSnakeList.clear();
        }
        if (!this.sharkList.isEmpty()) {
            this.sharkList.clear();
        }
        if (!this.whaleList.isEmpty()) {
            this.whaleList.clear();
        }
        this.boat = null;
    }

    /**
     * <p>
     * Affecte une tuile mer a un hexagone
     * </p>
     * @since2.0
     */
    public void removeTile() {
        this.setTile(null);
        this.setType(HexagonType.SEA);
    }

    /**
     * <p>
     * Renvois la pos x de la tuile
     * </p>
     * @since2.0
     */
    public int returnPosX(int resolution) {
        if (this.line % 2 == 0) {
            switch (resolution) {
                case 70:
                    return 92 + 70 * this.column;
                case 80:
                    return 105 + 80 * this.column;
                case 90:
                    return 120 + 90 * this.column;
                default:
                    break;
            }
        } else {
            switch (resolution) {
                case 70:
                    return 57 + 70 * this.column;
                case 80:
                    return 65 + 80 * this.column;
                case 90:
                    return 75 + 90 * this.column;
                default:
                    break;
            }
        }
        return 0;
    }

    /**
     * <p>
     * Renvois la pos y de la tuile
     * </p>
     * @since2.0
     */
    public int returnPosY(int resolution) {
        if (this.line % 2 == 0) {
            switch (resolution) {
                case 70:
                    return 26 + 54 * this.line;
                case 80:
                    return 29 + 62 * this.line;
                case 90:
                    return 31 + 70 * this.line;
                default:
                    break;
            }
        } else {
            switch (resolution) {
                case 70:
                    return 26 + 54 * this.line;
                case 80:
                    return 28 + 62 * this.line;
                case 90:
                    return 31 + 70 * this.line;
                default:
                    break;
            }
        }
        return 0;
    }

    public void displayPawns() {
        for (Pawn p : pawnsToDisplay) {
            remove(p);
        }
        revalidate();
        repaint();
        pawnsToDisplay.clear();
        // List<Pawn> pawnsToDisplay = new ArrayList<Pawn>();
        List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();
        List<Integer> index = new ArrayList<Integer>();
        int imageSize = 0;

        if (!this.sharkList.isEmpty()) {
            Shark s = new Shark();
            index.add(this.sharkList.size());
            pawnsToDisplay.add(s);
        }
        if (!this.whaleList.isEmpty()) {
            Whale w = new Whale();
            index.add(this.whaleList.size());
            pawnsToDisplay.add(w);
        }
        if (!this.seaSnakeList.isEmpty()) {
            SeaSnake ss = new SeaSnake();
            index.add(this.seaSnakeList.size());
            pawnsToDisplay.add(ss);
        }
        if (this.boat != null) {
            Boat b = new Boat();
            index.add(1);
            pawnsToDisplay.add(b);
        }
        if (!this.explorerList.isEmpty()) {
            if (containsExplorerColor(Color.GREEN)) {
                Explorer e = new Explorer(Color.GREEN, 0);
                index.add(nbExplorerColor(Color.GREEN));
                pawnsToDisplay.add(e);
            }
            if (containsExplorerColor(Color.RED)) {
                Explorer e = new Explorer(Color.RED, 0);
                index.add(nbExplorerColor(Color.RED));
                pawnsToDisplay.add(e);
            }
            if (containsExplorerColor(Color.BLUE)) {
                Explorer e = new Explorer(Color.BLUE, 0);
                index.add(nbExplorerColor(Color.BLUE));
                pawnsToDisplay.add(e);
            }
            if (containsExplorerColor(Color.YELLOW)) {
                Explorer e = new Explorer(Color.YELLOW, 0);
                index.add(nbExplorerColor(Color.YELLOW));
                pawnsToDisplay.add(e);
            }
        }
        float rate = ((float) resolution / (float) 90);
        switch (pawnsToDisplay.size()) {
            case 1:
                x.add((int) (11 * rate));
                y.add((int) (11 * rate));
                imageSize = 68;
                break;
            case 2:
                x.add((int) (1 * rate));
                y.add((int) (23 * rate));
                x.add((int) (46 * rate));
                y.add((int) (23 * rate));
                imageSize = 44;

                break;
            case 3:
                x.add((int) (27 * rate));
                x.add((int) (9 * rate));
                x.add((int) (45 * rate));
                y.add((int) (3 * rate));
                y.add((int) (39 * rate));
                y.add((int) (39 * rate));
                imageSize = 36;

                break;
            case 4:
                x.add((int) (15 * rate));
                x.add((int) (15 * rate));
                x.add((int) (49 * rate));
                x.add((int) (49 * rate));
                y.add((int) (15 * rate));
                y.add((int) (45 * rate));
                y.add((int) (15 * rate));
                y.add((int) (45 * rate));
                imageSize = 30;

                break;
            case 5:

                x.add((int) (30 * rate));
                x.add((int) (10 * rate));
                x.add((int) (50 * rate));
                x.add((int) (10 * rate));
                x.add((int) (50 * rate));
                y.add((int) (30 * rate));
                y.add((int) (10 * rate));
                y.add((int) (10 * rate));
                y.add((int) (50 * rate));
                y.add((int) (50 * rate));
                imageSize = 30;

                break;
            case 6:
                x.add((int) (20 * rate));
                x.add((int) (45 * rate));
                x.add((int) (10 * rate));
                x.add((int) (55 * rate));
                x.add((int) (20 * rate));
                x.add((int) (45 * rate));
                y.add((int) (5 * rate));
                y.add((int) (5 * rate));
                y.add((int) (30 * rate));
                y.add((int) (30 * rate));
                y.add((int) (55 * rate));
                y.add((int) (55 * rate));
                imageSize = 25;

                break;

            default:
                break;
        }
        for (int i = 0; i < pawnsToDisplay.size(); i++) {

            pawnsToDisplay.get(i).setPosition(x.get(i),
                    y.get(i), resolution, imageSize);
            pawnsToDisplay.get(i).createPawnImage();
            if (pawnsToDisplay.get(i) instanceof Boat)
                ((Boat) pawnsToDisplay.get(i)).displayBoatPawns(this.boat, resolution, pawnsToDisplay.size(), this);

            if (index.get(i) > 1)
                pawnsToDisplay.get(i).addIndex(index.get(i), imageSize);

            setLayer(pawnsToDisplay.get(i), 1);
            add(pawnsToDisplay.get(i));
        }
    }

    public boolean containsExplorerColor(final Color color) {
        return explorerList.stream().filter(o -> o.getColor().equals(color)).findFirst().isPresent();
    }

    public int nbExplorerColor(final Color color) {
        int nb = 0;
        for (Explorer e : explorerList) {
            if (e.getColor() == color) {
                nb++;
            }
        }
        return nb;
    }

    /*
     * public int getExplorerNbInList(final List<Explorer> list, final Color color){
     * return list.stream().filter(o ->
     * o.getColor().equals(color)).findFirst().isPresent();
     * }
     */

    public void discover(Player p, Board board) {
        if (this.tile != null) {
            this.tile.flipTile(this, p, board);
        } else {
            System.out.println("Aucune tuile sur la case choisie\n");
        }
    }
}
