/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aceofspades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Wryxo
 */
public class VisCard {
    public Rectangle position;
    public String s;
    public String v;
    public boolean w;
    
    VisCard(int x, int y, String suit, String value, boolean visible) {
        position = new Rectangle(x, y, 50, 80);
        s = suit;
        v = value;
        w = visible;
    }
    
    public void draw(Graphics g){
        g.fillRect(position.x,position.y,50,80);
        g.setColor(Color.BLACK);
        g.drawRect(position.x-1,position.y-1,51,81);
        if (w) {
            switch (s) {
                case "hearts" : g.setColor(Color.RED);
                                g.drawString("♥", position.x+4, position.y+20);
                                g.drawString(v, position.x+4, position.y+40);
                                break;
                case "diamonds" : g.setColor(Color.RED);
                                g.drawString("♦", position.x+4, position.y+20);
                                g.drawString(v, position.x+4, position.y+40);
                                break;
                case "spades" : g.setColor(Color.BLACK);
                                g.drawString("♠", position.x+4, position.y+20);
                                g.drawString(v, position.x+4, position.y+40);
                                break;
                case "clubs" : g.setColor(Color.BLACK);
                                g.drawString("♣", position.x+4, position.y+20);
                                g.drawString(v, position.x+4, position.y+40);
                                break;
            }
        } else {
            try {
                BufferedImage back = ImageIO.read(new File("res/card_back.jpg"));
                g.drawImage(back, position.x, position.y, null);
            } catch (IOException ex) {
                
            }
        }
    }
    
    public void setPosition(int _x, int _y) {
        position.x = _x;
        position.y = _y;
    }
}
