package fr.mcstudio.util;

public class Pair<Left,Right> {
    private Left l;
    private Right r;

    public Pair(Left l, Right r){
        this.l = l;
        this.r = r;
    }

    public Left getLeft(){ 
        return this.l; 
    }

    public Right getRight(){ 
        return this.r; 
    }

    public void setL(Left l){ 
        this.l = l; 
    }

    public void setR(Right r){ 
        this.r = r; 
    }
}
