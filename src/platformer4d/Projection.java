package platformer4d;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * 
 * provides the graphics for the game
 * 
 * @author Perttu
 */
public class Projection extends JPanel implements MouseListener{

    private static final int tileSize = 30;
    
    private final Game game;
    private final Dimension x, y, not1, not2;
    
    private final boolean wide = false;
    
    public Projection(Dimension x, Dimension y, Dimension not1, Dimension not2, Game game){
        super(new BorderLayout(5, 0));
        
        this.game = game;
        this.x = x;
        this.y = y;
        this.not1 = not1;
        this.not2 = not2;
        
        JTextField title = new JTextField(x.asString + y.asString);
        title.setEditable(false);
        title.setFocusable(false);
        title.setBorder(null);
        
        JPanel mid = new JPanel();
        mid.add(title);
        add(mid, BorderLayout.NORTH);
        
        canvas canvas = new canvas();
        canvas.setPreferredSize(new java.awt.Dimension(tileSize * game.map.getSize(x), tileSize * game.map.getSize(y)));
        
        mid = new JPanel();
        mid.add(canvas);
        add(mid, BorderLayout.CENTER);
        //canvas.addMouseListener(this);
    }
    
    private class canvas extends JPanel{
        @Override
        public void paint(Graphics g){

            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.darkGray.darker());

//            for(int i = 0; i <= game.map.getSize(x); i++){
//                g.drawLine(i * tileSize, 0, i * tileSize, getHeight());
//            }
//            for(int j = 0; j <= game.map.getSize(y); j++){
//                g.drawLine(0, j * tileSize, getWidth(), j * tileSize);
//            }

            g.setColor(Color.darkGray.darker());
            
            double distFromPlaneSq = Math.pow((game.player.getPos(not1) - game.player.getPosInt(not1)), 2) + 
                                    Math.pow((game.player.getPos(not2) - game.player.getPosInt(not2)), 2);
            
            for(int i = 0; i < game.map.getSize(x); i++){
                for(int j = 0; j < game.map.getSize(y); j++){
                    boolean color = false;
                    
                    if(wide){
                        //TODO
                    }else if(game.map.getTile(x, y, i, j, game.player)) {
                        //not wide: only check the player's position
                        color = true;
                    }

                    int size = (int)(2 * tileSize * Math.sqrt(0.25 - distFromPlaneSq));
                    
                    //color the tile
                    if(color)
                        g.fillOval(i * tileSize + (tileSize - size) / 2, j * tileSize + (tileSize - size) / 2, size, size);
                    
                    
                    if(game.map.hasStar(x, y, i, j, game.player)){
                        g.setColor(Color.yellow);
                        
                        g.fillOval(i * tileSize + (tileSize - size) / 2, j * tileSize + (tileSize - size) / 2, size, size);
                        g.setColor(Color.darkGray.darker());
                    }
                }
            }



            g.setColor(Color.green);
            double playerSize = 0.95;
            g.fillOval((int)((game.player.getPos(x) * tileSize + 1)), 
                    (int)((game.player.getPos(y) * tileSize + 1)),
                    (int)(tileSize * playerSize), (int)(tileSize * playerSize));

        }
    }
    @Override
    public void mouseClicked(MouseEvent e){
        game.map.toggleTile(x, y, e.getX() / tileSize, e.getY() / tileSize, game.player);
        game.clickCounter++;
        getParent().repaint();
    }
    
    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}
}
