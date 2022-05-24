package fr.mcstudio.util;

import java.util.ArrayList;
import java.util.List;

public class TripletList<Left,Middle,Right> extends ArrayList<Triplet<Left,Middle,Right>> {
    public TripletList() {
        super();
    }

    public boolean containsInTriplet(Object o) {
        for (Triplet<Left,Middle,Right> triplet : this) {
            if (o.equals(triplet.getLeft()) 
                    || o.equals(triplet.getMiddle()) 
                    || o.equals(triplet.getRight())) {
                return true;
            }
        }
        return false;
    }

    public List<Left> getLeftList() {
        List<Left> tmp = new ArrayList<Left>();
        for (Triplet<Left,Middle,Right> triplet : this) {
            tmp.add(triplet.getLeft());
        }
        return tmp;
    }

    public List<Middle> getMiddleList() {
        List<Middle> tmp = new ArrayList<Middle>();
        for (Triplet<Left,Middle,Right> triplet : this) {
            tmp.add(triplet.getMiddle());
        }
        return tmp;
    }

    public List<Right> getRightList() {
        List<Right> tmp = new ArrayList<Right>();
        for (Triplet<Left,Middle,Right> triplet : this) {
            tmp.add(triplet.getRight());
        }
        return tmp;
    }
}
