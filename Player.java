package pong3;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

    private int x = Pong.WINDOW_WIDTH / 2;
    private int xVelocity = 0;
    private int height = 10;
    private int width = 100;
    
    public int level = 1;

    public Player() {
    }

    public void update() {
        x = x + xVelocity;
    }

    public void paint(Graphics g) {
    	g.setColor(Color.green);
    	
        if (level == 1) {
        	g.fillRect(x, 300, 100, height);

        }else if (level == 2) {
        	width = 60;
        	g.fillRect(x, 300, 60, height);

        }else if (level == 3) {
        	width = 20;
        	g.fillRect(x, 300, 20, height);

        }
    	
 
    }

    public void setXVelocity(int speed) {
        xVelocity = speed;
    }

    public int getY() {
        return 300;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    //method for changing level
    public void level1() {
    	level = 1;
   
    }
    public void level2() {
    	level = 2;

    }
    public void level3() {
    	level = 3;
    
    }
}
