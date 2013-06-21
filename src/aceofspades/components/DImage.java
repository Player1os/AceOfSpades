package aceofspades.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class DImage extends DComponent {

    protected BufferedImage _img;
    private Rectangle _bounds;
    
    public DImage(BufferedImage img) {
        _img = img;
    }
    
    public void setImage(BufferedImage img) {
        _img = img;
    }
    
    public void setPosition(Point p) {
        _bounds.x = p.x;
        _bounds.y = p.y;
    }
    
    public void setDimensions(Dimension d) {
        _bounds.width = d.width;
        _bounds.height = d.height;
    }
    
    @Override
    public void draw(Graphics g) {
        if (_img != null) {
            g.drawImage(_img, _bounds.x, _bounds.y, _bounds.width, _bounds.height, null);
            g.drawRect(_bounds.x, _bounds.y, _bounds.width, _bounds.height);
        }
    }

}
