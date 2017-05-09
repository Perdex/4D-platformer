package platformer4d;

import javax.swing.JFrame;

public class Game implements Runnable{

    public final Map map;
    public final Player player;
    private final JFrame frame;
    
    public boolean pressed[] = {false, false, false, false, false, false, false, false};
    
    public int clickCounter = 0;

    public Game(JFrame frame){
        this.frame = frame;
        map = new Map();
        player = new Player(map);
        refreshTitle();
        unPause();
    }
    
    public void refreshTitle(){
        frame.setTitle("How many dimension would you like to have? Stars left: " + map.getStars().size());
    }
    
    private static boolean paused = false;
    
    public static void pause(){
        paused = true;
    }
    public static void unPause(){
        paused = false;
    }
    
    
    
    @Override
    public void run(){
        long waitTime = 5;
        int count = 0;
        
        while(!paused){
            
            long t = System.currentTimeMillis();
            
            checkPresses();
            
            player.move(this);
            
            map.eatStar(this);
            
            long toWait = t - System.currentTimeMillis() + waitTime;
            
            if(toWait > 0)
                try{
                    Thread.sleep(toWait);
                }catch(InterruptedException e){
                    System.err.println("interrupted");
                }
            
            if(count % 4 == 0)
                frame.repaint();
            count++;
        }
    }
    
    
    private void checkPresses(){
        
        if(pressed[0])
            player.moveCommand(Dimension.y, -1, this);
        if(pressed[1])
            player.moveCommand(Dimension.y, 1, this);
        if(pressed[2])
            player.moveCommand(Dimension.x, -1, this);
        if(pressed[3])
            player.moveCommand(Dimension.x, 1, this);
        if(pressed[4])
            player.moveCommand(Dimension.z, 1, this);
        if(pressed[5])
            player.moveCommand(Dimension.z, -1, this);
        if(pressed[6])
            player.moveCommand(Dimension.w, -1, this);
        if(pressed[7])
            player.moveCommand(Dimension.w, 1, this);
        
    }
}
