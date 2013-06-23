package aceofspades.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class DLabel extends DComponent {

    public final static int leftAlign = 0;
    public final static int centerAlign = 1;
    
    private String _text;
    private Font _textFont;
    private Color _fontColor;
    private Rectangle _bounds;
    private int _align;
    private boolean _showBorder;
    private Color _borderColor;

    public DLabel() {
        this("");
    }
    
    public DLabel(String text) {
        _text = text;
        _bounds = new Rectangle();
        _align = leftAlign;
        _showBorder = false;
    }

    public String getText() {
        return _text;
    }
    
    public void setText(String text) {
        _text = text;
    }
    
    public void setPosition(Point position) {
        _bounds.x = position.x;
        _bounds.y = position.y;
    }
    
    public void setDimensions(Dimension dimension) {
        _bounds.width = dimension.width;
        _bounds.height = dimension.height;
    }
    
    public void setFont(Font font, Color color) {
        _textFont = font;
        _fontColor = color;
    }

    public void setAlignment(int align) {
        _align = leftAlign;
        if (align == centerAlign) {
            _align = centerAlign;
        }
        
    }
    
    public void showBorder(boolean showBorder) {
        _showBorder = showBorder;
        
    }
    
    public void setBorderColor(Color color) {
        _borderColor = color;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setFont(_textFont);
        
        Point position = _bounds.getLocation();
        if (_showBorder) {
            position.x += 5;
            g.setColor(_borderColor);
            g.drawRect(_bounds.x, _bounds.y, _bounds.width, _bounds.height);
        }
        position.y += (_bounds.height + 
                g.getFontMetrics().getAscent() - g.getFontMetrics().getDescent()) / 2;
        if (_align == centerAlign) {
            position.x += (_bounds.width - g.getFontMetrics().stringWidth(_text)) / 2;
        }
        g.setColor(_fontColor);
        g.drawString(_text, position.x, position.y);
    }
}
