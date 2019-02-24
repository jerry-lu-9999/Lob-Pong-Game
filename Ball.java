package pong3;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Ball{

    private int x = Pong.WINDOW_WIDTH / 2;
    private int y = Pong.WINDOW_HEIGHT / 2;
    private int xVelocity = 1;
    private double yVelocity = -0.5;
    private double time = 0;
    private double count = 1;
    private int size = 5;
    private int playerScore = 5 ;
    private int remainingTime = 21;
  
    public int countStop = 1;
    public int level = 1;
    public Player player = new Player();
    
    public  void chooseLevel(int level) {
   	  switch(level) {
    		case 1:
    			remainingTime = 20;
    			player.level1();
    			level = 1;
    			update();
    			break;
    		case 2:
    			remainingTime = 30;
    			player.level2();
    			level = 2;
    			update();
    			break;
    		case 3:
    			remainingTime = 40;
    			player.level3();
    			level = 3;
    			update();
    			break;
    		}
    }

   	  
    Timer countdown =  new Timer(1000, new ActionListener() {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		if (remainingTime <= 0 || playerScore == 0) {
    			((Timer)e.getSource()).stop();
    			JOptionPane.showMessageDialog(null, "Game Over", "Sorry", JOptionPane.WARNING_MESSAGE);
    			System.exit(0);
    		} else {
    			remainingTime--;
    		}
    	}
    });
    
    
    public void update() {
    	
        countdown.start();
    			
	    	if (remainingTime > 0 && playerScore > 0) 
	    	{
		        x = x + xVelocity;
		        
		        yVelocity += 0.5 * time;
		        y = (int) (y + yVelocity);
		
		        if (y < 0) {
		            yVelocity = 4;
		            
		            time = 0;
		  
		        } else if (y + size > Pong.WINDOW_HEIGHT - 25) {
		            count += 0.2;
		        	yVelocity = -4 * count;
		          
		        	playerScore = playerScore - 1;
		            
		            time = 0;
		        }
		
		        if (x < 0) {
		            xVelocity = 4;
		        } else if (x + size > Pong.WINDOW_WIDTH - 28) {
		            xVelocity = -4;
		        }
		        
		        time = time + 0.01;
	    	} else if (remainingTime == 0 && playerScore > 0) {
	    		
	    		level++;
	    		chooseLevel(level);	
	    		
	    		if (level == 4) {
	    			JOptionPane.showMessageDialog(null, "You Win! Thank you for playing!", "Winner winner chicken dinner", JOptionPane.PLAIN_MESSAGE);
	    			System.exit(0);
	    		}
	    	}
    }

    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(x, y, size, size);
        g.drawString(toPlayer(), 5, 15);
        g.drawString(toCountDown(), 270, 15);
        g.drawString(toLevel(), 150, 15);
 
    }

    private void reverseDirection() {
        xVelocity = -xVelocity;
        time = 0;
    }

    private void reverseDirectionY() {
        yVelocity = -yVelocity;
        time = 0;
    }

    public void checkCollisionWith(Player player) {
        if (this.y > player.getY() && this.y < player.getY() + player.getHeight()) {
            if (this.x > player.getX() && this.x < player.getX() + player.getWidth()) {
                //ball has collided with player
                reverseDirectionY();
                
            }
        }
    }

    public void hitWall() {
        if (this.x < 30) {
            reverseDirection();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPlayerScore() {
        return playerScore;
    }
       
    
    public int getRemainingTime() {
    	return remainingTime;
    }

    public int getLevel() {
    	if (remainingTime == 20) {
    		return level = 1;
    	} else if (remainingTime == 30) {
    		return level = 2;
    	} else if (remainingTime == 40) {
    		return level = 3;
    	}
    	return level;
    }

    public String toPlayer() {
        String retVal = "";
        retVal = "Player Score: " + playerScore;
        return retVal;
    }
    
    public String toCountDown() {
    	String retTime = "";
    	retTime = "Remaining time: " + remainingTime;
    	return retTime;
    }
    
    public String toLevel() {
    	String retLevel = "";
    	retLevel = "Level: " + level;
    	return retLevel;
    
    }

}
