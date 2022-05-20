package fr.mcstudio.util;

import java.util.ArrayList;
import java.util.List;

public class PairList<Left,Right> extends ArrayList<Pair<Left,Right>> {
    public PairList() {
        super();
    }

    public boolean containsInPair(Object o) {
        for (Pair<Left,Right> pair : this) {
            if (o.equals(pair.getLeft()) || o.equals(pair.getRight())) {
                return true;
            }
        }
        return false;
    }

    public List<Left> getLeftList() {
        List<Left> tmp = new ArrayList<Left>();
        for (Pair<Left,Right> pair : this) {
            tmp.add(pair.getLeft());
        }
        return tmp;
    }

    public List<Right> getRightList() {
        List<Right> tmp = new ArrayList<Right>();
        for (Pair<Left,Right> pair : this) {
            tmp.add(pair.getRight());
        }
        return tmp;
    }
}
