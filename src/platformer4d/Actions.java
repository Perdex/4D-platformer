package platformer4d;

import java.awt.event.*;
import javax.swing.JPanel;

public class Actions implements KeyListener{

    private final Game game;
    private final JPanel parent;
    
    public Actions(Game game, JPanel parent){
        this.game = game;
        this.parent = parent;
    }
    
    
    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                game.pressed[0] = true;
                break;
            case KeyEvent.VK_S:
                game.pressed[1] = true;
                break;
            case KeyEvent.VK_A:
                game.pressed[2] = true;
                break;
            case KeyEvent.VK_D:
                game.pressed[3] = true;
                break;
            case KeyEvent.VK_E:
                game.pressed[4] = true;
                break;
            case KeyEvent.VK_Q:
                game.pressed[5] = true;
                break;
            case KeyEvent.VK_R:
                game.pressed[6] = true;
                break;
            case KeyEvent.VK_F:
                game.pressed[7] = true;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                game.pressed[0] = false;
                break;
            case KeyEvent.VK_S:
                game.pressed[1] = false;
                break;
            case KeyEvent.VK_A:
                game.pressed[2] = false;
                break;
            case KeyEvent.VK_D:
                game.pressed[3] = false;
                break;
            case KeyEvent.VK_E:
                game.pressed[4] = false;
                break;
            case KeyEvent.VK_Q:
                game.pressed[5] = false;
                break;
            case KeyEvent.VK_R:
                game.pressed[6] = false;
                break;
            case KeyEvent.VK_F:
                game.pressed[7] = false;
                break;
        }
    }

    
    
}
