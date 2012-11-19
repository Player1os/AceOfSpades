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
public class DrawMenuMouse extends MouseAdapter{
    DrawMenu _draw;
    Frame _frame;
    
    DrawMenuMouse(Frame f, DrawStrategy draw){
        _draw = (DrawMenu) draw;
        _frame = f;
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if ( mx > _draw.startButton.x && mx < (_draw.startButton.x+_draw.startButton.width) &&
                my > _draw.startButton.y && my < (_draw.startButton.y+_draw.startButton.height)) {
            _draw.setHover(true);
        } else {
            _draw.setHover(false);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if ( mx > _draw.startButton.x && mx < (_draw.startButton.x+_draw.startButton.width) &&
                my > _draw.startButton.y && my < (_draw.startButton.y+_draw.startButton.height)) {
            DrawStrategy draw = new DrawOptions(_frame);
            _frame.setDrawStrategy(draw);
            _frame.setMouseListener(new DrawOptionsMouse(_frame, draw));
            _frame.setMouseMotionListener(new DrawOptionsMouse(_frame, draw));
        }
    }
}
