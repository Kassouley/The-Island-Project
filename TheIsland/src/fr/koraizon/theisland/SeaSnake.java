package fr.koraizon.theisland;

public class SeaSnake extends EffectPawn {

    /**
     * <p>
     * Constructeur par d�faut
     * </p>
     */
    public SeaSnake() {
    }

    /**
     * <p>
     * R�alise l'effet du serpent de mer, coule les bateaux et tue les explorateurs.
     * </p>
     * 
     * @param hexagon Case dans laquel est r�alis� l'effet.
     * @since 1.0
     * 
     */
    public void makeEffect(Hexagon hexagon) {
        Whale whaleEffect = new Whale();
        Shark sharkEffect = new Shark();
        whaleEffect.makeEffect(hexagon);
        sharkEffect.makeEffect(hexagon);
    }

}
