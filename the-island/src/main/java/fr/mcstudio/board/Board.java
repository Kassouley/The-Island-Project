package fr.mcstudio.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.enums.TilesEffect;
import fr.mcstudio.enums.TilesType;
import fr.mcstudio.tiles.Tile;

@SuppressWarnings("serial")
public class Board extends JLabel{
	private Hexagon[][] hexagons = new Hexagon[13][12];
	
	JLayeredPane boardPane;
	
	public Board(JLayeredPane boardPane) {
		super();
		this.boardPane = boardPane;
		boardPane.setLayer(this, 0);
		this.setBounds(0, 0, 1230, 1000);
		this.setIcon(new ImageIcon(Board.class.getResource("/Map_90.png")));
		boardPane.add(this);
		
		JPanel tilesPane = new JPanel();
		tilesPane.setOpaque(false);
		boardPane.setLayer(tilesPane, 1);
		tilesPane.setBounds(0, 0, 1230, 1000);
		boardPane.add(tilesPane);
		tilesPane.setLayout(null);
		tilesPane.setVisible(true);
		List<Tile> tilesList = CreateTiles();
		Random r = new Random();
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 12; j++) {
				hexagons[i][j] = new Hexagon(boardPane, i, j);
				if(((i != 0 || j != 0) && (i != 1 || j != 0) && (i != 3 || j != 0) && 
						(i != 9 || j != 0) && (i != 11 || j != 0) && (i != 12 || j != 0) && 
						(i != 0 || j != 0) && (i != 0 || j != 1) && (i != 12 || j != 1) &&
						(i != 0 || j != 9) && (i != 12 || j != 9) &&
						(i != 0 || j != 10)) || 
						(i == 5 && j == 11) || (i == 7 || j == 11)){
					
					if((((i > 2 && j > 3 && i < 10 && j < 8) || 
						(i > 4 && j > 1 && i < 8 && j < 10 && (i != 6 || j != 9))) && 
						(i != 6 || j != 5)) ||
						(i == 4 && j == 3) || (i == 8 && j == 3)) {
						int n = r.nextInt(tilesList.size());
						hexagons[i][j].setTile(tilesList.get(n));
						tilesList.remove(n);
						hexagons[i][j].setType(HexagonType.TILES);
					}
					else {
						hexagons[i][j].setTile(new Tile());
						hexagons[i][j].setType(HexagonType.SEA);
					}

					
				} else if (i == 1 && j == 0 || i == 1 && j == 11 ||
						i == 11 && j == 0 ||  i == 11 && j == 11) {
					hexagons[i][j].setTile(new Tile());
					hexagons[i][j].setType(HexagonType.ISLAND);
				} else {
					hexagons[i][j].setTile(null);
					hexagons[i][j].setType(HexagonType.VOID);
				}
				int positionx = 0;
				int positiony = 0;
				if(i%2 == 0) {
					positionx = 120 + 90 * j;
					positiony = 35 + 70 * i;
				} else {
					positionx = 75 + 90*j;
					positiony = 35 + 70 * i;
				}
				if(hexagons[i][j].getTile() != null) {
					tilesPane.add(hexagons[i][j].getTile());
					hexagons[i][j].getTile().setPosition(positionx, positiony);
				}
			}
		}
		

		System.out.println(boardPane.getX() + "," + boardPane.getY());
		//plateauPane.addMouseListener((e) -> getClickedPosition(e));
		
		
	}	
	
	private  List<Tile> CreateTiles() {
		List<Tile> tiles = new ArrayList<Tile>();
		
		for (int i = 0; i < 16; i++) {
			Tile tile = new Tile();
			tile.setType(TilesType.BEACH);
			if(i < 3) {
				tile.setEffect(TilesEffect.WHALE);
			} else if(i >= 3 && i < 6) {
				tile.setEffect(TilesEffect.SHARK);
			} else if(i >= 6 && i < 9) {
				tile.setEffect(TilesEffect.DOLPHIN_MOVE);
			} else if(i >= 9 && i < 11) {
				tile.setEffect(TilesEffect.BOAT_MOVE);
			} else if(i == 11) {
				tile.setEffect(TilesEffect.BOAT);
			} else if(i == 12) {
				tile.setEffect(TilesEffect.SEASNAKE_LOST);
			} else if(i == 13) {
				tile.setEffect(TilesEffect.SHARK_LOST);
			} else if(i == 14) {
				tile.setEffect(TilesEffect.WHALE_LOST);
			} else if(i == 15) {
				tile.setEffect(TilesEffect.SHARK_DEATH);
			}
			tiles.add(tile);
		}
		for (int i = 0; i < 16; i++) {
			Tile tile = new Tile();
			tile.setType(TilesType.FOREST);
			if(i < 2) {
				tile.setEffect(TilesEffect.WHALE);
			} else if(i >= 2 && i < 4) {
				tile.setEffect(TilesEffect.SHARK);
			} else if(i >= 4 && i < 7) {
				tile.setEffect(TilesEffect.BOAT);
			} else if(i >= 7 && i < 9) {
				tile.setEffect(TilesEffect.WHIRLPOOL);
			} else if(i >= 9 && i < 11) {
				tile.setEffect(TilesEffect.WHALE_DEATH);
			} else if(i == 11) {
				tile.setEffect(TilesEffect.DOLPHIN_MOVE);
			} else if(i == 12) {
				tile.setEffect(TilesEffect.SEASNAKE_LOST);
			} else if(i == 13) {
				tile.setEffect(TilesEffect.SHARK_LOST);
			} else if(i == 14) {
				tile.setEffect(TilesEffect.WHALE_LOST);
			} else if(i == 15) {
				tile.setEffect(TilesEffect.SHARK_DEATH);
			}
			tiles.add(tile);
		}
		for (int i = 0; i < 8; i++) {
			Tile tile = new Tile();
			tile.setType(TilesType.MONTAINS);
			if(i < 4) {
				tile.setEffect(TilesEffect.WHIRLPOOL);
			} else if(i == 4) {
				tile.setEffect(TilesEffect.SHARK);
			} else if(i == 5) {
				tile.setEffect(TilesEffect.VOLCANO);
			} else if(i == 6) {
				tile.setEffect(TilesEffect.SHARK_DEATH);
			} else if(i == 7) {
				tile.setEffect(TilesEffect.WHALE_DEATH);
			}
			tiles.add(tile);
		}
		return tiles;
	}

	public Hexagon getTopLeft(Hexagon actualHexagon) {
        if (actualHexagon.getLine()-1 > 0 && actualHexagon.getLine()%2 == 1) {
            return this.hexagons[actualHexagon.getLine()-1][actualHexagon.getColomn()];
        } else if (actualHexagon.getLine()-1 > 0 
				&& actualHexagon.getColomn()-1 > 0 
				&& actualHexagon.getLine()%2 == 0) {
            return this.hexagons[actualHexagon.getLine()-1][actualHexagon.getColomn()-1];
        } else {
            return null;
        }
    }

    public Hexagon getTopRight(Hexagon actualHexagon) {
        if (actualHexagon.getLine()-1 > 0 
				&& actualHexagon.getColomn()+1 < 12 
				&& actualHexagon.getLine()%2 == 1) {
            return this.hexagons[actualHexagon.getLine()-1][actualHexagon.getColomn()+1];
        } else if (actualHexagon.getLine()-1 > 0 && actualHexagon.getLine()%2 == 0) {
            return this.hexagons[actualHexagon.getLine()-1][actualHexagon.getColomn()];
        } else {
            return null;
        }
    }

    public Hexagon getLeft(Hexagon actualHexagon) {
        if (actualHexagon.getColomn()-1 > 0) {
            return this.hexagons[actualHexagon.getLine()][actualHexagon.getColomn()-1];
        } else {
            return null;
        }
    }

    public Hexagon getRight(Hexagon actualHexagon) {
        if (actualHexagon.getColomn()+1 < 12) {
            return this.hexagons[actualHexagon.getLine()][actualHexagon.getColomn()+1];
        } else {
            return null;
        }
    }

    public Hexagon getBottomLeft(Hexagon actualHexagon) {
        if (actualHexagon.getLine()+1 < 13 && actualHexagon.getLine()%2 == 1) {
            return this.hexagons[actualHexagon.getLine()+1][actualHexagon.getColomn()];
        } else if (actualHexagon.getLine()+1 < 13 
				&& actualHexagon.getColomn()-1 > 0 
				&& actualHexagon.getLine()%2 == 0) {
            return this.hexagons[actualHexagon.getLine()+1][actualHexagon.getColomn()-1];
        } else {
            return null;
        }
    }

    public Hexagon getBottomRight(Hexagon actualHexagon) {
        if (actualHexagon.getLine()+1 < 13 
				&& actualHexagon.getColomn()+1 < 12 
				&& actualHexagon.getLine()%2 == 1) {
			return this.hexagons[actualHexagon.getLine()+1][actualHexagon.getColomn()+1];
        } else if (actualHexagon.getLine()+1 < 12 && actualHexagon.getLine()%2 == 0) {
            return this.hexagons[actualHexagon.getLine()+1][actualHexagon.getColomn()];
        } else {
            return null;
        }
    }
}
