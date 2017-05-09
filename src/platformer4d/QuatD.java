package platformer4d;

import sun.awt.AWTAutoShutdown;


/**
 * a quaternion object for all your placing needs
 * @author Perttu
 */
public class QuatD {
    
    
    double x, y, z, w;

    public QuatD(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
    }
    public QuatD(double i, double j, double k, double l){
        this.x = i;
        this.y = j;
        this.z = k;
        this.w = l;
    }
    public QuatD(Player p){
        QuatD pos = p.getPos();
        this.x = pos.x;
        this.y = pos.y;
        this.z = pos.z;
        this.w = pos.w;
    }
    
    public Quat getAsQuat(){
        return new Quat((int)Math.round(x), (int)Math.round(y), (int)Math.round(z), (int)Math.round(w));
    }
    
    public double getDim(Dimension d){
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
    public QuatD addToDimImmutable(Dimension d, double increment){
        QuatD q = copy();
        q.addToDim(d, increment);
        return q;
    }
    public void addToDim(Dimension d, double increment){
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
    public QuatD multiply(double d){
        x *= d;
        y *= d;
        z *= d;
        w *= d;
        return this;
    }
    public void add(QuatD other){
        x += other.x;
        y += other.y;
        z += other.z;
        w += other.w;
    }
    public double length(){
        return Math.sqrt(x*x + y*y + z*z + w*w);
    }
    public QuatD asUnit(){
        double l = length();
        return new QuatD(x / l, y / l, z / l, w / l);
    }
    public QuatD subtractImmutable(QuatD other){
        return new QuatD(x - other.x, y - other.y, z - other.z, w - other.w);
    }
    public double distSq(QuatD other){
        double dx = x - other.x;
        double dy = y - other.y;
        double dz = z - other.z;
        double dw = w - other.w;
        return dx * dx + dy * dy + dz * dz + dw * dw;
    }
    public double dot(QuatD other){
        return x*other.x + y*other.y + z*other.z + w*other.w;
    }
    public QuatD rounded(){
        return new QuatD((int)Math.round(x), (int)Math.round(y), (int)Math.round(z), (int)Math.round(w));
    }
    
    public void setDim(Dimension d, double value){
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
        if(!(other instanceof QuatD))
            return false;
        
        QuatD q = (QuatD)other;
        
        return x == q.x && y == q.y && z == q.z && w == q.w;
    }

    @Override
    public int hashCode(){
        int hash = 3;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.w) ^ (Double.doubleToLongBits(this.w) >>> 32));
        return hash;
    }

    @Override
    public String toString(){
        return "(" + x + "," + y + "," + z + "," + w + ")";
    }
    
    public QuatD copy(){
        return new QuatD(x, y, z, w);
    }
}
