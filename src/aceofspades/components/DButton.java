package aceofspades.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class DButton extends DComponent {
    
    private boolean _enabled;
    
    private String _text;
    private Font _textFont;
    private Color _fontColor;
    private Color _backgroundColor;
    private Color _hoverBackgroundColor;
    private boolean _isHovered;
    private Rectangle _bounds;
    private DAction _action;
    
    public DButton(String text) {
        _text = text;
        _isHovered = false;
        _enabled = true;
        _bounds = new Rectangle();
        _action = null;
    }    
    
    public void setEnabled(boolean enabled) {
        _enabled = enabled;
    }
    
    public void setFont(Font font, Color color) {
        _textFont = font;
        _fontColor = color;
    }
    
    public void setPosition(Point position) {
        _bounds.x = position.x;
        _bounds.y = position.y;
    }
    
    public void setDimensions(Dimension dimension) {
        _bounds.width = dimension.width;
        _bounds.height = dimension.height;
    }
    
    public void setBackground(Color color) {
        _backgroundColor = color;
    }
    
    public void setHoverBackground(Color color) {
        _hoverBackgroundColor = color;
    }
    
    public void setAction(DAction action) {
        _action = action;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setFont(_textFont);
        if (_enabled) {
            if (_isHovered) {
                g.setColor(_hoverBackgroundColor);
            } else {
                g.setColor(_backgroundColor);
            }
        } else {
            g.setColor(new Color(150, 150, 150));
        }
        
        g.fillRect(_bounds.x, _bounds.y, _bounds.width, _bounds.height);
        g.setColor(Color.black);
        g.drawRect(_bounds.x, _bounds.y, _bounds.width, _bounds.height);
        
        int strPosX = _bounds.x + 1 + (_bounds.width - 
                g.getFontMetrics().stringWidth(_text)) / 2;
        int strPosY = _bounds.y + (_bounds.height + 
                g.getFontMetrics().getAscent() - g.getFontMetrics().getDescent()) / 2;
        g.setColor(_fontColor);        
        g.drawString(_text, strPosX, strPosY);
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        if (_enabled) {
            if (_bounds.contains(e.getPoint())) {
                _isHovered = true;
            } else {
                _isHovered = false;
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (_enabled && (_action != null)) {
            if (_bounds.contains(e.getPoint())) {
                _action.run();
            }
        }
    }
}
