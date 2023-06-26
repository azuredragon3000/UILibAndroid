package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author PCH1HC
 *
 */
@SuppressWarnings("serial")
public class SimulateWorld extends JPanel {
    private Car car1;
    private int fps;
    private long delay;
    
    /**
     * 
     */
    public SimulateWorld() {
        setPreferredSize(new Dimension(400, 400));
        car1 = new Car(15,15,10,0.05,0.1);
        fps = 60;
        delay = 1000/fps;
    }

    /**
     * @param elapsedTime
     */
    @SuppressWarnings("javadoc")
    public void moveItems(long elapsedTime) {
       car1.move(elapsedTime,getWidth(),getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);   
        g.setColor(Color.RED);
        g.fillOval((int)car1.getPosx(), (int)car1.getPosy(), car1.getSize(), car1.getSize());    
    }

    /**
     * @param args
     */
    @SuppressWarnings("javadoc")
    public static void main(String[] args) {
        JFrame frame = new JFrame("Square Move");
        SimulateWorld squareMove = new SimulateWorld();
        frame.getContentPane().add(squareMove);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);        
        long startTime = System.currentTimeMillis();
        long lastUpdateTime = startTime;

        while (true) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastUpdateTime;

            if (elapsedTime >= squareMove.delay) {
                lastUpdateTime = currentTime;
                squareMove.moveItems(elapsedTime);
                squareMove.repaint();
            } else {
                try {
                    Thread.sleep(squareMove.delay - elapsedTime);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
