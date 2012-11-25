/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aceofspades.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Wryxo
 */
public class DrawMenu implements DrawStrategy{
    Frame _frame;
    
    public Rectangle startButton;
    public Rectangle optionsButton;
    public boolean hoverOptionsButton;
    public boolean hoverStartButton;
    
    DrawMenu (Frame f) {
        _frame = f;
    }
    
    @Override
    public void draw(Graphics g, int width, int height) {
        
        /*
         * Main Menu Title
         */
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 26));
        g.drawString("Main Menu",width/2-73, 50);
        
        /*
         * Single Player Button
         */
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        startButton = new Rectangle(width/2-50, 100, 100, 25);
        if (!hoverStartButton) {
            g.setColor(Color.CYAN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);
        g.setColor(Color.BLACK);
        g.drawString("Single Player", startButton.x+20, startButton.y+17);
        
        /*
         * Options Button
         */
        optionsButton = new Rectangle(width/2-50, 150, 100, 25);
        if (!hoverOptionsButton) {
            g.setColor(Color.CYAN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g.fillRect(optionsButton.x, optionsButton.y, optionsButton.width, optionsButton.height);
        g.setColor(Color.BLACK);
        g.drawString("Options", optionsButton.x+30, optionsButton.y+17);
        
        _frame.repaint();
    }
}
