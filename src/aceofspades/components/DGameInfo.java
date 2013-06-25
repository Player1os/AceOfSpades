package aceofspades.components;

import aceofspades.game.GameData;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class DGameInfo extends DComponent {
    
    private Point _position;
    private int _width;
    private DLabel _labelName;
    private DImage _imgIcon;
    private DLabel _labelAuthor;
    private DLabel _labelMinMaxPlayer;
    private Font _headerFont;
    private Font _contentFont;
    private Color _backgroundColor;

    public DGameInfo() {
        _labelName = new DLabel("No Game");
        _labelName.setAlignment(DLabel.centerAlign);
        _imgIcon = new DImage(null);
        _labelAuthor = new DLabel("");
        _labelMinMaxPlayer = new DLabel("");
    }
    
    public void updateGameData(GameData gameData) {
        if (gameData != null) {
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
        }
    }
    
    public void setPosition(Point position) {
        _position = position;
    }
    
    public void setWidth(int width) {
        _width = width;
        _imgIcon.setDimensions(new Dimension(_width - 100, _width - 100));
    }
    
    public void setHeaderFont(Font font, Color color) {
        _headerFont = font;
        _labelName.setFont(font, color);       
    }
    
    public void setContentFont(Font font, Color color) {
        _contentFont = font;
        _labelAuthor.setFont(font, color);
        _labelMinMaxPlayer.setFont(font, color);
    }
    
    public void setBackgroundColor(Color color) {
        _backgroundColor = color;
    }
    
    @Override
    public void draw(Graphics g) {
        int height = g.getFontMetrics(_headerFont).getHeight() + 10 + 
                2 * (g.getFontMetrics(_contentFont).getHeight() + 10) + _width - 50;
        
        g.setColor(_backgroundColor);
        g.fillRect(_position.x, _position.y, _width, height);
        g.setColor(Color.black);
        g.drawRect(_position.x, _position.y, _width, height);
        
        Point position = new Point(_position.x + _width / 2, _position.y + 
                g.getFontMetrics(_headerFont).getAscent());
        _labelName.setPosition(position);
        _labelName.draw(g);
        
        position.x = _position.x + 50;
        position.y += g.getFontMetrics(_headerFont).getDescent() + 25;
        _imgIcon.setPosition(position);
        _imgIcon.draw(g);
        
        position.x -= 20;
        position.y += _width - 50;
        _labelAuthor.setPosition(position);
        _labelAuthor.draw(g);
        
        position.y += g.getFontMetrics(_contentFont).getHeight() + 8;
        _labelMinMaxPlayer.setPosition(position);
        _labelMinMaxPlayer.draw(g);
    }

}
