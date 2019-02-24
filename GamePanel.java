package pong3;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    Player player = new Player();
    Ball ball = new Ball();
    
    
    public GamePanel() {
        Timer time = new Timer(10, this);
        time.start();

        this.addKeyListener(this);
        this.setFocusable(true);        
 
    }    
   
    private void update() {
        player.update();
        ball.update();
        
        ball.checkCollisionWith(player);
   
        ball.hitWall();

    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Pong.WINDOW_WIDTH, Pong.WINDOW_HEIGHT);

        player.paint(g);
        ball.paint(g);

    }

    public Ball getBall() {
        return ball;
    }

    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setXVelocity(-5);
            if (player.getX() < 30) {
                player.setXVelocity(0);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setXVelocity(5);
            if (player.getX() + 40 > Pong.WINDOW_WIDTH - 28) {
                player.setXVelocity(0);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            player.setXVelocity(0);

        }
    }

    public void keyTyped(KeyEvent e) {
    }
}
