package fr.mcstudio.enums;

// Creating an enum of different ActionTurn.
public enum ActionTurn {
    PLAY_TILE, MOVE_PAWNS, DISCOVER_TILE, MOVE_MONSTER;

    // Creating an array of the enum values.
    private static ActionTurn[] vals = values();

    /**
     * "Return the next value in the enum"
     * 
     * @return The next value in the enum.
     */
    public ActionTurn next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    /**
     * "Return the title of the current state."
     * 
     * The function is a switch statement that returns a string depending on the current state
     * 
     * @return The title of the enum.
     */
    public String getTitle() {
        switch (this) {
            case PLAY_TILE:
                return "Jouez une tuile";
            case MOVE_PAWNS:
                return "Déplacez vos pions";
            case DISCOVER_TILE:
                return "Retournez une tuile";
            case MOVE_MONSTER:
                return "Lancez le dé";
            default:
                break;
        }
        return null;
    }

    /**
     * It returns a string that describes the current action
     * 
     * @return The description of the action.
     */
    public String getDesc() {
        switch (this) {
            case PLAY_TILE:
                return "<html><center>Jouez une tuile de votre main ou passez votre action</center></html>";
            case MOVE_PAWNS:
                return "<html><center>Choisissez un explorateur<br>ou un bateau et déplacez le</center></html>";
            case DISCOVER_TILE:
                return "<html><center>Retournez une tuile et<br>découvrez ce qu'il se cache derrière</center></html>";
            case MOVE_MONSTER:
                return "<html><center>Lancez le dé puis déplacez<br>le monstre qui a été choisit</center></html>";
            default:
                break;
        }
        return null;
    }

}