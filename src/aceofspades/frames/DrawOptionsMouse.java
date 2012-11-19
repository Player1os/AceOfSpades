/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aceofspades.frames;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Wryxo
 */
public class DrawOptionsMouse extends MouseAdapter{
    DrawOptions _draw;
    Frame _frame;
    
    DrawOptionsMouse(Frame f, DrawStrategy draw){
        _draw = (DrawOptions) draw;
        _frame = f;
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if ( mx > _draw.menuButton.x && mx < (_draw.menuButton.x+_draw.menuButton.width) &&
                my > _draw.menuButton.y && my < (_draw.menuButton.y+_draw.menuButton.height)) {
            _draw.hoverMenuButton = true;
        } else {
            _draw.hoverMenuButton = false;
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if ( mx > _draw.menuButton.x && mx < (_draw.menuButton.x+_draw.menuButton.width) &&
                my > _draw.menuButton.y && my < (_draw.menuButton.y+_draw.menuButton.height)) {
            DrawStrategy draw = new DrawMenu(_frame);
            _frame.setDrawStrategy(draw);
            _frame.setMouseListener(new DrawMenuMouse(_frame, draw));
            _frame.setMouseMotionListener(new DrawMenuMouse(_frame, draw));
        }
    }
}
