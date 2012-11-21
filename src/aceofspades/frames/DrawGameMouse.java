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
public class DrawGameMouse extends MouseAdapter{
    DrawGame _draw;
    Frame _frame;
    int _mx;
    int _my;
    
    DrawGameMouse(Frame f, DrawStrategy draw){
        _draw = (DrawGame) draw;
        _frame = f;
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if ( mx > _draw.dragCard.x && mx < (_draw.dragCard.x+_draw.dragCard.width) &&
                my > _draw.dragCard.y && my < (_draw.dragCard.y+_draw.dragCard.height)) {
            _draw.hoverDragCard = true;
        } else {
            _draw.hoverDragCard = false;
        }
        _draw.hoverCard = -1;
        for (int i=0; i<_draw.a.size(); i++){
            if ( mx > _draw.a.get(i).x && mx < (_draw.a.get(i).x+_draw.a.get(i).width) &&
                    my > _draw.a.get(i).y && my < (_draw.a.get(i).y+_draw.a.get(i).height)) {
                _draw.hoverCard = i;
            }
        }        
        _mx = mx;
        _my = my;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        /*int last = _draw.a.size()-1;
        if ( mx > _draw.a.get(0).x && mx < (_draw.a.get(last).x+_draw.a.get(last).width) &&
                my > _draw.a.get(0).y && my < (_draw.a.get(last).y+_draw.a.get(last).height)) {
                if (_draw.drawCascade) _draw.drawCascade = false;
                else _draw.drawCascade = true;
            }*/
        _draw.selectCard = -1;
        for (int i=0; i<_draw.a.size(); i++){
            if ( mx > _draw.a.get(i).x && mx < (_draw.a.get(i).x+_draw.a.get(i).width) &&
                    my > _draw.a.get(i).y && my < (_draw.a.get(i).y+_draw.a.get(i).height)) {
                _draw.selectCard = i;
            }
        }
        if ( mx > _draw.dragCard.x && mx < (_draw.dragCard.x+_draw.dragCard.width) &&
                my > _draw.dragCard.y && my < (_draw.dragCard.y+_draw.dragCard.height)) {
            _draw.selectDragCard = true;
        } else {
            _draw.selectDragCard = false;
        }
        _mx = mx;
        _my = my;
    }
    
    @Override
    public void mouseDragged (MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        for (int i=0; i < _draw.a.size(); i++) {
            if (_draw.selectCard == i) {
                _draw.a.get(i).x += mx - _mx;
                _draw.a.get(i).y += my - _my;
            }
        }
        if (_draw.selectDragCard) {
            _draw.x += mx - _mx;
            _draw.y += my - _my;
        }
        _mx = mx;
        _my = my;
    }
    
    
}
