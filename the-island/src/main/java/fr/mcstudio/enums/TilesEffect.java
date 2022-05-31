package fr.mcstudio.enums;

// It's a list of all the possible effects of the tiles.
public enum TilesEffect {
	
	BOAT_MOVE("Navigation", "Les vents vous sont favorables. "
			+ "Deplacez un des bateaux que vous controlez de 1 "
			+ "1 � 3 cases de mer", "Rouge"),
	BOAT("Bateau", "Prenez un pion bateau mis de c�t� "
			+ "et placez-le sur la case de mer "
			+ "qu�occupait la tuile de terrain. "
			+ "Si cette case de mer contenait un ou plusieurs "
			+ "nageurs, placez-les � bord du bateau. "
			+ "Si la case de mer contenait plus de "
			+ "trois nageurs, c'est le joueur ayant "
			+ "r�v�l� la tuile de terrain qui choisit "
			+ "lesquels montent � bord ", "Verte"),
	DOLPHIN_MOVE("Sauvetage", "Un dauphin vient en aide � "
			+ "l'un de vos nageurs. D�placez un de vos nageurs de 1 � "
			+ "3 cases de mer.", "Rouge"),
	SEASNAKE_LOST("Serpent de Mer Perdu",
			"D�placez le serpent de mer de votre choix "
			+ "d�j� pr�sent sur le plateau de jeu sur "
					+ "n'mporte quelle case de mer inoccup�e",
			"Rouge"),
	SHARK("Requin", "Prenez un pion requin mis de c�t� "
			+ "et placez sur la case de mer qu�occupait "
			+ "la tuile de terrain. Tout nageur occupant "
			+ "cette case de mer est retir� du jeu", "Verte"),
	SHARK_DEATH("Requin Mort", "Quand un autre joueur d�place "
			+ "un requin dans une case de mer occup�e par l�un "
			+ "de vos nageurs, vous pouvez jouer cette "
			+ "tuile de terrain pour retirer le requin du "
			+ "jeu. Tous les nageurs demeurent dans la case mer. ", "Defense"),
	SHARK_LOST("Requin Perdu", "D�placez le requin de votre "
			+ "choix d�j� pr�sent sur le plateau de jeu sur n�importe "
			+ "quelle case de mer inoccup�e.", "Rouge"),
	
	WHIRLPOOL("Tourbillon", "Tourbillon : retirez du jeu tous "
			+ "les nageurs, serpents de mer, requins, "
			+ "baleines, bateaux et explorateurs de la case de "
			+ "mer qu'occupait la tuile de "
			+ "terrain et de toutes les cases mer adjacentes.", "Verte"),
	VOLCANO("Volcan", "Eruption volcanique : Fin du jeu", "Verte"),
	WHALE("Baleine",
			"Prenez un pion baleine mis de c�t� et placez-le sur "
			+ "la case de mer qu'occupait la tuile de terrain !",
			"Verte"),
	WHALE_DEATH("Baleine Morte",
			"Quand un autre joueur d�place une baleine dans une "
			+ "case de mer occup�e par un bateau que "
					+ "vous contr�lez, vous pouvez jouer cette tuile "
					+ "de terrain pour retirer la baleine du jeu. "
					+ "Votre bateau demeure dans la case de mer",
			"Defense"),
	WHALE_LOST("Baleine Perdue", "D�placez la baleine de votre choix "
			+ "d�j� pr�sente sur le plateau de jeu sur n�importe "
			+ "quelle case de mer inoccup�e ", "Rouge");

	private final String name;
	private final String description;
	private final String type;

	// It's a constructor.
	TilesEffect(String name, String description, String type) {
		this.name = name;
		this.description = description;
		this.type = type;
	}

	/**
	 * This function returns the name of the tiles
	 * 
	 * @return The name of the tiles.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This function returns the description of the object
	 * 
	 * @return The description of the item.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This function returns the type of the current object
	 * 
	 * @return The type of the object.
	 */
	public String getType() {
		return type;
	}
}
