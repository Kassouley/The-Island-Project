package fr.mcstudio.util;

/**
 * It's a class that allows you to store three variables of different types in one variable
 */
public class Triplet<Left,Middle,Right> {

    // It's a constructor.
    public Triplet(Left l, Middle m, Right r){
        this.l = l;
        this.m = m;
        this.r = r;
    }

    // It's the left variable of the triplet.
    private Left l;
    
    // It's the middle variable of the triplet.
    private Middle m;
    
    // It's the right variable of the triplet.
    private Right r;

    /**
     * Returns the left element of the triplet
     * 
     * @return The left element of the triplet
     */
    public Left getLeft(){ 
        return this.l; 
    }

    /**
     * Returns the middle element of the triplet
     * 
     * @return The middle element of the triplet
     */
    public Middle getMiddle() {
        return this.m;
    }

    /**
     * Returns the right element of the triplet
     * 
     * @return The right element of the triplet
     */
    public Right getRight(){ 
        return this.r; 
    }

    /**
     * Sets the left element of the triplet.
     * 
     * @param l The left element to set.
     */
    public void setLeft(Left l){ 
        this.l = l; 
    }

    /**
     * Sets the middle element of the triplet.
     * 
     * @param m The middle element to set.
     */
    public void setMiddle(Middle m) {
        this.m = m;
    }

    /**
     * Sets the right element of the triplet.
     * 
     * @param r The right element to set.
     */
    public void setRight(Right r){ 
        this.r = r; 
    }
}