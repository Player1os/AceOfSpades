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
        
        /*
         * OnHover StartGame Button
         */
        if ( (mx > _draw.startButton.x) && (mx < (_draw.startButton.x+_draw.startButton.width)) &&
                (my > _draw.startButton.y) && (my < (_draw.startButton.y+_draw.startButton.height))) {
            _draw.hoverStartButton = true;
        } else {
            _draw.hoverStartButton = false;
        }
        
        /*
         * OnHover Options Button
         */
        if ( (mx > _draw.optionsButton.x) && (mx < (_draw.optionsButton.x+_draw.optionsButton.width)) &&
                (my > _draw.optionsButton.y) && (my < (_draw.optionsButton.y+_draw.optionsButton.height)) ) {
            _draw.hoverOptionsButton = true;
        } else {
            _draw.hoverOptionsButton = false;
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        /*
         * OnClick StartGame Button
         */
        if ( (mx > _draw.startButton.x) && (mx < (_draw.startButton.x+_draw.startButton.width)) &&
                (my > _draw.startButton.y) && (my < (_draw.startButton.y+_draw.startButton.height)) ) {
            DrawStrategy draw = new DrawGame(_frame);
            _frame.setDrawStrategy(draw);
            _frame.setMouseListener(new DrawGameMouse(_frame, draw));
            _frame.setMouseMotionListener(new DrawGameMouse(_frame, draw));
        }
        
        /*
         * OnClick Options Button
         */
        if ( (mx > _draw.optionsButton.x) && (mx < (_draw.optionsButton.x+_draw.optionsButton.width)) &&
                (my > _draw.optionsButton.y) && (my < (_draw.optionsButton.y+_draw.optionsButton.height)) ) {
            DrawStrategy draw = new DrawOptions(_frame);
            _frame.setDrawStrategy(draw);
            _frame.setMouseListener(new DrawOptionsMouse(_frame, draw));
            _frame.setMouseMotionListener(new DrawOptionsMouse(_frame, draw));
        }
    }
}
