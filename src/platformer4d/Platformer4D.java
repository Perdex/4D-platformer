package platformer4d;

import java.awt.Toolkit;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

/**
 * main method of the project
 *
 * @author Perttu
 */
public class Platformer4D extends JPanel{

    
    private Platformer4D(JFrame frame){
        super(new GridLayout(2, 3, 10, 10));
        
        init(frame);
        
    }
    
    private void init(JFrame frame){
        Game.pause();
        
        removeAll();
        
        Game game = new Game(frame);
        
        Actions actions = new Actions(game, this);
        
        addKeyListener(actions);
        setFocusable(true);
        requestFocus();
        
        createProjection(Dimension.x, Dimension.y, Dimension.z, Dimension.w, game);
        createProjection(Dimension.z, Dimension.w, Dimension.x, Dimension.y, game);
        createProjection(Dimension.x, Dimension.z, Dimension.y, Dimension.w, game);
        createProjection(Dimension.y, Dimension.w, Dimension.x, Dimension.z, game);
        createProjection(Dimension.x, Dimension.w, Dimension.y, Dimension.z, game);
        createProjection(Dimension.y, Dimension.z, Dimension.x, Dimension.w, game);
        
        new Thread(game).start();
    }
    
    private void createProjection(Dimension x, Dimension y, Dimension not1, Dimension not2, Game game){
        JPanel panel = new Projection(x, y, not1, not2, game);
        add(panel);
    }
    
    
    public static void main(String[] args){
        JFrame frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Platformer4D root = new Platformer4D(frame);
        
        //frame.setJMenuBar(root.makeMenuBar(frame));
        
        
        frame.add(root);
        frame.pack();
        
        
        //position frame to the center of screen
        java.awt.Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((int)(screen.getWidth() / 2 - frame.getWidth() / 2), 
                        (int)(screen.getHeight() / 2 - frame.getHeight() / 2),
                        frame.getWidth(), frame.getHeight());
        frame.setVisible(true);
        
        
        JOptionPane.showMessageDialog(frame, "Welcome to the 4-dimensional star-searching adventure!\nUse WS, AD, QE and RF to move.");
        
    }
    
    private JMenuBar makeMenuBar(JFrame frame){
        JMenuBar bar = new JMenuBar();
        
        
        JMenuItem item = new JMenu("New game");
        item.addActionListener((ActionEvent e) -> {
            System.out.println("asdf");
            init(frame);
        });
        bar.add(item);
        
        
        return bar;
    }
    
}
