package aceofspades.components;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;

public abstract class DComponent extends MouseAdapter {

    public abstract void draw(Graphics g);
    public void unload() {}    
}
