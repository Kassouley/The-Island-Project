package fr.mcstudio.util;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")

/**
 * "This class is a list of pairs, and it has some functions to make it easier to use."
 */
public class PairList<Left,Right> extends ArrayList<Pair<Left,Right>> {

    // It's a constructor.
    public PairList() {
        super();
    }

    /**
     * "If the object is equal to the left or right of the pair, return true."
     * 
     * The function is a boolean, so it returns true or false
     * 
     * @param o The object to search for.
     * @return A boolean value.
     */
    public boolean containsInPair(Object o) {
        for (Pair<Left,Right> pair : this) {
            if (o.equals(pair.getLeft()) || o.equals(pair.getRight())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function returns a list of all the left elements in the list of pairs.
     * 
     * @return A list of Left objects.
     */
    public List<Left> getLeftList() {
        List<Left> tmp = new ArrayList<Left>();
        for (Pair<Left,Right> pair : this) {
            tmp.add(pair.getLeft());
        }
        return tmp;
    }

    /**
     * This function returns a list of all the right elements in the list.
     * 
     * @return A list of the right elements of the pairs in the list.
     */
    public List<Right> getRightList() {
        List<Right> tmp = new ArrayList<Right>();
        for (Pair<Left,Right> pair : this) {
            tmp.add(pair.getRight());
        }
        return tmp;
    }
}
