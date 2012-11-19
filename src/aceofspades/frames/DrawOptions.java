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
public class DrawOptions implements DrawStrategy{
    Frame _frame;
    
    public Rectangle menuButton;
    public boolean hoverMenuButton;
    
    DrawOptions (Frame f) {
        _frame = f;
    }
    
    @Override
    public void draw(Graphics g, int width, int height) {
        menuButton = new Rectangle(width/2-50, 200, 100, 25);
        if (!hoverMenuButton) {
            g.setColor(Color.CYAN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g.fillRect(menuButton.x, menuButton.y, menuButton.width, menuButton.height);
        g.setColor(Color.BLACK);
        g.drawString("Main Menu", menuButton.x+20, menuButton.y+17);
        
        _frame.repaint();
    }
}
