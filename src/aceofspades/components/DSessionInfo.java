package aceofspades.components;

import aceofspades.game.SessionManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class DSessionInfo extends DComponent {
  
    private Point _position;
    private int _width;
    private DLabel _labelName;
    private DImage _imgIcon;
    private DLabel _labelCreator;
    private DLabel _labelPlayerText;
    private DLabel _labelPlayerCount;
    private Font _headerFont;
    private Color _headerFontColor;
    private Font _contentFont;
    private Color _contentFontColor;
    private Color _backgroundColor;

    public DSessionInfo() {
        /*_labelName = new DLabel("No Game");
        _imgIcon = new DImage(null);
        _labelAuthor = new DLabel("");
        _labelMinMaxPlayer = new DLabel("");
        _labelName.setAlignment(DLabel.centerAlign);*/
    }
    
    public void updateSessionManager(SessionManager sessionManager) {
        /*if (gameData != null) {
            _labelName.setText(gameData.getName());
            _imgIcon.setImage(gameData.getIcon());
            _labelAuthor.setText("Author : " + gameData.getAuthor());
            _labelMinMaxPlayer.setText("Min " + 
                    Integer.toString(gameData.getMinPlayerCount()) + " players | Max " + 
                    Integer.toString(gameData.getMaxPlayerCount()) + " players");
        } else {
            _labelName.setText("No Game");
            _imgIcon.setImage(null);
            _labelAuthor.setText("");
            _labelMinMaxPlayer.setText("");
        }*/
    }
    
    public void setPosition(Point position) {
        _position = position;
    }
    
    public void setWidth(int width) {
        _width = width;
        //_imgIcon.setDimensions(new Dimension(_width - 100, _width - 100));
    }
    
    /*public void setHeaderFont(Font font, Color color) {
        _headerFont = font;
        _headerFontColor = color;
    }
    
    public void setContentFont(Font font, Color color) {
        _contentFont = font;
        _contentFontColor = color;
    }*/
    
    public void setBackgroundColor(Color color) {
        _backgroundColor = color;
    }
    
    @Override
    public void draw(Graphics g) {
        int height = _width/*g.getFontMetrics(_headerFont).getHeight() + 10 + 
                2 * (g.getFontMetrics(_contentFont).getHeight() + 10) + _width - 50*/;
        
        g.setColor(_backgroundColor);
        g.fillRect(_position.x, _position.y, _width, height);
        g.setColor(Color.black);
        g.drawRect(_position.x, _position.y, _width, height);
        
        /*g.setFont(_headerFont);
        g.setColor(_headerFontColor);
        Point position = new Point(_position.x + _width / 2, _position.y + 
                g.getFontMetrics().getAscent());
        _labelName.setPosition(position);
        _labelName.draw(g);
        
        position.x = _position.x + 50;
        position.y += g.getFontMetrics().getDescent() + 25;
        _imgIcon.setPosition(position);
        _imgIcon.draw(g);
        
        g.setFont(_contentFont);
        g.setColor(_contentFontColor);
        
        position.x -= 20;
        position.y += _width - 50;
        _labelAuthor.setPosition(position);
        _labelAuthor.draw(g);
        
        position.y += g.getFontMetrics().getHeight() + 8;
        _labelMinMaxPlayer.setPosition(position);
        _labelMinMaxPlayer.draw(g);*/
    }
}
