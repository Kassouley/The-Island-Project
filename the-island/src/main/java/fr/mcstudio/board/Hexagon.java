package fr.mcstudio.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.pawns.Boat;
import fr.mcstudio.pawns.EffectPawn;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.Pawn;
import fr.mcstudio.pawns.SeaSnake;
import fr.mcstudio.pawns.Shark;
import fr.mcstudio.pawns.Whale;
import fr.mcstudio.tiles.Tile;

public class Hexagon {

    /**
     * 
     */
    /**
     * <p>
     * Constructeur par défaut
     * </p>
     */
    Hexagon hexagon = this;

    public Hexagon(JLayeredPane boardPane, final int line, final int column) {
        this.line = line;
        this.column = column;

        highlightLabel.setIcon(new ImageIcon(Board.class.getResource("/HexagonBlanc.png")));
        boardPane.setLayer(highlightLabel, 3);
        boardPane.add(highlightLabel);

    }

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

    /**
     * <p>
     * Liste des exploreurs presents sur l'hexagone dans le board
     * </p>
     */
    private List<Explorer> explorerList = new ArrayList<Explorer>();

    /**
     * <p>
     * Liste des exploreurs bleus presents sur l'hexagone dans le board
     * </p>
     */
    private List<Explorer> blueExplorerList = new ArrayList<Explorer>();

    /**
     * <p>
     * Liste des exploreurs jaunes presents sur l'hexagone dans le board
     * </p>
     */
    private List<Explorer> yellowExplorerList = new ArrayList<Explorer>();

    /**
     * <p>
     * Liste des exploreurs rouges presents sur l'hexagone dans le board
     * </p>
     */
    private List<Explorer> redExplorerList = new ArrayList<Explorer>();

    /**
     * <p>
     * Liste des exploreurs verts presents sur l'hexagone dans le board
     * </p>
     */
    private List<Explorer> greenExplorerList = new ArrayList<Explorer>();
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
        if (tile == null) {
            return null;
        } else {
            return tile;
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
     * Accesseur au(x) explorateur(s) dans l'hexagone
     * </p>
     */
    public List<Explorer> getBlueExplorerList() {
        this.blueExplorerList.clear();
        for (Explorer e : this.explorerList) {
            if (e.getColor() == Color.BLUE) {
                this.blueExplorerList.add(e);
            }
        }
        return this.blueExplorerList;
    }

    /**
     * <p>
     * Accesseur au(x) explorateur(s) dans l'hexagone
     * </p>
     */
    public List<Explorer> getGreenExplorerList() {
        this.greenExplorerList.clear();
        for (Explorer e : this.explorerList) {
            if (e.getColor() == Color.GREEN) {
                this.greenExplorerList.add(e);
            }
        }
        return this.greenExplorerList;
    }

    /**
     * <p>
     * Accesseur au(x) explorateur(s) dans l'hexagone
     * </p>
     */
    public List<Explorer> getRedExplorerList() {
        this.redExplorerList.clear();
        for (Explorer e : this.explorerList) {
            if (e.getColor() == Color.RED) {
                this.redExplorerList.add(e);
            }
        }
        return this.redExplorerList;
    }

    /**
     * <p>
     * Accesseur au(x) explorateur(s) dans l'hexagone
     * </p>
     */
    public List<Explorer> getYellowExplorerList() {
        this.yellowExplorerList.clear();
        for (Explorer e : this.explorerList) {
            if (e.getColor() == Color.YELLOW) {
                this.yellowExplorerList.add(e);
            }
        }
        return this.yellowExplorerList;
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
                if (isInDemiPlan(tile.getX() + 35, tile.getY(), tile.getX(), tile.getY() + 15, clickx, clicky) &&
                        isInDemiPlan(tile.getX() + 69, tile.getY() + 15, tile.getX() + 35, tile.getY(), clickx, clicky)
                        &&
                        isInDemiPlan(tile.getX(), tile.getY() + 54, tile.getX() + 35, tile.getY() + 69, clickx, clicky)
                        &&
                        isInDemiPlan(tile.getX() + 35, tile.getY() + 69, tile.getX() + 69, tile.getY() + 54, clickx,
                                clicky)
                        &&
                        isInDemiPlan(tile.getX(), tile.getY() + 15, tile.getX(), tile.getY() + 54, clickx, clicky) &&
                        isInDemiPlan(tile.getX() + 69, tile.getY() + 54, tile.getX() + 69, tile.getY() + 15, clickx,
                                clicky)) {
                    return true;
                } else {
                    return false;
                }
            case 80:
                if (isInDemiPlan(tile.getX() + 40, tile.getY(), tile.getX(), tile.getY() + 17, clickx, clicky) &&
                        isInDemiPlan(tile.getX() + 79, tile.getY() + 17, tile.getX() + 40, tile.getY(), clickx, clicky)
                        &&
                        isInDemiPlan(tile.getX(), tile.getY() + 62, tile.getX() + 40, tile.getY() + 79, clickx, clicky)
                        &&
                        isInDemiPlan(tile.getX() + 40, tile.getY() + 79, tile.getX() + 79, tile.getY() + 62, clickx,
                                clicky)
                        &&
                        isInDemiPlan(tile.getX(), tile.getY() + 17, tile.getX(), tile.getY() + 62, clickx, clicky) &&
                        isInDemiPlan(tile.getX() + 79, tile.getY() + 62, tile.getX() + 79, tile.getY() + 17, clickx,
                                clicky)) {
                    return true;
                } else {
                    return false;
                }
            case 90:
                if (isInDemiPlan(tile.getX() + 45, tile.getY(), tile.getX(), tile.getY() + 20, clickx, clicky) &&
                        isInDemiPlan(tile.getX() + 89, tile.getY() + 20, tile.getX() + 45, tile.getY(), clickx, clicky)
                        &&
                        isInDemiPlan(tile.getX(), tile.getY() + 69, tile.getX() + 45, tile.getY() + 89, clickx, clicky)
                        &&
                        isInDemiPlan(tile.getX() + 45, tile.getY() + 89, tile.getX() + 89, tile.getY() + 69, clickx,
                                clicky)
                        &&
                        isInDemiPlan(tile.getX(), tile.getY() + 20, tile.getX(), tile.getY() + 69, clickx, clicky) &&
                        isInDemiPlan(tile.getX() + 89, tile.getY() + 69, tile.getX() + 89, tile.getY() + 20, clickx,
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

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
        if (highlight) {
            this.highlightLabel.setBounds(tile.getX(), tile.getY(), 90, 90);

            this.highlightLabel.setVisible(true);
        } else {

            this.highlightLabel.setVisible(false);
        }
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
        this.setTile(new Tile());
        this.setType(HexagonType.SEA);

    }

    /**
     * <p>
     * Renvois la pos x de la tuile
     * </p>
     * @since2.0
     */
    public int returnPosTileX() {
        if (this.line % 2 == 0) {
            return 120 + 90 * this.column;
        } else {
            return 75 + 90 * this.column;
        }
    }

    /**
     * <p>
     * Renvois la pos y de la tuile
     * </p>
     * @since2.0
     */
    public int returnPosTileY() {
        if (this.line % 2 == 0) {
            return 35 + 70 * this.line;
        } else {
            return 35 + 70 * this.line;
        }
    }

    public void displayPawns(JPanel pawnPane) {
        List<Pawn> pawnsToDisplay = new ArrayList<Pawn>();
        List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();

        if (!this.sharkList.isEmpty()) {
            Shark s = new Shark();
            s.setImage();
            if (this.sharkList.size() > 1) {
                JLabel textDisplay = new JLabel(Integer.toString(
                        this.sharkList.size()));
            }
            pawnsToDisplay.add(s);

        }
        if (!this.whaleList.isEmpty()) {
            Whale w = new Whale();
            w.setImage();
            if (this.whaleList.size() > 1) {
                JLabel textDisplay = new JLabel(Integer.toString(
                        this.whaleList.size()));
            }
            pawnsToDisplay.add(w);

        }
        if (!this.seaSnakeList.isEmpty()) {
            SeaSnake ss = new SeaSnake();
            ss.setImage();
            if (this.seaSnakeList.size() > 1) {
                JLabel textDisplay = new JLabel(Integer.toString(
                        this.seaSnakeList.size()));
            }
            pawnsToDisplay.add(ss);

        }
        if (this.boat != null) {
            this.boat.setImage();
            pawnsToDisplay.add(this.boat);
        }
        if (!this.explorerList.isEmpty()) {
            boolean yellowSeen = false;
            boolean redSeen = false;
            boolean blueSeen = false;
            boolean greenSeen = false;

            for (Explorer e : this.explorerList) {
                if (!greenSeen && e.getColor() == Color.GREEN) {
                    greenSeen = true;
                    pawnsToDisplay.add(e);

                } else if (!redSeen && e.getColor() == Color.RED) {
                    redSeen = true;
                    pawnsToDisplay.add(e);

                } else if (!blueSeen && e.getColor() == Color.BLUE) {
                    blueSeen = true;
                    pawnsToDisplay.add(e);

                } else if (!yellowSeen && e.getColor() == Color.YELLOW) {
                    yellowSeen = true;
                    pawnsToDisplay.add(e);
                }

                if (yellowSeen && blueSeen && redSeen && greenSeen) {
                    break;
                }
            }
        }

        switch (pawnsToDisplay.size()) {
            case 1:
                x.add(11);
                y.add(11);
                break;
            case 2:
                x.add(11);
                x.add(5);
                y.add(11);
                y.add(39);

                break;
            case 3:
                x.add(11);
                x.add(5);
                x.add(5);
                y.add(11);
                y.add(39);
                y.add(39);

                break;
            case 4:
                x.add(11);
                x.add(5);
                x.add(5);
                x.add(5);
                y.add(11);
                y.add(11);
                y.add(39);
                y.add(39);

                break;
            case 5:
                x.add(11);
                x.add(5);
                x.add(5);
                x.add(5);
                x.add(5);
                y.add(11);
                y.add(11);
                y.add(39);
                y.add(39);
                y.add(39);

                break;
            case 6:
                x.add(11);
                x.add(5);
                x.add(5);
                x.add(5);
                x.add(5);
                x.add(5);
                y.add(11);
                y.add(11);
                y.add(39);
                y.add(39);
                y.add(39);
                y.add(39);

                break;

            default:
                break;
        }

        for (int i = 0; i < pawnsToDisplay.size(); i++) {
            pawnsToDisplay.get(i).setPosition(x.get(i), y.get(i));
            pawnPane.add(pawnsToDisplay.get(i));
        }

    }
}