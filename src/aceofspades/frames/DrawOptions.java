package aceofspades.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Wryxo
 */
public class DrawOptions implements DrawStrategy{
    Frame _frame;
    
    public Rectangle menuButton;
    public Rectangle resolutionButton;
    public int res_width;
    public int res_height;
    public boolean changedResolution;
    public boolean hoverMenuButton;
    public boolean hoverResolutionButton;
    
    /**
     * 
     * @param f 
     */
    public DrawOptions (Frame f) {
        _frame = f;
        res_width = f._width;
        res_height = f._height;
    }
    
    /**
     * 
     * @param g
     * @param width
     * @param height 
     */
    @Override
    public void draw(Graphics g, int width, int height) {
        
        /*
         * Options Title
         */
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 26));
        g.drawString("Options",width/2-73, 50);
        
        /*
         * Main Menu Button
         */
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        menuButton = new Rectangle(width/2-50, 300, 100, 25);
        if (!hoverMenuButton) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g.fillRect(menuButton.x, menuButton.y, menuButton.width, menuButton.height);
        g.setColor(Color.BLACK);
        g.drawString("Main Menu", menuButton.x+20, menuButton.y+17);
        
        /*
         * Change Resolution Button
         */
        resolutionButton = new Rectangle(width/2-50, 100, 100, 25);
        if (!hoverResolutionButton) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g.fillRect(resolutionButton.x, resolutionButton.y, resolutionButton.width, resolutionButton.height);
        g.setColor(Color.BLACK);
        g.drawString(res_width + " x " + res_height, resolutionButton.x+20, resolutionButton.y+17);
        
        _frame.repaint();
    }
    
    /**
     * 
     */
    public void changeResolution () {

        switch (res_width) {
            case 640 :  res_width = 800;
                        res_height = 600;
                        break;
            case 800 :  res_width = 1024;
                        res_height = 768;
                        break;
            case 1024 :  res_width = 1280;
                        res_height = 720;
                        break;
            case 1280 :  res_width = 1600;
                        res_height = 1200;
                        break;
            case 1600 :  res_width = 1920;
                        res_height = 1080;
                        break;
            case 1920 :  res_width = 640;
                        res_height = 480;
                        break;
        }
        changedResolution = true;
    }
}
