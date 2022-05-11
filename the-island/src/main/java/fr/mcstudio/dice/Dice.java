/*
 * Nom de classe : Dice
 *
 * Description   : Gestion du dé du jeu The Island 
 *
 * Version       : 1.0
 *
 * Date          : 08/05/2022
 * 
 * Copyright     : Loïk Simon
 */

package fr.mcstudio.dice;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * <p>
 * Gestion du dé du jeu The Island
 * </p>
 * 
 * @version 1.0
 * 
 * @author Loïk Simon
 */
public class Dice {

	/**
	 * <p>
	 * Constructeur par défaut
	 * </p>
	 */
	public Dice() {
		this.seaSnakeList.add(new DiceAnimation("the-island/img/Animation/seaSnake/seaSnake1.gif"));
		this.seaSnakeList.add(new DiceAnimation("the-island/img/Animation/seaSnake/seaSnake2.gif"));
		this.sharkList.add(new DiceAnimation("the-island/img/Animation/seaSnake/shark1.gif"));
		this.sharkList.add(new DiceAnimation("the-island/img/Animation/seaSnake/shark2.gif"));
		this.whaleList.add(new DiceAnimation("the-island/img/Animation/seaSnake/whale1.gif"));
		this.whaleList.add(new DiceAnimation("the-island/img/Animation/seaSnake/whale2.gif"));

	}

	/**
	 * <p>
	 * Retourne le résultat d'un lancé de dé sous forme de int.
	 * </p>
	 * 
	 * @since 1.0
	 */
	public int roll() {
		return (int) ((Math.random() * (3 - 1)) + 1);
	}

	/**
	 * <p>
	 * Lance une animation du dé.
	 * </p>
	 * 
	 * @param panelAnimation JPanel sur lequel placer l'animation.
	 * @param layeredPane    JLayeredPane sur lequel placer le JPanel d'animation.
	 * @param result         Résultat du dé à animer.
	 * @since 1.0
	 */
	public void startAnimation(JPanel panelAnimation, JLayeredPane layeredPane, int result) {
		int animationNumber;
		switch (result) {
			case 1:
				animationNumber = (int) (Math.random() * this.seaSnakeList.size());
				panelAnimation.add(this.seaSnakeList.get(animationNumber).getLabel());
				break;

			case 2:
				animationNumber = (int) (Math.random() * this.sharkList.size());
				panelAnimation.add(this.sharkList.get(animationNumber).getLabel());
				break;

			case 3:
				animationNumber = (int) (Math.random() * this.whaleList.size());
				panelAnimation.add(this.whaleList.get(animationNumber).getLabel());
				break;

			default:
				System.err.print("Dans Dice.java, dice.startAnimation : résultat non compatible");
				break;
		}

		layeredPane.add(panelAnimation, 3);
	}

	/**
	 * <p>
	 * Arrête l'animation du dé.
	 * </p>
	 * 
	 * @param panelAnimation JPanel sur lequel est placé l'animation.
	 * @param layeredPane    JLayeredPane sur lequel est placé le JPanel
	 *                       d'animation.
	 * @since 1.0
	 */
	public void endAnimation(JPanel panelAnimation, JLayeredPane layeredPane) {
		panelAnimation.setVisible(false);
		panelAnimation.repaint();
		layeredPane.remove(panelAnimation);
	}

	/**
	 * <p>
	 * Liste de d'animation du résultat "seaSnake".
	 * </p>
	 */
	private List<DiceAnimation> seaSnakeList = new ArrayList<DiceAnimation>();

	/**
	 * <p>
	 * Liste de d'animation du résultat "shark".
	 * </p>
	 */
	private List<DiceAnimation> sharkList = new ArrayList<DiceAnimation>();

	/**
	 * <p>
	 * Liste de d'animation du résultat "whale".
	 * </p>
	 */
	private List<DiceAnimation> whaleList = new ArrayList<DiceAnimation>();

}