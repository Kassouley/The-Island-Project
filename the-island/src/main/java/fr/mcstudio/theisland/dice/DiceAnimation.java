/*
 * Nom de classe : Dice
 *
 * Description   : Gestion des animations de dé du jeu The Island
 *
 * Version       : 1.0
 *
 * Date          : 08/05/2022
 * 
 * Copyright     : Loïk Simon
 */

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * <p>
 * Gestion des animations de dé du jeu The Island
 * </p>
 * 
 * @version 1.0
 * 
 * @author Loïk Simon
 */
public class DiceAnimation {
	
	/**
	 * <p>
	 * Constructeur par défaut
	 * </p>
	 * 
	 * @param path Chemin d'accès vers le gif d'animation.
	 */
	public DiceAnimation(String path) {
		this.label = new JLabel(new ImageIcon(path));
	}
	
	/**
	 * <p>
	 * Accesseur pour le JLabel d'animation.
	 * </p>
	 */
	public JLabel getLabel() {
		return this.label;
	}

	/**
	 * <p>
	 * JLabel d'animation.
	 * </p>
	 */
	private JLabel label;
}