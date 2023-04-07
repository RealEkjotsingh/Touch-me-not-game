package G1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ASWDGame extends JPanel implements KeyListener {

    private static final long serialVersionUID = 1L;
    private int x, y; // position of the blue square
    private int redX, redY; // position of the red square
    private int greenX, greenY; // position of the green square
    private int points; // number of points
    private Random rand;

    /**
     * Constructor for the game
     */
    public ASWDGame() {
        rand = new Random();
        x = rand.nextInt(250);
        y = rand.nextInt(250);
        redX = rand.nextInt(250);
        redY = rand.nextInt(250);
        greenX = rand.nextInt(250);
        greenY = rand.nextInt(250);
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(this);
        points = 0;
    }

    /**
     * Draws the blue, red, and green squares on the panel
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 20, 20);
        g.setColor(Color.RED);
        g.fillRect(redX, redY, 20, 20);
        g.setColor(Color.GREEN);
        g.fillRect(greenX, greenY, 20, 20);
        g.setColor(Color.WHITE);
        g.drawString("Points: " + points, 10, 20);
    }

    /**
     * Handles key events
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            x -= 10;
        } else if (key == KeyEvent.VK_RIGHT) {
            x += 10;
        } else if (key == KeyEvent.VK_UP) {
            y -= 10;
        } else if (key == KeyEvent.VK_DOWN) {
            y += 10;
        }
        else if (key == KeyEvent.VK_A) {
            x -= 10;
        } else if (key == KeyEvent.VK_D) {
            x += 10;
        } else if (key == KeyEvent.VK_W) {
            y -= 10;
        } else if (key == KeyEvent.VK_S) {
            y += 10;
        }

        // Check if blue square touches the red square
        Rectangle blueRect = new Rectangle(x, y, 20, 20);
        Rectangle redRect = new Rectangle(redX, redY, 20, 20);
        
        if (blueRect.intersects(redRect)) {
            points += 10; // Increase points by 5 if blue touches red
            redX = rand.nextInt(350);
            redY = rand.nextInt(350);
            greenX = rand.nextInt(350);
            greenY = rand.nextInt(350);
        }

        // Check if blue square touches the green square
        Rectangle greenRect = new Rectangle(greenX, greenY, 20, 20);
        if (blueRect.intersects(greenRect) && points == 0) {
            JOptionPane.showMessageDialog(null, "Game Over You Scored: "+ points +" points Congrats LOSER" );
            System.exit(0);
        } else if (blueRect.intersects(greenRect)) {
            JOptionPane.showMessageDialog(null, "Game Over You Scored: "+ points +" points Congrats!" );
            System.exit(0);
        }
        if(points>=999) {
        	JOptionPane.showMessageDialog(null, "Conratulations you Won the Game with: "+ points +" points" );
            System.exit(0);
        }

        repaint(); // Redraw the squares
    }
    
    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("touch me not");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ASWDGame game = new ASWDGame();
        frame.add(game);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
