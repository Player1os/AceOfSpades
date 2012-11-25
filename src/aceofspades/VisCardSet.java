/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aceofspades;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Wryxo
 */
public class VisCardSet {
    public Rectangle position;
    
    VisCardSet(int _x, int _y) {
        position = new Rectangle(_x, _y, 50, 80);
    }
    
    public void draw(Graphics g){
        g.fillRect(position.x, position.y, position.width, position.height);
    }
    
    public void setPosition(int _x, int _y) {
        position.x = _x;
        position.y = _y;
    }
}
