
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

	/**
	* 
	*/
	/*
	 * public void whaleEffect(Hexagon tilePosition) {
	 * Whale w = new Whale();
	 * tilePosition.addPawn(w);
	 * }
	 */

	/**
	* 
	*/
	public void whaleDeathEffect(/* board */) {
		// board[i][j].listePions.removeFromBoard(whale)
	}

	/**
	* 
	*/
	public void whaleLostEffect(/* board */) {
		// selectionner une baleine
		// selectionne la case ou on veut la deplacer (mer inoccupée)
	}

	/**
	* 
	*/
	public void boatMoveEffect() {
		// selectionnne un bateau
		// le de placer de 1 a 3 cases
	}

	/**
	* 
	*/
	public void boatEffect() {
		// board[i][j].tuiles = MER
		// board[i][j].listePions.addToBoard(boat)
	}

	/**
	* 
	*/
	public void dolphinMoveEffect() {
		// selectionnne un nageur
		// le de placer de 1 a 3 cases
	}

	/**
	* 
	*/
	public void sharkEffect() {
		// board[i][j].tuiles = MER
		// board[i][j].listePions.addToBoard(shark)
	}

	/**
	* 
	*/
	public void sharkDeathEffect() {
		// board[i][j].listePions.removeFromBoard(shark)
	}

	/**
	* 
	*/
	public void sharkLostEffect() {
		// selectionner un requin
		// selectionne la case ou on veut la deplacer (mer inoccupée)
	}

	/**
	* 
	*/
	public void seasnakeLostEffect() {
		// selectionner un seasnake
		// selectionne la case ou on veut la deplacer (mer inoccupée)
	}

	/**
	* 
	*/
	public void whirlpoolEffect() {
		// if(board[i][j].tuile=MER)
		// for(i=0;i<len(board[i][j].listePions);i++) removeFromBoard(pion)
	}

	/**
	* 
	*/
	public void volcanoEffect() {
		// endgame
	}

}
