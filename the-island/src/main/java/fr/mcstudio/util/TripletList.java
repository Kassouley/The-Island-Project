package fr.mcstudio.util;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")

/**
 * The TripletList class is a list of Triplet objects
 */
public class TripletList<Left,Middle,Right> 
		extends ArrayList<Triplet<Left,Middle,Right>> {
    
    // It's a constructor.
    public TripletList() {
        super();
    }

    /**
     * If the object is equal to the left, middle, or right of the triplet, return true
     * 
     * @param o The object to search for
     * @return A boolean value.
     */
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

    /**
     * Returns a list of the left elements of the triplets
     * 
     * @return A list of the left elements of the triplets in the list.
     */
    public List<Left> getLeftList() {
        List<Left> tmp = new ArrayList<Left>();
        for (Triplet<Left,Middle,Right> triplet : this) {
            tmp.add(triplet.getLeft());
        }
        return tmp;
    }

    /**
     * Returns a list of the middle elements of the triplets
     * 
     * @return A list of Middle objects.
     */
    public List<Middle> getMiddleList() {
        List<Middle> tmp = new ArrayList<Middle>();
        for (Triplet<Left,Middle,Right> triplet : this) {
            tmp.add(triplet.getMiddle());
        }
        return tmp;
    }

   /**
    * Returns a list of the right elements of the triplets
    * 
    * @return A list of the right elements of the triplets in the list.
    */
    public List<Right> getRightList() {
        List<Right> tmp = new ArrayList<Right>();
        for (Triplet<Left,Middle,Right> triplet : this) {
            tmp.add(triplet.getRight());
        }
        return tmp;
    }
}
