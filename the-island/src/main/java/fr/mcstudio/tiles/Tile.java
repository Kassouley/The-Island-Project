
package fr.mcstudio.tiles;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.enums.AnimationType;
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.ExternalPanelState;
import fr.mcstudio.enums.TilesEffect;
import fr.mcstudio.enums.TilesType;
import fr.mcstudio.game.Player;
import fr.mcstudio.pawns.Boat;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.Shark;
import fr.mcstudio.pawns.Whale;

/**
 * 
 */
@SuppressWarnings("serial")
public class Tile extends JLayeredPane {

	private TilesType type = null;
	private TilesEffect effect = null;
	private int resolution;
	private JLabel typeLabel = new JLabel();
	private JLabel effectLabel = new JLabel();

	/**
	 * Default constructor
	 */
	public Tile(int resolution) {
		this.resolution = resolution;
		this.typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}

	/**
	* 
	*/
	public TilesType getType() {
			return this.type;
	}

	/**
	* 
	*/
	public void setType(TilesType type) {
		ImageIcon icone = null;
		Image scaleImage;
		this.type = type;
		if (type == TilesType.BEACH) {
			icone = new ImageIcon(Tile.class.getResource("/Plage.png"));
		} else if (type == TilesType.FOREST) {
			icone = new ImageIcon(Tile.class.getResource("/Foret.png"));
		} else if (type == TilesType.MOUNTAINS) {
			icone = new ImageIcon(Tile.class.getResource("/Montagne.png"));
		}
		
		scaleImage = icone.getImage().getScaledInstance(resolution, resolution,Image.SCALE_SMOOTH);
		icone.setImage(scaleImage);
		
		this.typeLabel.setIcon(icone);
	}

	/**
	* 
	*/
	public TilesEffect getEffect() {
		return this.effect;
	}

	/**
	* 
	*/
	public void setEffect(TilesEffect effect) {
		ImageIcon icone = null;
		Image scaleImage;
		this.effect = effect;
		
		switch(effect) {
			case BOAT_MOVE:
				icone = new ImageIcon(Tile.class.getResource("/Bateau_move.png"));
				break;
			case BOAT:
				icone = new ImageIcon(Tile.class.getResource("/Bateau.png"));
				break;
			case DOLPHIN_MOVE:
				icone = new ImageIcon(Tile.class.getResource("/Dauphin_move.png"));
				break;
			case SEASNAKE_LOST:
				icone = new ImageIcon(Tile.class.getResource("/Serpent_de_mer.png"));
				break;
			case SHARK:
				icone = new ImageIcon(Tile.class.getResource("/Requin.png"));
				break;
			case SHARK_DEATH:
				icone = new ImageIcon(Tile.class.getResource("/Requin_mort.png"));
				break;
			case SHARK_LOST:
				icone = new ImageIcon(Tile.class.getResource("/Requin_perdu.png"));
				break;
			case WHIRLPOOL:
				icone = new ImageIcon(Tile.class.getResource("/Tourbillon.png"));
				break;
			case VOLCANO:
				icone = new ImageIcon(Tile.class.getResource("/Volcan.png"));
				break;
			case WHALE:
				icone = new ImageIcon(Tile.class.getResource("/Baleine.png"));
				break;
			case WHALE_DEATH:
				icone = new ImageIcon(Tile.class.getResource("/Baleine_mort.png"));
				break;
			case WHALE_LOST:
				icone = new ImageIcon(Tile.class.getResource("/Baleine_perdu.png"));
				break;
		}
		
		scaleImage = icone.getImage().getScaledInstance(resolution, resolution,Image.SCALE_SMOOTH);
		icone.setImage(scaleImage);
		
		this.effectLabel.setIcon(icone);
	}

	/*
	 * <p>
	 * Retourne la tuile présente sur la case choisie de la map.
	 * </p>
	 * @since1.0
	 */
	public void flipTile(Hexagon hexagon, Player p, Board board) {
		for(Explorer e : hexagon.getExplorerList()) {
			e.setStatus(ExplorerStatus.SWIMMER);
			e.setMovePoint(1);
		}
		if(hexagon.getTile().getEffect().getType() == "Verte"){
			hexagon.getTile().applyEffect(hexagon,board);
		}
		else {
			  p.getTileList().add(hexagon.getTile());
		}
		
		hexagon.remove(hexagon.getTile().getTypeLabel());
		hexagon.revalidate();
		hexagon.repaint();
		hexagon.removeTile();
		 
	}
	
	/**
	 * <p>
	 * Applique l'effet de la tuile.
	 * </p>
	 * @since1.0
	 */
	public void applyEffect(Hexagon hex,Board board) {
		switch(this.effect) {
		
		case BOAT : this.boatEffect(hex, board);break;
		case SHARK : this.sharkEffect(hex,board);break;
		case WHIRLPOOL : this.whirlpoolEffect(hex,board); break;
		case VOLCANO : this.volcanoEffect(board); break;
		case WHALE : this.whaleEffect(hex,board); break;
		default : break;
		}
		if(!hex.getSharkList().isEmpty()) {
			hex.getSharkList().get(0).makeEffect(hex);
		}
		if(!hex.getSeaSnakeList().isEmpty()) {
			hex.getSeaSnakeList().get(0).makeEffect(hex);
		}
		if(!hex.getWhaleList().isEmpty()) {
			hex.getWhaleList().get(0).makeEffect(hex);
		}
	}
	
	/**
	 * <p>
	 * Ajoute un bateau sur la case de la tuile.
	 * Tuile instantanée.
	 * </p>
	 * @param tilePosition Tuile actuelle
	 */
	public void boatEffect(Hexagon tilePosition, Board board) {
		Boat b = new Boat(resolution);
        b.setPosition(0, 0, resolution, 68);
        b.createImage(resolution);
		tilePosition.addPawn(b);
		if(tilePosition.getExplorerList().size() > 3) {
	        board.setDisplayExternalPanel(true);
            board.getExternalPanel().setClickedHex(tilePosition);
	        board.getExternalPanel().setExternalPanelState(ExternalPanelState.BOARDINGPANEL);
			
		} else {
			board.getExternalPanel().setAnimationType(AnimationType.BOAT_SUMMON);
	        board.setDisplayExternalPanel(true);
	        board.getExternalPanel().setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
	        for (int i = tilePosition.getExplorerList().size() - 1; i >= 0; i--) {
	        	Explorer e = tilePosition.getExplorerList().get(i);
	        	e.move(tilePosition, b, tilePosition);
			}
		}
	}
		
	/**
	 * <p>
	 * Ajoute un requin sur la case de la tuile.
	 * Tuile instantanée.
	 * </p>
	 * @param tilePosition Tuile actuelle
	 */
	public void sharkEffect(Hexagon tilePosition, Board board) {
		board.getExternalPanel().setAnimationType(AnimationType.SHARK_SUMMON);
        board.setDisplayExternalPanel(true);
        board.getExternalPanel().setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
		Shark s = new Shark(board);
		tilePosition.addPawn(s);
	}
	
	/**
	 * <p>
	 * Tue le requin sur la tuile.
	 * Tuile défense.
	 * </p>
	 * @param tilePosition Tuile actuelle
	 */
	public void sharkDeathEffect(Hexagon tilePosition, Board board) {
		board.getExternalPanel().setAnimationType(AnimationType.SHARK_COUNTER);
        board.setDisplayExternalPanel(true);
        board.getExternalPanel().setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
		// a changer avec lucas
		Shark s = new Shark(board);
		tilePosition.removePawn(s);
	}
	
	/**
	 * <p>
	 * Retire tout les pions sur les cases de mer aux alentours et sur cette tuile.
	 * Tuile instantanée.
	 * </p>
	 */
	public void whirlpoolEffect(Hexagon tilePosition,Board board) {
		board.getExternalPanel().setAnimationType(AnimationType.WHIRLPOOL);
        board.setDisplayExternalPanel(true);
        board.getExternalPanel().setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
		Hexagon[][] hexagons = board.getHexagons();
		List<Hexagon> listNeighbors = new ArrayList<Hexagon>();

       
		listNeighbors = findNeighbors(tilePosition, board);		
		listNeighbors.add(tilePosition);
		for(Hexagon hexagon :listNeighbors ) {
			hexagons[hexagon.getLine()][hexagon.getColumn()].removeAllPawn();
			hexagons[hexagon.getLine()][hexagon.getColumn()].displayPawns(board);
		}										
	}
	
	/**
	 * <p>
	 * Fin de la partie.
	 * Tuile instantanée.
	 * </p>
	 */
	public void volcanoEffect(Board board) {
		board.getExternalPanel().setAnimationType(AnimationType.VOLCANO);
        board.setDisplayExternalPanel(true);
        board.getExternalPanel().setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
		//endgame
	}
	
	/**
	 * <p>
	 * Ajoute un pion baleine sur la case choisie.
	 * Tuile à effet instantanée.
	 * </p>
	 * @param tilePosition Tuile actuelle
	 * @since1.0
	 */
	public void whaleEffect(Hexagon tilePosition, Board board) {
		board.getExternalPanel().setAnimationType(AnimationType.WHALE_SUMMON);
        board.setDisplayExternalPanel(true);
        board.getExternalPanel().setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
		Whale w = new Whale(board);
		tilePosition.addPawn(w);	
	}
	
	/**
	 * <p>
	 * Tue la baleine sur la tuile.
	 * Tuile défense.
	 * </p>
	 * @param tilePosition Tuile actuelle
	 * @since1.0
	 */
	public void whaleDeathEffect(Hexagon tilePosition, Board board) {
		board.getExternalPanel().setAnimationType(AnimationType.WHALE_COUNTER);
        board.setDisplayExternalPanel(true);
        board.getExternalPanel().setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
		//a changer avec lucas
		Whale w = new Whale(board);
		tilePosition.removePawn(w);
	}
	
	/**
	 * <p>
	 * Déplace une baleine sur une case de mer vide.
	 * Tuile en début de tour.
	 * </p>
	 */
	public void whaleLostEffect() {
		//a voir avec kevin pour la selection de case
		//selectionner une baleine
		//selectionne la case ou on veut la deplacer (mer inoccupÃ©e)
	}

	/**
	 * <p>
	 * Renvoie tous les voisins d'une tuile dans une liste
	 * </p>
	 * @since2.0
	 */
	public List<Hexagon> findNeighbors(Hexagon actualPosition, Board board) {
        List<Hexagon> tmp = new ArrayList<Hexagon>();
        List<Hexagon> listHexagon = new ArrayList<Hexagon>();
        
        tmp.add(board.getTopLeft(actualPosition));
        tmp.add(board.getTopRight(actualPosition));
        tmp.add(board.getLeft(actualPosition));
        tmp.add(board.getRight(actualPosition));
        tmp.add(board.getBottomLeft(actualPosition));
        tmp.add(board.getBottomRight(actualPosition));

        for (Hexagon hexagon : tmp) {
            if (hexagon != null
                   && hexagon.isSea()) {
                listHexagon.add(hexagon);
            }
        }
		return listHexagon;
    }

	public JLabel getEffectLabel() {
		return effectLabel;
	}
	
	public JLabel getTypeLabel() {
		return typeLabel;
	}

	/**
	 * 
	 * @return
	 */
	public boolean checkPlayTile(Board board,Player p) {
		switch(this.effect) {
		case BOAT_MOVE : 
			if(board.isBoatOnBoard()) {
				return true;
			}
		case DOLPHIN_MOVE : 
			if(board.isSwimmerOnBoard(p)) {
				return true;
			}
		case SHARK_LOST : 
			if(board.isSharkOnBoard()) {
				return true;
			}
		case SEASNAKE_LOST : 		
			if(board.isSeaSnakeOnBoard()) {
				return true;
			}
		case WHALE_LOST : 
			if(board.isWhaleOnBoard()) {
				return true;
			}
		default : break;
		}
		return false;
	}
	
}
