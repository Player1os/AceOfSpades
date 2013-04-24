package aceofspades.utils;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;

public abstract class DComponent extends MouseAdapter {

    public abstract void draw(Graphics g);
}
