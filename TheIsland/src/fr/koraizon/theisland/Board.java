package fr.koraizon.theisland;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.koraizon.theisland.enums.Color;
import fr.koraizon.theisland.enums.HexagonType;
import fr.koraizon.theisland.enums.TilesEffect;
import fr.koraizon.theisland.enums.TilesType;

@SuppressWarnings("serial")
public class Board extends JLabel{
	private Hexagon[][] hexagons = new Hexagon[13][13];
	
	public Board(JLayeredPane plateauPane) {
		super();
		
		plateauPane.setLayer(this, 0);
		this.setBounds(0, 0, 1230, 1000);
		this.setIcon(new ImageIcon(Board.class.getResource("/sprites/Map_90.png")));
		plateauPane.add(this);
		
		JPanel tilesPane = new JPanel();
		tilesPane.setOpaque(false);
		plateauPane.setLayer(tilesPane, 1);
		tilesPane.setBounds(0, 0, 1230, 1000);
		plateauPane.add(tilesPane);
		tilesPane.setLayout(null);
		tilesPane.setVisible(true);
		List<Tile> tilesList = CreateTiles();
		Random r = new Random();
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 12; j++) {
				hexagons[i][j] = new Hexagon(plateauPane);
				if(((i != 0 || j != 1) && (i != 1 || j != 1) && (i != 3 || j != 1) && 
						(i != 9 || j != 1) && (i != 11 || j != 1) && (i != 12 || j != 1) && 
						(i != 0 || j != 1) && (i != 0 || j != 2) && (i != 12 || j != 2) &&
						(i != 0 || j != 10) && (i != 12 || j != 10) &&
						(i != 0 || j != 11)) || 
						(i == 5 && j == 12) || (i == 7 || j == 12)){
					
					if((((i > 2 && j > 4 && i < 10 && j < 9) || 
						(i > 4 && j > 2 && i < 8 && j < 11 && (i != 6 || j != 10))) && 
						(i != 6 || j != 6)) ||
						(i == 4 && j == 4) || (i == 8 && j == 4)) {
						int n = r.nextInt(tilesList.size());
						hexagons[i][j].setTile(tilesList.get(n));
						tilesList.remove(n);
						hexagons[i][j].setType(HexagonType.TILES);
					}
					else {
						hexagons[i][j].setTile(new Tile());
						hexagons[i][j].setType(HexagonType.SEA);
					}

					if(i%2 == 0) {
						int positionx = 30 + 90 * j;
						int positiony = 35 + 70 * i;
						hexagons[i][j].setPosition(positionx, positiony);
						hexagons[i][j].getTile().setPosition(positionx, positiony);
					} else {
						int positionx = -15 + 90*j;
						int positiony = 35 + 70 * i;
						hexagons[i][j].setPosition(positionx, positiony);
						hexagons[i][j].getTile().setPosition(positionx, positiony);
					}
					tilesPane.add(hexagons[i][j].getTile());
				} else if(i == 0 && j == 1 || i == 1 && j == 1 || i == 2 && j == 0 ||
						i == 0 && j == 11 || i == 1 && j == 12 || i == 2 && j == 12 ||
						i == 10 && j == 0 || i == 11 && j == 1 || i == 12 && j == 1 ||
						i == 12 && j == 11 || i == 11 && j == 12 || i == 10 && j == 12 ) {
					hexagons[i][j].setTile(null);
					hexagons[i][j].setType(HexagonType.ISLAND);
				} else {
					hexagons[i][j].setTile(null);
					hexagons[i][j].setType(HexagonType.VOID);
				}
				

			}
		}
		hexagons[7][7].addPawn(new Explorer(Color.BLUE, 1));
		hexagons[7][7].addPawn(new Explorer(Color.BLUE, 1));
		hexagons[7][7].addPawn(new Explorer(Color.BLUE, 1));
		hexagons[7][7].addPawn(new Explorer(Color.BLUE, 1));
		
	}	
	
	public List<Tile> CreateTiles() {
		List<Tile> tiles = new ArrayList<>();
		
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
}
