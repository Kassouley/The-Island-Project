package fr.mcstudio.board;

import java.awt.Image;
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
import fr.mcstudio.enums.TilesType;
import fr.mcstudio.pawns.Boat;
import fr.mcstudio.pawns.EffectPawn;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.Pawn;
import fr.mcstudio.pawns.SeaSnake;
import fr.mcstudio.pawns.Shark;
import fr.mcstudio.pawns.Whale;
import fr.mcstudio.tiles.Tile;

@SuppressWarnings("serial")
public class Hexagon extends JPanel {

    /**
     * 
     */
    /**
     * <p>
     * Constructeur par défaut
     * </p>
     */
    Hexagon hexagon = this;

    public Hexagon(JLayeredPane boardPane, final int line, final int column, int resolution) {
        super();
    	this.setLayout(null);
        this.setBounds(this.returnPosTileX(resolution),
                this.returnPosTileY(resolution),
                resolution,
                resolution);

        this.line = line;
        this.column = column;
        this.resolution = resolution;

        highlightLabel.setIcon(new ImageIcon(Board.class.getResource("/HexagonBlanc.png")));
        boardPane.setLayer(highlightLabel, 3);
        boardPane.add(highlightLabel);

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

    public void setHighlight(int resolution, JLayeredPane boardPane, boolean highlight, String color) {
        this.highlight = highlight;
        if (highlight) {
            // boardPane.remove(highlightLabel);
            ImageIcon icone = null;
            Image scaleImage;
            if (color == "white") {
                icone = new ImageIcon(Tile.class.getResource("/HexagonBlanc.png"));
            } else if (color == "yellow") {
                icone = new ImageIcon(Tile.class.getResource("/HexagonJaune.png"));
            } else if (color == "red") {
                icone = new ImageIcon(Tile.class.getResource("/HexagonRouge.png"));
            }
            switch (resolution) {
                case 70:
                    scaleImage = icone.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    highlightLabel.setBounds(tile.getX(), tile.getY(), 70, 70);
                    icone.setImage(scaleImage);
                    break;
                case 80:
                    scaleImage = icone.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    highlightLabel.setBounds(tile.getX(), tile.getY(), 80, 80);
                    icone.setImage(scaleImage);
                    break;
                case 90:
                    highlightLabel.setBounds(tile.getX(), tile.getY(), 90, 90);
                    break;
                default:
                    break;
            }
            boardPane.setLayer(highlightLabel, 4);
            this.highlightLabel.setIcon(icone);
            this.highlightLabel.setVisible(true);
            boardPane.add(highlightLabel);
        } else {
            this.highlightLabel.setVisible(false);
            boardPane.remove(highlightLabel);
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
    public int returnPosTileX(int resolution) {
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
    public int returnPosTileY(int resolution) {
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

    public void displayPawns(JPanel pawnPane) {
        List<Pawn> pawnsToDisplay = new ArrayList<Pawn>();
        List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();
        int imageSize = 0;

        if (!this.sharkList.isEmpty()) {
            Shark s = new Shark();
            /*s.createPawnImage(this.resolution);
            if (this.sharkList.size() > 1) {
                JLabel textDisplay = new JLabel(Integer.toString(
                        this.sharkList.size()));
            }*/
            pawnsToDisplay.add(s);
        }
        if (!this.whaleList.isEmpty()) {
            Whale w = new Whale();
            /*w.createPawnImage(this.resolution);
            if (this.whaleList.size() > 1) {
                JLabel textDisplay = new JLabel(Integer.toString(
                        this.whaleList.size()));
            }*/
            pawnsToDisplay.add(w);
        }
        if (!this.seaSnakeList.isEmpty()) {
            SeaSnake ss = new SeaSnake();
            /*ss.createPawnImage(this.resolution);
            if (this.seaSnakeList.size() > 1) {
                JLabel textDisplay = new JLabel(Integer.toString(
                        this.seaSnakeList.size()));
            }*/
            pawnsToDisplay.add(ss);
        }
        if (this.boat != null) {
        	this.boat = new Boat();
            //this.boat.createPawnImage(this.resolution);
            pawnsToDisplay.add(this.boat);
        }
        if (!this.explorerList.isEmpty()) {
        	
        	if(containsExplorerColor(explorerList, Color.GREEN)) {
        		Explorer e = new Explorer(Color.GREEN, 0);
                pawnsToDisplay.add(e);
        	}
        	if(containsExplorerColor(explorerList, Color.RED)) {
        		Explorer e = new Explorer(Color.RED, 0);
                pawnsToDisplay.add(e);
        	}
        	if(containsExplorerColor(explorerList, Color.BLUE)) {
        		Explorer e = new Explorer(Color.BLUE, 0);
                pawnsToDisplay.add(e);
        	}
        	if(containsExplorerColor(explorerList, Color.YELLOW)) {
        		Explorer e = new Explorer(Color.YELLOW, 0);
                pawnsToDisplay.add(e);
        	}
            /*boolean yellowSeen = false;
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
            }*/
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
            pawnsToDisplay.get(i).setPosition(x.get(i) + this.returnPosTileX(this.resolution),
                    y.get(i) + this.returnPosTileY(this.resolution), resolution, imageSize);
            pawnsToDisplay.get(i).createPawnImage(this);
            pawnPane.add(pawnsToDisplay.get(i));
        }

    }
    
    public boolean containsExplorerColor(final List<Explorer> list, final Color color){
        return list.stream().filter(o -> o.getColor().equals(color)).findFirst().isPresent();
    }
    
    /*public int getExplorerNbInList(final List<Explorer> list, final Color color){
        return list.stream().filter(o -> o.getColor().equals(color)).findFirst().isPresent();
    }*/
}
