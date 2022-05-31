package fr.mcstudio.util;

/**
 * This class is a generic class that can be used to create a pair of any two types
 */
public class Pair<Left,Right> {

    // A constructor.
    public Pair(Left l, Right r){
        this.l = l;
        this.r = r;
    }

    // Declaring a variable called `l` of type `Left`.
    private Left l;

    // Declaring a variable called `r` of type `Right`.
    private Right r;

    /**
     * This function returns the left element of the pair
     * 
     * @return The left element of the pair
     */

    public Left getLeft(){ 
        return this.l; 
    }

    /**
     * This function returns the right element of the pair
     * 
     * @return The right element of the pair
     */
    public Right getRight(){ 
        return this.r; 
    }

    /**
     * This function sets the left element of the pair
     * 
     * @param l The left element of the pair.
     */
    public void setLeft(Left l){ 
        this.l = l; 
    }

    /**
     * This function sets the right element of the pair
     * 
     * @param r The right element of the pair.
     */
    public void setRight(Right r){ 
        this.r = r; 
    }
}
