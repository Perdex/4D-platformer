package platformer4d;


/**
 * a quaternion object for all your placing needs
 * @author Perttu
 */
public class Quat {
    
    
    int x, y, z, w;

    public Quat(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
    }
    public Quat(int i, int j, int k, int l){
        this.x = i;
        this.y = j;
        this.z = k;
        this.w = l;
    }
    public Quat(Player p){
        Quat pos = p.getPos().getAsQuat();
        this.x = pos.x;
        this.y = pos.y;
        this.z = pos.z;
        this.w = pos.w;
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
        return -1;
    }
    public Quat addToDimImmutable(Dimension d, int increment){
        Quat q = copy();
        q.addToDim(d, increment);
        return q;
    }
    public void addToDim(Dimension d, int increment){
        switch(d){
            case x:
                x += increment;
                break;
            case y:
                y += increment;
                break;
            case z:
                z += increment;
                break;
            case w:
                w += increment;
                break;
        }
    }
    public void setDim(Dimension d, int value){
        switch(d){
            case x:
                x = value;
                break;
            case y:
                y = value;
                break;
            case z:
                z = value;
                break;
            case w:
                w = value;
                break;
        }
    }
    
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Quat))
            return false;
        
        Quat q = (Quat)other;
        
        return x == q.x && y == q.y && z == q.z && w == q.w;
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 89 * hash + this.x;
        hash = 89 * hash + this.y;
        hash = 89 * hash + this.z;
        hash = 89 * hash + this.w;
        return hash;
    }
    
    @Override
    public String toString(){
        return "(" + x + "," + y + "," + z + "," + w + ")";
    }
    
    public Quat copy(){
        return new Quat(x, y, z, w);
    }
}
