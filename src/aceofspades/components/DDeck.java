package aceofspades.components;

import aceofspades.game.Deck;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DDeck extends DComponent {

    private Deck _deck;
    private Rectangle _bounds;
    private String _name;
    private BufferedImage _img;
    private Color _highlightColor;
    private DMouseAction _action;
    private Font _font;

    public DDeck(Deck deck) {
        _deck = deck;
        _name = deck.toString();
        _bounds = new Rectangle();
    }
    
    public void setFont(Font font) {
        _font = font;
    }
    
    public void setPosition(Point position) {
        _bounds.x = position.x;
        _bounds.y = position.y;
    }
    
    public void setDimensions(Dimension dimension) {
        _bounds.width = dimension.width;
        _bounds.height = dimension.height;
    }
    
    public void setImage(BufferedImage img) {
        _img = img;
    }
    
    public void setHighlightColor(Color color) {
        _highlightColor = color;
    }
    
    public void setAction(DMouseAction action) {
        _action = action;
    }
    
    public Deck getDeck() {
        return _deck;
    }
    
    @Override
    public void draw(Graphics g) {
        if (_highlightColor != null) {
            g.setColor(_highlightColor);
            g.fillRect(_bounds.x, _bounds.y, _bounds.width, _bounds.height);
        }
        
        int padding = 3;
        Rectangle smallBounds = new Rectangle(_bounds.x + padding, _bounds.y + 
                padding, _bounds.width - 2 * padding, _bounds.height - 2 * padding);
        
        g.drawImage(_img, smallBounds.x, smallBounds.y, smallBounds.width, 
                smallBounds.height, null);
        
        g.setFont(_font);
        g.setColor(Color.black);
        g.drawString(_deck.toString(), _bounds.x + (_bounds.width - 
                g.getFontMetrics().stringWidth(_deck.toString()))/ 2, _bounds.y 
                + _bounds.height + 15);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (_action != null) {
            if (_bounds.contains(e.getPoint())) {
                _action.setMouseEvent(e);
                _action.run();
            }
        }
    }
    
}
