package fr.mcstudio.util;

public class Triplet<Left,Middle,Right> {
    private Left l;
    private Middle m;
    private Right r;

    public Triplet(Left l, Middle m, Right r){
        this.l = l;
        this.m = m;
        this.r = r;
    }

    public Left getLeft(){ 
        return this.l; 
    }

    public Middle getMiddle() {
        return this.m;
    }

    public Right getRight(){ 
        return this.r; 
    }

    public void setLeft(Left l){ 
        this.l = l; 
    }

    public void setMiddle(Middle m) {
        this.m = m;
    }

    public void setRight(Right r){ 
        this.r = r; 
    }
}