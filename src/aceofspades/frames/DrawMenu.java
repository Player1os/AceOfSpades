/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aceofspades.frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Wryxo
 */
public class DrawMenu implements DrawStrategy{
    Frame _frame;
    
    public Rectangle startButton;
    public boolean hoverStartButton;
    
    DrawMenu (Frame f) {
        _frame = f;
    }
    
    @Override
    public void draw(Graphics g, int width, int height) {
        startButton = new Rectangle(width/2-50, 100, 100, 25);
        if (!hoverStartButton) {
            g.setColor(Color.CYAN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);
        g.setColor(Color.BLACK);
        g.drawString("Options", startButton.x+20, startButton.y+17);
        
        _frame.repaint();
    }
    
    void setHover(boolean x) {
        hoverStartButton = x;
    }
}
