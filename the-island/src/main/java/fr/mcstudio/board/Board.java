package fr.mcstudio.board;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	
	public Board(int resolution, JLayeredPane boardPane) {
		super();
		this.boardPane = boardPane;
		boardPane.setLayer(this, 0);
		setBoundsFromResolution(resolution);
		boardPane.add(this);

        
		
		JPanel tilesPane = new JPanel();
		tilesPane.setOpaque(false);
		boardPane.setLayer(tilesPane, 1);
		switch(resolution) {
		case 70:
			tilesPane.setBounds(0, 0, 955, 770);
			break;
		case 80:
			tilesPane.setBounds(0, 0, 1090, 880);
			break;
		case 90:
			tilesPane.setBounds(0, 0, 1230, 990);
			break;
		default:
			break;
		}
		boardPane.add(tilesPane);
		tilesPane.setLayout(null);
		tilesPane.setVisible(true);
		List<Tile> tilesList = CreateTiles(resolution);
		Random r = new Random();
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 12; j++) {
				hexagons[i][j] = new Hexagon(boardPane, i, j);
				if(((i != 0 || j != 0) 
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
						&& (i != 0 || j != 10)) 
						|| (i == 5 && j == 11) 
						|| (i == 7 || j == 11)){
					
					if((((i > 2 && j > 3 && i < 10 && j < 8) 
							|| (i > 4 && j > 1 && i < 8 && j < 10 
							&& (i != 6 || j != 9))) 
							&& (i != 6 || j != 5)) 
							|| (i == 4 && j == 3) 
							|| (i == 8 && j == 3)) {
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
					switch(resolution) {
					case 70:
						positionx = 82 + 70*j;
						positiony = 16 + 54 * i;
						break;
					case 80:
						positionx = 100 + 80*j;
						positiony = 24 + 62 * i;
						break;
					case 90:
						positionx = 120 + 90 * j;
						positiony = 31 + 70 * i;
						break;
					default:
						break;
					}
				} else {
					switch(resolution) {
					case 70:
						positionx = 47 + 70*j;
						positiony = 16 + 54 * i;
						break;
					case 80:
						positionx = 60 + 80*j;
						positiony = 24 + 62 * i;
						break;
					case 90:
						positionx = 75 + 90*j;
						positiony = 31 + 70 * i;
						break;
					default:
						break;
					}
				}
				if(hexagons[i][j].getTile() != null) {
					tilesPane.add(hexagons[i][j].getTile());
					hexagons[i][j].getTile().setPosition(positionx, positiony);
				}
			}
		}
		

		//plateauPane.addMouseListener((e) -> getClickedPosition(e));
		
		boardPane.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				for(int i = 0; i < 13; i ++) {
					for (int j = 0; j < 12; j++) {
						if(hexagons[i][j].getTile() != null)
							if(hexagons[i][j].isInHexagonfloat(resolution, e.getX(), e.getY())) {
								System.out.println("Yay ! " + hexagons[i][j].getLigne() + " " + hexagons[i][j].getColonne());
								//tile.applyEffect(hexagon);
							}
					}
				}
				
			}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}

			public void mouseEntered(MouseEvent e) {}

			public void mouseExited(MouseEvent e) {}
        });
		
	}	
	
	private  List<Tile> CreateTiles(int resolution) {
		List<Tile> tiles = new ArrayList<Tile>();
		
		for (int i = 0; i < 16; i++) {
			Tile tile = new Tile();
			tile.setType(resolution, TilesType.BEACH);
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
			tile.setType(resolution, TilesType.FOREST);
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
			tile.setType(resolution, TilesType.MONTAINS);
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
	

	private void setBoundsFromResolution(int resolution) {
		ImageIcon icone = new ImageIcon(Board.class.getResource("/Map_90.png"));
		Image scaleImage;
		switch(resolution) {
		case 70:
			this.setBounds(0, 0, 955, 770);
			scaleImage = icone.getImage().getScaledInstance(955, 770,Image.SCALE_SMOOTH);
			icone.setImage(scaleImage);
			break;
		case 80:
			this.setBounds(0, 0, 1090, 880);
			scaleImage = icone.getImage().getScaledInstance(1090, 880,Image.SCALE_SMOOTH);
			icone.setImage(scaleImage);
			break;
		case 90:
			this.setBounds(0, 0, 1230, 990);
			scaleImage = icone.getImage().getScaledInstance(1230, 990,Image.SCALE_SMOOTH);
			icone.setImage(scaleImage);
			break;
		default: break;
		}

	    this.setIcon(icone);
	}
}
