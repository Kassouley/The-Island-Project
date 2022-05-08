
import java.util.*;

/**
 * 
 */
public class Tile {

    /**
     * Default constructor
     */
    public Tile() {
    }

    /**
     * 
     */
    public int type;

    /**
     * 
     */
    public int effect;

    /**
     * 
     */
   public void discover(/*board*/) {
		/*if(board[i][j].tile.getType() == Verte){
		 * 	board[i][j].tile.applyeffect()
		 * 	removeFromBoard tile
		 * }
		 * else
		 *	putInHands ( board[i][j].tile)
		 */
	}

    /**
     * 
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
     * 
     */    
    public void whaleEffect(/*board*/) {
		//selectionner une case de mer Uniquement
		//Point(i,j)
		//board[i][j]listePions.addToBoard(whale)	
	}
	
    /**
     * 
     */
	public void whaleDeathEffect(/*board*/) {
		//board[i][j].listePions.removeFromBoard(whale)
	}
	
    /**
     * 
     */
	public void whaleLostEffect(/*board*/) {
		//selectionner une baleine
		//selectionne la case ou on veut la deplacer (mer inoccupée)
	}
	
    /**
     * 
     */
	public void boatMoveEffect() {
		//selectionnne un bateau
		//le de placer de 1 a 3 cases
	}
	
    /**
     * 
     */
	public void boatEffect() {
		//board[i][j].tuiles = MER
		//board[i][j].listePions.addToBoard(boat)
	}
	
    /**
     * 
     */
	public void dolphinMoveEffect() {
		//selectionnne un nageur
		//le de placer de 1 a 3 cases
	}
	
    /**
     * 
     */
	public void sharkEffect() {
		//board[i][j].tuiles = MER
		//board[i][j].listePions.addToBoard(shark)
	}
	
    /**
     * 
     */
	public void sharkDeathEffect() {
		//board[i][j].listePions.removeFromBoard(shark)
	}
	
    /**
     * 
     */
	public void sharkLostEffect() {
		//selectionner un requin
		//selectionne la case ou on veut la deplacer (mer inoccupée)
	}
	
    /**
     * 
     */
	public void seasnakeLostEffect() {
		//selectionner un seasnake
		//selectionne la case ou on veut la deplacer (mer inoccupée)
	}
	
    /**
     * 
     */
	public void whirlpoolEffect() {
		//if(board[i][j].tuile=MER)
		//for(i=0;i<len(board[i][j].listePions);i++) removeFromBoard(pion)
	}
	
    /**
     * 
     */
	public void volcanoEffect() {
		//endgame
	}

}
