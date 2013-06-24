package aceofspades.components;

import java.awt.Color;
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
        _bounds = new Rectangle();
    }
    
    public void setImage(BufferedImage img) {
        _img = img;
    }
    
    public void setPosition(Point position) {
        _bounds.x = position.x;
        _bounds.y = position.y;
    }
    
    public void setDimensions(Dimension dimension) {
        _bounds.width = dimension.width;
        _bounds.height = dimension.height;
    }
    
    @Override
    public void draw(Graphics g) {
        if (_img != null) {
            g.drawImage(_img, _bounds.x, _bounds.y, _bounds.width, _bounds.height, null);
            g.setColor(Color.black);
            g.drawRect(_bounds.x, _bounds.y, _bounds.width, _bounds.height);
        }
    }

}
