import java.awt.Graphics;
import java.awt.Graphics2D;
import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Rectangle;
 
public class Draw {
    
        public static void main(String[] args)
        {
                JFrame frame = new JFrame("Collision Detection");
                frame.setVisible(true);
                frame.setSize(800, 250);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new Paint());
             
        }
}
 
class Paint extends Applet implements ActionListener, KeyListener {
        rect rect1;
        rect rect2;
        Timer time;
        int xa, ya;
        
        public Paint() 
        {
                this.addKeyListener(this);
                rect1 = new rect(0, 0);
                rect2 = new rect(700, 150);
                time = new Timer(10, this);
                time.start();
                
        }
         
        
 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
                rect1.move();
                rect2.move();
                checkCollision();
                repaint();
        }
 
        @Override
        public void paint(Graphics g) 
        {

                super.paint(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.BLUE);
                g2d.fillRect(rect1.x, rect1.y, 100, 50);
                g2d.setColor(Color.ORANGE);
                g2d.fillRect(rect2.x, rect2.y, 100, 50);
                
        }
 
        public void checkCollision() {
                Rectangle rectangle1 = rect1.getBounds();
                Rectangle rectangle2 = rect2.getBounds();
                
                if (rectangle1.intersects(rectangle2)) 
                {
                   
                        if(Math.abs(rect1.y - rect2.y) >= 44) 
                        {
                                rect1.ya*=-1;
                                rect2.ya*=-1;
                        }
                        if(Math.abs(rect1.x - rect2.x) >= 94) 
                       {
                                rect1.xa*=-1;
                                rect2.xa*=-1;
                       }
                }
        }

    @Override
    public void keyTyped(KeyEvent e) 
    {
         
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        xa = rect1.xa;
        ya = rect1.ya;
        checkCollision();
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            rect1.xa = 2;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            rect1.xa = -2;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
           rect1.ya =  -2;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
           rect1.ya = 2;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
  
    }
}