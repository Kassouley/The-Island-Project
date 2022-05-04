package fr.koraizon.theisland.enums;

public enum TilesEffect {
	WHALE("Baleine", "Prenez un pion baleine mis de côté et placez-le sur la case de mer qu’occupait la tuile de terrain !", "Verte"), 
	WHALE_DEATH("Baleine Morte", "Quand un autre joueur déplace une baleine dans une case de mer occupée par un bateau que "
			+ "vous contrôlez, vous pouvez jouer cette tuile de terrain pour retirer la baleine du jeu. "
			+ "Votre bateau demeure dans la case de mer", "Défense"), 
	WHALE_LOST("Baleine Perdue", "Déplacez la baleine de votre choix déjà présente sur le plateau de jeu sur n’importe "
			+ "quelle case de mer inoccupée ", "Rouge"), 
	BOAT_MOVE("Navigation", "Les vents vous sont favorables. Déplacez un des bateaux que vous contrôlez de 1 "
			+ "à 3 cases de mer", "Rouge"),
	BOAT("Bateau", "Prenez un pion bateau mis de côté et placez-le sur la case de mer "
			+ "qu’occupait la tuile de terrain. Si cette case de mer contenait un ou plusieurs "
			+ "nageurs, placez-les à bord du bateau. Si la case de mer contenait plus de "
			+ "trois nageurs, c’est le joueur ayant révélé la tuile de terrain qui choisit "
			+ "lesquels montent à bord ", "Verte"),
	DOLPHIN_MOVE("Sauvetage", "Un dauphin vient en aide à l’un de vos nageurs. Déplacez un de vos nageurs de 1 à "
			+ "3 cases de mer.", "Rouge"), 
	SHARK("Requin", "Prenez un pion requin mis de côté et placez sur la case de mer qu’occupait "
			+ "la tuile de terrain. Tout nageur occupant cette case de mer est retiré du jeu", "Verte"), 
	SHARK_DEATH("Requin Mort", "Quand un autre joueur déplace un requin dans une case de mer occupée par l’un "
			+ "de vos nageurs, vous pouvez jouer cette tuile de terrain pour retirer le requin du "
			+ "jeu. Tous les nageurs demeurent dans la case mer. ", "Défense"), 
	SHARK_LOST("Requin Perdu", "Déplacez le requin de votre choix déjà présent sur le plateau de jeu sur n’importe "
			+ "quelle case de mer inoccupée.", "Rouge"), 
	SEASNAKE_LOST("Serpent de Mer Perdu", "Déplacez le serpent de mer de votre choix déjà présent sur le plateau de jeu sur "
			+ "n’importe quelle case de mer inoccupée", "Rouge"),
	WHIRLPOOL("Tourbillon", "Tourbillon : retirez du jeu tous les nageurs, serpents de mer, requins, "
			+ "baleines, bateaux et explorateurs de la case de mer qu’occupait la tuile de "
			+ "terrain et de toutes les cases mer adjacentes.", "Verte"),
	VOLCANO("Volcan", "Eruption volcanique : Fin du jeu", "Verte");
	
	private final String name;
	private final String description;
	private final String type;
	
	TilesEffect(String name, String description, String type){
		this.name = name;
		this.description = description;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}
}
