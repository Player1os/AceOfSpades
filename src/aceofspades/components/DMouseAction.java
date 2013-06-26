package aceofspades.components;

import java.awt.event.MouseEvent;

public abstract class DMouseAction extends DAction {

    protected MouseEvent _e;

    public void setMouseEvent(MouseEvent e) {
        _e = e;
    }    

}
