import java.applet.Applet;

import java.awt.Rectangle;
/**
 *
 * @author Keshav Alvi
 */
public class rect { 
        int x;
        int y;
        int xa = 1;
        int ya = 1;
    
        public rect(int x, int y) {
                this.x = x;
                this.y = y;
     
        }
        
        
    
        void move() {
		if (x + xa < 0)
			xa = 1;
		if (x + xa > 700)
			xa = -1;
		if (y + ya < 0)
			ya = 1;
		if (y + ya > 185)
			ya = -1;
		x = x + xa;
		y = y + ya;
        }
        public Rectangle getBounds() {
                return (new Rectangle (x, y, 100, 50));
        }
}