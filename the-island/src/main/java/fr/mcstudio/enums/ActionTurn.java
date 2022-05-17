package fr.mcstudio.enums;

public enum ActionTurn {
    PLAY_TILE, MOVE_PAWNS, DISCOVER_TILE, MOVE_MONSTER;

    private static ActionTurn[] vals = values();

    public ActionTurn next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

}