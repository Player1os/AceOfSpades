/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aceofspades.frames;

import aceofspades.Application;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Wryxo
 */
public class DrawGameMouse extends MouseAdapter {

    DrawGame _draw;
    Frame _frame;
    int _mx;
    int _my;

    DrawGameMouse(Frame f, DrawStrategy draw) {
        _draw = (DrawGame) draw;
        _frame = f;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();


        if ((mx > _draw.dragCard.x) && (mx < (_draw.dragCard.x + _draw.dragCard.width))
                && (my > _draw.dragCard.y) && my < (_draw.dragCard.y + _draw.dragCard.height)) {
            _draw.hoverDragCard = true;
        } else {
            _draw.hoverDragCard = false;
        }
        
        _draw.hoverCard = -1;
        if (_draw.leftZoom != -1) {
            for (int i=0; i < Application.cardSety.get(_draw.leftZoom).getCardCount(); i++) {
                if ((mx > 10+20*i) && (mx < (60+20*i))
                    && (my > _frame._height-100) && (my < (_frame._height))) {
                    _draw.hoverCard = i;
                }
            }
        }
        
        if (_draw.rightZoom != -1) {
            for (int i=0; i < Application.cardSety.get(_draw.rightZoom).getCardCount(); i++) {
                if ((mx > _frame._width/2+10+20*i) && (mx < _frame._width/2+(10+50*(i+1)))
                    && (my > _frame._height-100) && (my < (_frame._height))) {
                    _draw.hoverCard = i;
                }
            }
        }
        
        _mx = mx;
        _my = my;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        /*
         * int last = _draw.a.size()-1; if ( mx > _draw.a.get(0).x && mx <
         * (_draw.a.get(last).x+_draw.a.get(last).width) && my >
         * _draw.a.get(0).y && my <
         * (_draw.a.get(last).y+_draw.a.get(last).height)) { if
         * (_draw.drawCascade) _draw.drawCascade = false; else _draw.drawCascade
         * = true; }
         */

        if ((mx > _draw.dragCard.x) && (mx < (_draw.dragCard.x + _draw.dragCard.width))
                && (my > _draw.dragCard.y) && (my < (_draw.dragCard.y + _draw.dragCard.height))) {
            _draw.selectDragCard = true;
        } else {
            _draw.selectDragCard = false;
        }
        
        _draw.selectCardSet = -1;
        for (int i=0; i < Application.cardSety.size(); i++) {
            if ((mx > Application.cardSety.get(i).getVisCardSet().position.x) && (mx < (Application.cardSety.get(i).getVisCardSet().position.x + Application.cardSety.get(i).getVisCardSet().position.width))
                    && (my > Application.cardSety.get(i).getVisCardSet().position.y) && (my < (Application.cardSety.get(i).getVisCardSet().position.y + Application.cardSety.get(i).getVisCardSet().position.height))) {
                if (e.getButton() == MouseEvent.BUTTON1) _draw.leftZoom = i;
                if (e.getButton() == MouseEvent.BUTTON3) _draw.rightZoom = i;
                _draw.selectCardSet = i;
            }
        }
        _draw.selectCard = -1;
        if (_draw.leftZoom != -1) {
            for (int i=0; i < Application.cardSety.get(_draw.leftZoom).getCardCount(); i++) {
                if ((mx > 10+20*i) && (mx < (60+20*i))
                    && (my > _frame._height-100) && (my < (_frame._height))) {
                    _draw.selectCard = i;
                }
            }
        }
        
        if (_draw.rightZoom != -1) {
            for (int i=0; i < Application.cardSety.get(_draw.rightZoom).getCardCount(); i++) {
                if ((mx > _frame._width/2+10+20*i) && (mx < _frame._width/2+(10+50*(i+1)))
                    && (my > _frame._height-100) && (my < (_frame._height))) {
                    _draw.selectCard = i;
                }
            }
        }
        
        _mx = mx;
        _my = my;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        if (_draw.selectCardSet != -1) {
            Application.cardSety.get(_draw.selectCardSet).getVisCardSet().position.x = mx - _mx;
            Application.cardSety.get(_draw.selectCardSet).getVisCardSet().position.y = my - _my;
        }
        
        
        
        _mx = mx;
        _my = my;
    }
}
