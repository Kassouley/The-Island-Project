package fr.mcstudio.board;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.enums.TilesEffect;
import fr.mcstudio.enums.TilesType;
import fr.mcstudio.tiles.Tile;

@SuppressWarnings("serial")
public class Board extends JLayeredPane{
	private Hexagon[][] hexagons = new Hexagon[13][12];

	public Hexagon[][] getHexagons() {
		return hexagons;
	}

	JLabel boardLabel = new JLabel();

	Board board;

	public Board(final int resolution) {
		super();
		this.board = this;
		setLayer(boardLabel, 0);
		setPanelBoundsFromResolution(resolution);
		setLabel();
		add(boardLabel);

		List<Tile> tilesList = CreateTiles(resolution);
		Random r = new Random();
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 12; j++) {
				hexagons[i][j] = new Hexagon(this, i, j, resolution);
				if (((i != 0 || j != 0)
						&& (i != 1 || j != 0)
						&& (i != 3 || j != 0)
						&& (i != 9 || j != 0)
						&& (i != 11 || j != 0)
						&& (i != 12 || j != 0)
						&& (i != 0 || j != 0)
						&& (i != 0 || j != 1)
						&& (i != 12 || j != 1)
						&& (i != 0 || j != 9)
						&& (i != 12 || j != 9)
						&& (i != 0 || j != 10) 
						&& (i != 12 || j != 10)
						&& j != 11)
						|| (i == 5 && j == 11)
						|| (i == 7 && j == 11)) {

					if ((((i > 2 && j > 3 && i < 10 && j < 8)
							|| (i > 4 && j > 1 && i < 8 && j < 10
							&& (i != 6 || j != 9)))
							&& (i != 6 || j != 5))
							|| (i == 4 && j == 3)
							|| (i == 8 && j == 3)) {
						int n = r.nextInt(tilesList.size());
						hexagons[i][j].setTile(tilesList.get(n));
						tilesList.remove(n);
						hexagons[i][j].setType(HexagonType.TILES);
						hexagons[i][j].getTile().setBounds(0, 0, 
								resolution, resolution);
						hexagons[i][j].add(hexagons[i][j].getTile());

					} else {
						hexagons[i][j].setType(HexagonType.SEA);
					}

				} else if (i == 1 && j == 0 || i == 1 && j == 11 ||
						i == 11 && j == 0 || i == 11 && j == 11) {
					hexagons[i][j].setType(HexagonType.ISLAND);
				} else {
					hexagons[i][j].setType(HexagonType.VOID);
				}
			}
		}
	}

	private List<Tile> CreateTiles(int resolution) {
		List<Tile> tiles = new ArrayList<Tile>();

		for (int i = 0; i < 16; i++) {
			Tile tile = new Tile();
			tile.setType(resolution, TilesType.BEACH);
			if (i < 3) {
				tile.setEffect(TilesEffect.WHALE);
			} else if (i >= 3 && i < 6) {
				tile.setEffect(TilesEffect.SHARK);
			} else if (i >= 6 && i < 9) {
				tile.setEffect(TilesEffect.DOLPHIN_MOVE);
			} else if (i >= 9 && i < 11) {
				tile.setEffect(TilesEffect.BOAT_MOVE);
			} else if (i == 11) {
				tile.setEffect(TilesEffect.BOAT);
			} else if (i == 12) {
				tile.setEffect(TilesEffect.SEASNAKE_LOST);
			} else if (i == 13) {
				tile.setEffect(TilesEffect.SHARK_LOST);
			} else if (i == 14) {
				tile.setEffect(TilesEffect.WHALE_LOST);
			} else if (i == 15) {
				tile.setEffect(TilesEffect.SHARK_DEATH);
			}
			tiles.add(tile);
		}
		for (int i = 0; i < 16; i++) {
			Tile tile = new Tile();
			tile.setType(resolution, TilesType.FOREST);
			if (i < 2) {
				tile.setEffect(TilesEffect.WHALE);
			} else if (i >= 2 && i < 4) {
				tile.setEffect(TilesEffect.SHARK);
			} else if (i >= 4 && i < 7) {
				tile.setEffect(TilesEffect.BOAT);
			} else if (i >= 7 && i < 9) {
				tile.setEffect(TilesEffect.WHIRLPOOL);
			} else if (i >= 9 && i < 11) {
				tile.setEffect(TilesEffect.WHALE_DEATH);
			} else if (i == 11) {
				tile.setEffect(TilesEffect.DOLPHIN_MOVE);
			} else if (i == 12) {
				tile.setEffect(TilesEffect.SEASNAKE_LOST);
			} else if (i == 13) {
				tile.setEffect(TilesEffect.SHARK_LOST);
			} else if (i == 14) {
				tile.setEffect(TilesEffect.WHALE_LOST);
			} else if (i == 15) {
				tile.setEffect(TilesEffect.SHARK_DEATH);
			}
			tiles.add(tile);
		}
		for (int i = 0; i < 8; i++) {
			Tile tile = new Tile();
			tile.setType(resolution, TilesType.MONTAINS);
			if (i < 4) {
				tile.setEffect(TilesEffect.WHIRLPOOL);
			} else if (i == 4) {
				tile.setEffect(TilesEffect.SHARK);
			} else if (i == 5) {
				tile.setEffect(TilesEffect.VOLCANO);
			} else if (i == 6) {
				tile.setEffect(TilesEffect.SHARK_DEATH);
			} else if (i == 7) {
				tile.setEffect(TilesEffect.WHALE_DEATH);
			}
			tiles.add(tile);
		}
		return tiles;
	}

	private void setPanelBoundsFromResolution(int resolution) {
		
		switch (resolution) {
			case 70:
				setBounds(217, 0, 955, 770);
				break;
			case 80:
				setBounds(248, 0, 1090, 880);
				break;
			case 90:
				setBounds(282, 0, 1230, 990);
				break;
			default:
				break;
		}
	}
	
	private void setLabel() {
		ImageIcon icone = new ImageIcon(Board.class.getResource("/Map_90.png"));
		Image scaleImage = icone.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);;
		icone.setImage(scaleImage);
		boardLabel.setIcon(icone);
		boardLabel.setBounds(0, 0, getWidth(), getHeight());
	}

	public Hexagon getTopLeft(Hexagon actualHexagon) {
		if (actualHexagon.getLine() - 1 > 0 && actualHexagon.getLine() % 2 == 0) {
			return this.hexagons[actualHexagon.getLine() - 1][actualHexagon.getColumn()];
		} else if (actualHexagon.getLine() - 1 > 0
				&& actualHexagon.getColumn() - 1 > 0
				&& actualHexagon.getLine() % 2 == 1) {
			return this.hexagons[actualHexagon.getLine() - 1][actualHexagon.getColumn() - 1];
		} else {
			return null;
		}
	}

	public Hexagon getTopRight(Hexagon actualHexagon) {
		if (actualHexagon.getLine() - 1 > 0
				&& actualHexagon.getColumn() + 1 < 12
				&& actualHexagon.getLine() % 2 == 0) {
			return this.hexagons[actualHexagon.getLine() - 1][actualHexagon.getColumn() + 1];
		} else if (actualHexagon.getLine() - 1 > 0 && actualHexagon.getLine() % 2 == 1) {
			return this.hexagons[actualHexagon.getLine() - 1][actualHexagon.getColumn()];
		} else {
			return null;
		}
	}

	public Hexagon getLeft(Hexagon actualHexagon) {
		if (actualHexagon.getColumn() - 1 > 0) {
			return this.hexagons[actualHexagon.getLine()][actualHexagon.getColumn() - 1];
		} else {
			return null;
		}
	}

	public Hexagon getRight(Hexagon actualHexagon) {
		if (actualHexagon.getColumn() + 1 < 12) {
			return this.hexagons[actualHexagon.getLine()][actualHexagon.getColumn() + 1];
		} else {
			return null;
		}
	}

	public Hexagon getBottomLeft(Hexagon actualHexagon) {
		if (actualHexagon.getLine() + 1 < 13 && actualHexagon.getLine() % 2 == 0) {
			return this.hexagons[actualHexagon.getLine() + 1][actualHexagon.getColumn()];
		} else if (actualHexagon.getLine() + 1 < 13
				&& actualHexagon.getColumn() - 1 > 0
				&& actualHexagon.getLine() % 2 == 1) {
			return this.hexagons[actualHexagon.getLine() + 1][actualHexagon.getColumn() - 1];
		} else {
			return null;
		}
	}

	public Hexagon getBottomRight(Hexagon actualHexagon) {
		if (actualHexagon.getLine() + 1 < 13
				&& actualHexagon.getColumn() + 1 < 12
				&& actualHexagon.getLine() % 2 == 0) {
			return this.hexagons[actualHexagon.getLine() + 1][actualHexagon.getColumn() + 1];
		} else if (actualHexagon.getLine() + 1 < 12 && actualHexagon.getLine() % 2 == 1) {
			return this.hexagons[actualHexagon.getLine() + 1][actualHexagon.getColumn()];
		} else {
			return null;
		}
	}
}
