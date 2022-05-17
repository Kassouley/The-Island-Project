
package fr.mcstudio.tiles;

import java.awt.Image;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fr.mcstudio.board.Board;
import fr.mcstudio.board.Hexagon;
import fr.mcstudio.enums.HexagonType;
import fr.mcstudio.enums.TilesEffect;
import fr.mcstudio.enums.TilesType;
import fr.mcstudio.game.Player;
import fr.mcstudio.pawns.Boat;
import fr.mcstudio.pawns.Shark;
import fr.mcstudio.pawns.Whale;

/**
 * 
 */
@SuppressWarnings("serial")
public class Tile extends JLabel {

	private TilesType type = null;
	private TilesEffect effect = null;

	/**
	 * Default constructor
	 */
	public Tile() {
		this.setHorizontalAlignment(SwingConstants.CENTER);
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
	public void setType(int resolution, TilesType type) {
		ImageIcon icone = null;
		Image scaleImage;
		this.type = type;
		if (type == TilesType.BEACH) {
			icone = new ImageIcon(Tile.class.getResource("/Plage.png"));
		} else if (type == TilesType.FOREST) {
			icone = new ImageIcon(Tile.class.getResource("/Foret.png"));
		} else if (type == TilesType.MONTAINS) {
			icone = new ImageIcon(Tile.class.getResource("/Montagne.png"));
		}
		switch(resolution) {
		case 70:
			scaleImage = icone.getImage().getScaledInstance(70, 70,Image.SCALE_SMOOTH);
			icone.setImage(scaleImage);
			break;
		case 80:
			scaleImage = icone.getImage().getScaledInstance(80, 80,Image.SCALE_SMOOTH);
			icone.setImage(scaleImage);
			break;
		default:
			break;
		}

		this.setIcon(icone);
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
		this.effect = effect;
	}

	/**
	* 
	*/
	public void setPosition(int x, int y) {
		this.setBounds(x, y, 90, 90);
	}

	/**
	 * <p>
	 * Regarde si il y a une tuile sur l'hexagone cliqué
	 * </p>
	 * @since1.0
	 */
	public void discover(Hexagon tilePosition,Player p,Board board) {
		if(tilePosition.getTile().getType() != null) {
				flipTile(tilePosition,p,board);
		}
		else {
			System.out.println("Aucune tuile sur la case choisie\n");
		}
	}
	

	/*public void applyEffect(Hexagon tilePosition) {
		switch (this.effect) {
			case WHALE:
				this.whaleEffect(tilePosition);
				break;
			case WHALE_DEATH:
				this.whaleDeathEffect(tilePosition);
				break;
			case WHALE_LOST:
				this.whaleLostEffect();
				break;
			case BOAT_MOVE:
				this.boatMoveEffect();
				break;
			case BOAT:
				this.boatEffect(tilePosition);
				break;
			case DOLPHIN_MOVE:
				this.dolphinMoveEffect();
				break;
			case SHARK:
				this.sharkEffect(tilePosition);
				break;
			case SHARK_DEATH:
				this.sharkDeathEffect(tilePosition);
				break;
			case SHARK_LOST:
				this.sharkLostEffect();
				break;
			case SEASNAKE_LOST:
				this.seasnakeLostEffect();
				break;
			case WHIRLPOOL:
				this.whirlpoolEffect();
				break;
			case VOLCANO:
				this.volcanoEffect();
				break;
			default:
				break;
		}
	}*/

	/*
	 * <p>
	 * Retourne la tuile présente sur la case choisie de la map.
	 * </p>
	 * @since1.0
	 */
	public void flipTile(Hexagon tilePosition,Player p,Board board) {
		Hexagon[][] hexagons = board.getHexagons();
		tilePosition.getTile().applyEffect(tilePosition,board);	
		/*if(tilePosition.getTile().getEffect().getType() == "Verte"){
			tilePosition.getTile().applyEffect(tilePosition,hexagon);		  	
		  }
		else {
			  p.getTileList().add(tilePosition.getTile());
		  }*/
		
		//hexagons[tilePosition.getLine()][tilePosition.getColumn()].setType(HexagonType.SEA);
		//hexagons[tilePosition.getLine()][tilePosition.getColumn()].setTile(null);
		

		 
	}
	
	/**
	 * <p>
	 * Applique l'effet de la tuile.
	 * </p>
	 * @since1.0
	 */
	public void applyEffect(Hexagon tilePosition,Board board) {
		System.out.println(this.effect);
		switch(this.effect) {
		
		case BOAT_MOVE : this.boatMoveEffect();break;
		case BOAT : this.boatEffect(tilePosition);break;
		case DOLPHIN_MOVE :this.dolphinMoveEffect(); break;
		case SEASNAKE_LOST : this.seasnakeLostEffect();break;
		case SHARK : this.sharkEffect(tilePosition);break;
		case SHARK_DEATH : this.sharkDeathEffect(tilePosition); break;
		case SHARK_LOST : this.sharkLostEffect(); break;
		case WHIRLPOOL : this.whirlpoolEffect(tilePosition,board); break;
		case VOLCANO : this.volcanoEffect(); break;
		case WHALE : this.whaleEffect(tilePosition); break;
		case WHALE_DEATH : this.whaleDeathEffect(tilePosition);break;
		case WHALE_LOST :this.whaleLostEffect(); break;
		default : break;
		}
	}
	
	
	
	/**
	 * <p>
	 * Déplace un bateau que vous controlez de 1 à 3 cases.
	 * Tuile en début de tour.
	 * </p>
	 */
	public void boatMoveEffect() {
		//selectionnne un bateau
		//le de placer de 1 a 3 cases
	}
	
	/**
	 * <p>
	 * Ajoute un bateau sur la case de la tuile.
	 * Tuile instantanée.
	 * </p>
	 * @param tilePosition Tuile actuelle
	 */
	public void boatEffect(Hexagon tilePosition) {
		Boat b = new Boat();
		tilePosition.addPawn(b);
	}
	
	/**
	 * <p>
	 * Déplace un nageur de 1 à 3 cases.
	 * Tuile en début de tour.
	 * </p>
	 */
	public void dolphinMoveEffect() {
		//selectionnne un nageur
		//le de placer de 1 a 3 cases
	}
	
	/**
	 * <p>
	 * Déplace un serpent de mer sur une case de mer vide.
	 * Tuile en début de tour.
	 * </p>
	 */
	public void seasnakeLostEffect() {
		//selectionner un seasnake
		//selectionne la case ou on veut la deplacer (mer inoccupÃ©e)
	}
		
	/**
	 * <p>
	 * Ajoute un requin sur la case de la tuile.
	 * Tuile instantanée.
	 * </p>
	 * @param tilePosition Tuile actuelle
	 */
	public void sharkEffect(Hexagon tilePosition) {
		Shark s = new Shark();
		tilePosition.addPawn(s);
	}
	
	/**
	 * <p>
	 * Tue le requin sur la tuile.
	 * Tuile défense.
	 * </p>
	 * @param tilePosition Tuile actuelle
	 */
	public void sharkDeathEffect(Hexagon tilePosition) {
		// a changer avec lucas
		Shark s = new Shark();
		tilePosition.removePawn(s);
	}
	
	/**
	 * <p>
	 * Déplace un requin sur une case de mer vide.
	 * Tuile en début de tour. 
	 * </p>
	 */
	public void sharkLostEffect() {
		//selectionner un requin
		//selectionne la case ou on veut la deplacer (mer inoccupÃ©e)
	}
	
	/**
	 * <p>
	 * Retire tout les pions sur les cases de mer aux alentours et sur cette tuile.
	 * Tuile instantanée.
	 * </p>
	 */
	public void whirlpoolEffect(Hexagon tilePosition,Board board) {
		Hexagon[][] hexagons = board.getHexagons();
		List<Hexagon> listNeighbors = new ArrayList<Hexagon>();

       
		listNeighbors = findNeighbors(tilePosition, board);		
		listNeighbors.add(tilePosition);
		for(Hexagon hexagon :listNeighbors ) {
			hexagons[hexagon.getLine()][hexagon.getColumn()].removeAllPawn();

			System.out.println(" ligne = " + hexagon.getLine() +" colonne = : " + hexagon.getColumn() + " coucou toi");
		}										
	}
	
	/**
	 * <p>
	 * Fin de la partie.
	 * Tuile instantanée.
	 * </p>
	 */
	public void volcanoEffect() {
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
	public void whaleEffect(Hexagon tilePosition) {
		Whale w = new Whale();
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
	public void whaleDeathEffect(Hexagon tilePosition) {
		//a changer avec lucas
		Whale w = new Whale();
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
	
	
	
}
