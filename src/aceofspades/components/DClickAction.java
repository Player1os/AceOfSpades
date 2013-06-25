package aceofspades.components;

import java.awt.event.MouseEvent;

public abstract class DClickAction extends DAction {

    public final static int rightClick = 0;
    public final static int leftClick = 1;        

    protected int _click;

    public void setMouseClick(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            _click = leftClick;
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            _click = rightClick;
        }
    }    

}
