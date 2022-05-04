package fr.koraizon.theisland;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.koraizon.theisland.enums.TilesEffect;
import fr.koraizon.theisland.enums.TilesType;

public class Board {
	private Tiles[][] tiles = new Tiles[13][12];
	private int nbBeachTiles = 16;
	private int nbForestTiles = 16;
	private int nbMontainsTiles = 8;
	
	public Board(JLayeredPane plateauPane) {
		super();
		
		JLabel board = new JLabel("");
		plateauPane.setLayer(board, 0);
		board.setBounds(0, 0, 1230, 1000);
		board.setIcon(new ImageIcon(Board.class.getResource("/sprites/Map_90.png")));
		plateauPane.add(board);
		
		JPanel tilesPane = new JPanel();
		tilesPane.setOpaque(false);
		plateauPane.setLayer(tilesPane, 1);
		tilesPane.setBounds(0, 0, 1230, 1000);
		plateauPane.add(tilesPane);
		tilesPane.setLayout(null);
		tilesPane.setVisible(true);
		List<Tiles> tilesList = CreateTiles();
		Random r = new Random();
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 12; j++) {
				if(((i != 0 || j != 0) && (i != 1 || j != 0) && (i != 3 || j != 0) && 
					(i != 9 || j != 0) && (i != 11 || j != 0) && (i != 12 || j != 0) && 
					(i != 0 || j != 0) && (i != 0 || j != 1) && (i != 12 || j != 1) &&
					(i != 0 || j != 9) && (i != 12 || j != 9) &&
					(i != 0 || j != 10) && (i != 12 || j != 9)) || 
					(i == 5 && j == 11) || (i == 7 || j == 11)){
							
						if((((i > 2 && j > 3 && i < 10 && j < 8) || 
							(i > 4 && j > 1 && i < 8 && j < 10 && (i != 6 || j != 9))) && 
							(i != 6 || j != 5)) ||
							(i == 4 && j == 3) || (i == 8 && j == 3)) {
							int n = r.nextInt(tilesList.size());
							tiles[i][j] = tilesList.get(n);
							tilesList.remove(n);
						}
						else {
							tiles[i][j] = new Tiles();
						}

						if(i%2 == 0) {
							tiles[i][j].setPosition(120 + 90*j, 35 + 70 * i);
						} else {
							tiles[i][j].setPosition(75 + 90*j, 35 + 70 * i);
						}
						tilesPane.add(tiles[i][j]);
				} else {
					tiles[i][j] = null;
				}
				

			}
		}
	}	
	
	public List<Tiles> CreateTiles() {
		List<Tiles> tiles = new ArrayList<>();
		
		for (int i = 0; i < 16; i++) {
			Tiles tile = new Tiles();
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
			Tiles tile = new Tiles();
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
			Tiles tile = new Tiles();
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
