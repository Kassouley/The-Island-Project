package fr.koraizon.theisland;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fr.koraizon.theisland.enums.TilesEffect;
import fr.koraizon.theisland.enums.TilesType;

@SuppressWarnings("serial")
public class Tile extends JLabel{
	
	private TilesType type;
	private TilesEffect effect;
	

	public Tile() {
		this.setHorizontalAlignment(SwingConstants.CENTER);		
	}

	public TilesType getType() {
		return type;
	}

	public void setType(TilesType type) {
		this.type = type;
		if(type == TilesType.BEACH) {
			this.setIcon(new ImageIcon(Tile.class.getResource("/sprites/Plage.png")));
		} else if(type == TilesType.FOREST) {
			this.setIcon(new ImageIcon(Tile.class.getResource("/sprites/Foret.png")));
		} else if(type == TilesType.MONTAINS){
			this.setIcon(new ImageIcon(Tile.class.getResource("/sprites/Montagne.png")));
		} else {
			this.setIcon(null);
		}
	}
	
	public TilesEffect getEffect() {
		return effect;
	}

	public void setEffect(TilesEffect effect) {
		this.effect = effect;
	}
	
	public void setPosition(int x, int y) {
		this.setBounds(x, y, 90, 90);
	}
	
}
