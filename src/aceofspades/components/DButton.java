package aceofspades.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class DButton extends DComponent {
    
    private String _text;
    private Font _textFont;
    private Color _fontColor;
    private Color _backgroundColor;
    private Color _hoverBackgroundColor;
    private Boolean _isHovered;
    private Rectangle _bounds;
    private DAction _action;
    
    public DButton(String text) {
        _text = text;
        _isHovered = false;
        _bounds = new Rectangle();
        _action = null;
    }    
    
    public void setFont(Font f, Color c) {
        _textFont = f;
        _fontColor = c;
    }
    
    public void setPosition(Point p) {
        _bounds.x = p.x;
        _bounds.y = p.y;
    }
    
    public void setDimensions(Dimension d) {
        _bounds.width = d.width;
        _bounds.height = d.height;
    }
    
    public void setBackground(Color c) {
        _backgroundColor = c;
    }
    
    public void setHoverBackground(Color c) {
        _hoverBackgroundColor = c;
    }
    
    public void setAction(DAction a) {
        _action = a;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setFont(_textFont);
        if (_isHovered) {
            g.setColor(_hoverBackgroundColor);
        } else {
            g.setColor(_backgroundColor);
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
        if (_bounds.contains(e.getPoint())) {
            _isHovered = true;
        } else {
            _isHovered = false;
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (_action != null) {
            if (_bounds.contains(e.getPoint())) {
                _action.run();
            }
        }
    }
}
