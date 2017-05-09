package platformer4d;

import java.awt.Point;

/**
 * The basic hypercube object
 * 
 * @author Perttu
 */
public class Tile {
    
    
    int x, y, z, w;

    public Tile(int x, int y, int z, int w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    
    public Point get2dProj(Dimension a, Dimension b){
        return new Point(getDim(a), getDim(b));
    }
    
    public int getDim(Dimension d){
        switch(d){
            case x:
                return x;
            case y:
                return y;
            case z:
                return z;
            case w:
                return w;
        }
        return 0;
    }
    
}
