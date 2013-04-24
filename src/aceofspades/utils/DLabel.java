package aceofspades.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class DLabel extends DComponent {

    private String _text;
    private Font _textFont;
    private Color _fontColor;
    private Point _position;

    public DLabel(String text) {
        _text = text;
        _position = new Point();
    }

    public void setCenterPosition(Point p) {
        _position.x = p.x;
        _position.y = p.y;
    }
    
    public void setFont(Font f, Color c) {
        _textFont = f;
        _fontColor = c;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(_fontColor);
        g.setFont(_textFont);
        g.drawString(_text, _position.x - 
                g.getFontMetrics().stringWidth(_text) / 2
                , _position.y + g.getFontMetrics().getAscent());
    }
}
