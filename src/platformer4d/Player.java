package platformer4d;

import java.util.Random;



public class Player {

    private QuatD pos;
    private QuatD vel;
    
    private static final double playerSpeed = 0.001;
    
    public Player(Map map){
        Random r = new Random();
        do{
            pos = new QuatD(r.nextInt(map.getSize(Dimension.x) - 2) + 1,
                        r.nextInt(map.getSize(Dimension.y) - 2) + 1,
                        r.nextInt(map.getSize(Dimension.z) - 2) + 1,
                        r.nextInt(map.getSize(Dimension.w) - 2) + 1);
        }while(map.getTile(pos.getAsQuat()));
        vel = new QuatD();
    }
    
    public void moveCommand(Dimension d, int i, Game game){
        
        vel.addToDim(d, i * playerSpeed);
        
    }
    
    public void move(Game game){
        
        pos.add(vel);
        
        
        //gravity
        //vel.addToDim(Dimension.y, 0.0002);
        //vel.addToDim(Dimension.w, 0.0002);
        
        vel.multiply(0.98);
        
        checkCollision(game, Dimension.x);
        checkCollision(game, Dimension.y);
        checkCollision(game, Dimension.z);
        checkCollision(game, Dimension.w);
    }
    
    private void checkCollision(Game game, Dimension d){
        checkCollision2(game, pos.addToDimImmutable(d, 1).rounded());
        checkCollision2(game, pos.addToDimImmutable(d, -1).rounded());
    }
    private void checkCollision2(Game game, QuatD q){
        double collDist = 1;
        try{
            if(game.map.getTile(q.getAsQuat())){
                double distsq = q.distSq(pos);
                if(distsq < (collDist*collDist)){
                    //collision!
                    
                    double dist = Math.sqrt(distsq);
                    
                    QuatD dpos = pos.subtractImmutable(q);
                    
                    //move to safe distance
                    pos.add(dpos.multiply(- 1 + collDist / dist));
                    
                    dpos = dpos.asUnit();
                    
                    double relativeV = vel.dot(dpos);
                    
                    vel.add(dpos.multiply(-1.5*relativeV));
                    
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            
        }
    }
    
    public double getPos(Dimension d){
        return pos.getDim(d);
    }
    public int getPosInt(Dimension d){
        return pos.getAsQuat().getDim(d);
    }
    public QuatD getPos(){
        return pos;
    }
//    public int getDimInt(Quat.dimension d){
//        switch(d){
//            case x:
//                return (int)Math.round(pos.x);
//            case y:
//                return (int)Math.round(pos.y);
//            case z:
//                return (int)Math.round(pos.z);
//            case w:
//                return (int)Math.round(pos.w);
//        }
//        return 0;
//    }
    
}
