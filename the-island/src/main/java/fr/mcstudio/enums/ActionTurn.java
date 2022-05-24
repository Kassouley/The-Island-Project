package fr.mcstudio.enums;

public enum ActionTurn {
	PLAY_TILE, MOVE_PAWNS, DISCOVER_TILE, MOVE_MONSTER;

    private static ActionTurn[] vals = values();

    public ActionTurn next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public String getTitle() {
        switch (this) {
            case PLAY_TILE:
                return "Jouez une tuile !";
            case MOVE_PAWNS:
                return "Déplacez vos pions !";
            case DISCOVER_TILE:
                return "Retournez une tuile !";
            case MOVE_MONSTER:
                return "Lancez le dé !";
            default:
                break;
        }
        return null;
    }

    public String getDesc() {
        switch (this) {
            case PLAY_TILE:
                return "Jouez une tuile de votre main ou passez votre action";
            case MOVE_PAWNS:
                return "Choisissez un explorateur ou un bateau et déplacez le";
            case DISCOVER_TILE:
                return "Retournez une tuile et découvrez ce qu'il se cache derrière";
            case MOVE_MONSTER:
                return "Lancez le dé puis déplacez le monstre qui a été choisit";
            default:
                break;
        }
        return null;
    }

}