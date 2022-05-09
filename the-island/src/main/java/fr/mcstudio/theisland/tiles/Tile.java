
package fr.mcstudio.theisland.tiles;

import fr.mcstudio.theisland.enums.*;
import javax.swing.*;

/**
 * 
 */
public class Tile extends JLabel {

	private TilesType type;
	private TilesEffect effect;

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
		return type;
	}

	/**
	* 
	*/
	public void setType(TilesType type) {
		this.type = type;
		if (type == TilesType.BEACH) {
			this.setIcon(new ImageIcon(Tile.class.getResource("/sprites/Plage.png")));
		} else if (type == TilesType.FOREST) {
			this.setIcon(new ImageIcon(Tile.class.getResource("/sprites/Foret.png")));
		} else if (type == TilesType.MONTAINS) {
			this.setIcon(new ImageIcon(Tile.class.getResource("/sprites/Montagne.png")));
		} else {
			this.setIcon(null);
		}
	}

	/**
	* 
	*/
	public TilesEffect getEffect() {
		return effect;
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
	* 
	*/
	public void discover(/* board */) {
		/*
		 * if(board[i][j].tile.getType() == Verte){
		 * board[i][j].tile.applyeffect()
		 * removeFromBoard tile
		 * }
		 * else
		 * putInHands ( board[i][j].tile)
		 */
	}

	/**
	* 
	*/
	public void applyEffect() {
		switch (this.effect) {
			case WHALE:
				// this.whaleEffect();
				break;
			case WHALE_DEATH:
				this.whaleDeathEffect();
				break;
			case WHALE_LOST:
				this.whaleLostEffect();
				break;
			case BOAT_MOVE:
				this.boatMoveEffect();
				break;
			case BOAT:
				this.boatEffect();
				break;
			case DOLPHIN_MOVE:
				this.dolphinMoveEffect();
				break;
			case SHARK:
				this.sharkEffect();
				break;
			case SHARK_DEATH:
				this.sharkDeathEffect();
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
	}

	/*
	 * <p>
	 * Découvre la tuile présente sur la case de la map.
	 * </p>
	 * @since1.0
	 */
	public void discover(Hexagon tilePosition) {
		if(tilePosition.effect.getType() == Verte){
			tilePosition.applyEffect();
		  	
		  }
		  else {
			  
			 //mettre la tuile dans la main
		  }
		 
	}
	/*
	 * <p>
	 * Applique l'effet de la tuile.
	 * </p>
	 * @since1.0
	 */
	public void applyEffect() {
		switch(this.effect) {
		case WHALE : this.whaleEffect(); break;
		case WHALE_DEATH : this.whaleDeathEffect();break;
		case WHALE_LOST :this.whaleLostEffect(); break;
		case BOAT_MOVE : this.boatMoveEffect();break;
		case BOAT : this.boatEffect();break;
		case DOLPHIN_MOVE :this.dolphinMoveEffect(); break;
		case SHARK : this.sharkEffect();break;
		case SHARK_DEATH : this.sharkDeathEffect(); break;
		case SHARK_LOST : this.sharkLostEffect(); break;
		case SEASNAKE_LOST : this.seasnakeLostEffect();break;
		case WHIRLPOOL : this.whirlpoolEffect(); break;
		case VOLCANO : this.volcanoEffect(); break;
		default : break;
		}
	}
	
	/**
	 * <p>
	 * Ajoute un pion baleine sur la case choisie.
	 * Tuile à effet instantanée.
	 * </p>
	 * @param tilePosition Tuile actuelle
	 * @since 1.0
	 */
	public void whaleEffect(Hexagon tilePosition) {
		whale w = new whale();
		tilePosition.addPawn(w);	
	}
	
	/**
	 * <p>
	 * Tue la baleine sur la tuile.
	 * Tuile défense.
	 * </p>
	 * @param tilePosition Tuile actuelle
	 * @since 1.0
	 */
	public void whaleDeathEffect(Hexagon tilePosition) {
		whale w = new whale();
		tilePosition.removePawn(w);
	}
	
	/**
	 * <p>
	 * Déplace une baleine sur une case de mer vide.
	 * Tuile en début de tour.
	 * </p>
	 */
	public void whaleLostEffect() {
		//selectionner une baleine
		//selectionne la case ou on veut la deplacer (mer inoccupÃ©e)
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
		boat b = new boat();
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
	 * Ajoute un requin sur la case de la tuile.
	 * Tuile instantanée.
	 * </p>
	 * @param tilePosition Tuile actuelle
	 */
	public void sharkEffect(Hexagon tilePosition) {
		shark s = new shark();
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
		shark s = new shark();
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
	 * Retire tout les pions sur les cases de mer aux alentours et sur cette tuile.
	 * Tuile instantanée.
	 * </p>
	 */
	public void whirlpoolEffect() {
		//if(board[i][j].tuile=MER)
		//for(i=0;i<len(board[i][j].listePions);i++) removeFromBoard(pion)
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

	
	
}