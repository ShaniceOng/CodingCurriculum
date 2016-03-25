import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Rectangle;
 
public class Draw {
    
        public static void main(String[] args) {
                JFrame frame = new JFrame("Collision Detection");
                frame.setVisible(true);
                frame.setSize(800, 250);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new Paint());
        }
}
 
class Paint extends JPanel implements ActionListener {
        rect rect1;
        rect rect2;
        Timer time;
 
        public Paint() {
                rect1 = new rect(0, 0);
                rect2 = new rect(700, 150);
                time = new Timer(5, this);
                time.start();
        }
 
        @Override
        public void actionPerformed(ActionEvent e) {
                rect1.move();
                rect2.move();
                checkCollision();
                repaint();
        }
 
        @Override
        public void paint(Graphics g) {
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
 
                //collision = rectangle1.intersects(rectangle2);
                
                if (rectangle1.intersects(rectangle2)) {
                    
                        if((rect1.y >= rect2.y + 45) || rect2.y >= rect1.y + 45) {
                                rect1.ya*=-1;
                                rect2.ya*=-1;
                        }
                        if((rect1.x >= rect2.x + 95) || rect2.x >= rect1.x + 95) {
                                rect1.xa*=-1;
                                rect2.xa*=-1;
                       }
                }
        }
}