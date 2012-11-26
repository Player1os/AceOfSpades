package aceofspades.frames;

import aceofspades.Application;
import aceofspades.CardSet;
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
        
        _draw.hoverlzCard = -1;
        _draw.hoverrzCard = -1;
        if (_draw.leftZoom != -1) {
            for (int i=0; i < Application.cardSety.get(_draw.leftZoom).getCardCount(); i++) {
                if ((mx > 10+20*i) && (mx < (60+20*i))
                    && (my > _frame._height-100) && (my < (_frame._height))) {
                    _draw.hoverlzCard = i;
                    _draw.hoverrzCard = -1;
                }
            }
        }
        
        if (_draw.rightZoom != -1) {
            for (int i=0; i < Application.cardSety.get(_draw.rightZoom).getCardCount(); i++) {
                if ((mx > _frame._width/2+10+20*i) && (mx < _frame._width/2+(10+50*(i+1)))
                    && (my > _frame._height-100) && (my < (_frame._height))) {
                    _draw.hoverrzCard = i;
                    _draw.hoverlzCard = -1;
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
        _draw.selectCardSet = -1;
        _draw.selectlzCard = -1;
        _draw.selectrzCard = -1;
        for (int i=0; i < Application.cardSety.size(); i++) {
            if ((mx > Application.cardSety.get(i).getVisCardSet().position.x) && (mx < (Application.cardSety.get(i).getVisCardSet().position.x + Application.cardSety.get(i).getVisCardSet().position.width))
                    && (my > Application.cardSety.get(i).getVisCardSet().position.y) && (my < (Application.cardSety.get(i).getVisCardSet().position.y + Application.cardSety.get(i).getVisCardSet().position.height))) {
                if (e.getButton() == MouseEvent.BUTTON1 && _draw.rightZoom != i) _draw.leftZoom = i;
                if (e.getButton() == MouseEvent.BUTTON3 && _draw.leftZoom != i) _draw.rightZoom = i;
                _draw.selectCardSet = i;
            }
        }
        if (_draw.leftZoom != -1) {
            for (int i=0; i < Application.cardSety.get(_draw.leftZoom).getCardCount(); i++) {
                if ((mx > 10+20*i) && (mx < (60+20*i))
                    && (my > _frame._height-100) && (my < (_frame._height))) {
                    _draw.selectlzCard = i;
                }
            }
        }
        
        if (_draw.rightZoom != -1) {
            for (int i=0; i < Application.cardSety.get(_draw.rightZoom).getCardCount(); i++) {
                if ((mx > _frame._width/2+10+20*i) && (mx < _frame._width/2+(60+20*i))
                    && (my > _frame._height-100) && (my < (_frame._height))) {
                    _draw.selectrzCard = i;
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
            Application.cardSety.get(_draw.selectCardSet).getVisCardSet().position.x += mx - _mx;
            Application.cardSety.get(_draw.selectCardSet).getVisCardSet().position.y += my - _my;
        }
        
        if (_draw.selectlzCard != -1) {
            Application.cardSety.get(_draw.leftZoom).getCard(_draw.selectlzCard).getVisCard().position.x += mx - _mx;
            Application.cardSety.get(_draw.leftZoom).getCard(_draw.selectlzCard).getVisCard().position.y += my - _my;
        }
        
        if (_draw.selectrzCard != -1) {
            Application.cardSety.get(_draw.rightZoom).getCard(_draw.selectrzCard).getVisCard().position.x += mx - _mx;
            Application.cardSety.get(_draw.rightZoom).getCard(_draw.selectrzCard).getVisCard().position.y += my - _my;
        }
        
        _mx = mx;
        _my = my;
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        if (_draw.rightZoom != -1 && _draw.selectlzCard != -1) {
            CardSet lzCS = Application.cardSety.get(_draw.leftZoom);
            CardSet rzCS = Application.cardSety.get(_draw.rightZoom);
            for (int i=rzCS.getCardCount(); i >= 0; i--) {
                if ((mx > _frame._width/2+10+20*i) && (mx < _frame._width/2+(10+20*(i+1)))
                    && (my > _frame._height-100) && (my < (_frame._height))) {
                    lzCS.getCard(_draw.selectlzCard).moveTo(rzCS, i);
                    break;
                }
            }
            if ((mx > _frame._width/2+10+20*rzCS.getCardCount()) && (mx < _frame._width)
                    && (my > _frame._height-100) && (my < (_frame._height))) {
                    lzCS.getCard(_draw.selectlzCard).moveTo(rzCS, rzCS.getCardCount());
                }
        }
        
        if (_draw.leftZoom != -1 && _draw.selectrzCard != -1) {
            CardSet lzCS = Application.cardSety.get(_draw.leftZoom);
            CardSet rzCS = Application.cardSety.get(_draw.rightZoom);
            for (int i=lzCS.getCardCount()-1; i >= 0 ; i--) {
                if ((mx > 10+20*i) && (mx < (10+20*(i+1)))
                    && (my > _frame._height-100) && (my < (_frame._height))) {
                    rzCS.getCard(_draw.selectrzCard).moveTo(lzCS, i);
                    break;
                }
            }
            if ((mx > 10+20*lzCS.getCardCount()) && (mx < _frame._width/2)
                    && (my > _frame._height-100) && (my < (_frame._height))) {
                    rzCS.getCard(_draw.selectrzCard).moveTo(lzCS, lzCS.getCardCount());
            }
        }
        
        _draw.selectCardSet = -1;
        _draw.selectlzCard = -1;
        _draw.selectrzCard = -1;
    }
}
