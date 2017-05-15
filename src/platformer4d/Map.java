package platformer4d;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;



public class Map {

    private static final Quat size = new Quat(12, 10, 11, 8);
    
    private final boolean[][][][] map = new boolean[size.x][size.y][size.z][size.w];
    
    private final ArrayList<Quat> stars = new ArrayList();
    
    public Map(){
        
        Random r = new Random();
        
        //init randomly, about 1/4 as false
        for(int i = 0; i < size.x; i++)
            for(int j = 0; j < size.y; j++)
            for(int k = 0; k < size.z; k++)
            for(int l = 0; l < size.w; l++){
                boolean val;
                if(i == 0 || j == 0 || k == 0 || l == 0 ||
                        i == size.x - 1 || j == size.y - 1 || k == size.z - 1 || l == size.w - 1)
                    val = true;
                else
                    val = r.nextFloat() < 0.60f;
                map[i][j][k][l] = val;
                
                if(!val && r.nextFloat() < 0.02f){
                    stars.add(new Quat(i, j, k, l));
                    //TODO add a route to each one
                }
            }
        
        
    }
    
    public ArrayList<Quat> getStars(){
        return stars;
    }
    public void eatStar(Game game){
        if(stars.remove(game.player.getPos().getAsQuat())){
            if(stars.isEmpty()){
                JOptionPane.showMessageDialog(null, "Congrats! You found all stars!\nYou clicked " + game.clickCounter + " times");
            }else{
                game.refreshTitle();
            }
        }
        
    }
    
    public boolean getTile(Quat q){
        try{
            return map[q.x][q.y][q.z][q.w];
        }catch(ArrayIndexOutOfBoundsException e){
            return true;
        }
    }
    
    /**
     * get the tile that is at player's position + i in dimension x and j in dimension y
     * 
     * @param x first dimension
     * @param y second dimension
     * @param i distance in first dim
     * @param j distance in second dim
     * @param player player object
     * @return value of the tile in question
     */
    public boolean getTile(Dimension x, Dimension y, int i, int j, Player player){
        Quat q = new Quat(player);
        q.setDim(x, i);
        q.setDim(y, j);
        return getTile(q);
    }
    
    public boolean hasStar(Dimension x, Dimension y, int i, int j, Player player){
        Quat q = new Quat(player);
        q.setDim(x, i);
        q.setDim(y, j);
        return stars.contains(q);
    }
    public void toggleTile(Dimension x, Dimension y, int i, int j, Player player){
        Quat q = new Quat(player);
        q.setDim(x, i);
        q.setDim(y, j);
        toggleTile(q);
    }
    
    public void toggleTile(Quat q){
        map[q.x][q.y][q.z][q.w] = !map[q.x][q.y][q.z][q.w];
    }
    public void setTile(Quat q, boolean b){
        map[q.x][q.y][q.z][q.w] = b;
    }
    
    public int getSize(Dimension d){
        return size.getDim(d);
    }
    
}
