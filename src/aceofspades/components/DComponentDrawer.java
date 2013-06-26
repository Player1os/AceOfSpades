package aceofspades.components;

import java.awt.Graphics;

public class DComponentDrawer extends DComponent {
    
    private DComponent _comp;

    public void setComponent(DComponent comp) {
        _comp = comp;
    }
    
    @Override
    public void draw(Graphics g) {
        if (_comp != null) {
            _comp.draw(g);
        }
    }

}
