package aceofspades.components;

import aceofspades.game.GameData;
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
    private DLabel _labelOpenSlotsText;
    private DLabel _labelOpenSlotsCount;
    private Font _headerFont;
    private Font _contentFont;
    private Color _backgroundColor;

    public DSessionInfo() {
        _labelName = new DLabel("No Session");
        _labelName.setAlignment(DLabel.centerAlign);
        _imgIcon = new DImage(null);
        _labelCreator = new DLabel("");
        _labelPlayerText = new DLabel("");
        _labelPlayerCount = new DLabel("");
        _labelOpenSlotsText = new DLabel("");
        _labelOpenSlotsCount = new DLabel("");
    }
    
    public void updateSessionManager(SessionManager sessionManager) {
        if (sessionManager != null) {
            GameData gameData = sessionManager.getGameData();
            _labelName.setText(gameData.getName());
            _imgIcon.setImage(gameData.getIcon());
            _labelCreator.setText("Creator : " + sessionManager.getCreator().getName());
            _labelPlayerText.setText("Player count :");
            _labelPlayerCount.setText(Integer.toString(sessionManager.getPlayers().size()));
            if (sessionManager.getPlayers().size() < gameData.getMinPlayerCount()) {
                _labelPlayerCount.setFont(_contentFont, Color.red);
            } else {
                _labelPlayerCount.setFont(_contentFont, Color.green);
            }
            _labelOpenSlotsText.setText("Open slot count :");
            _labelOpenSlotsCount.setText(Integer.toString(sessionManager.getOpenSlotCount()));
        } else {
            _labelName.setText("No Session");
            _imgIcon.setImage(null);
            _labelCreator.setText("");
            _labelPlayerText.setText("");
            _labelPlayerCount.setText("");
            _labelOpenSlotsText.setText("");
            _labelOpenSlotsCount.setText("");
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
        _labelName.setFont(font, color);
        _headerFont = font;
    }
    
    public void setContentFont(Font font, Color color) {
        _labelCreator.setFont(font, color);
        _labelPlayerText.setFont(font, color);
        _labelOpenSlotsText.setFont(font, color);
        _labelOpenSlotsCount.setFont(font, color);
        _contentFont = font;
    }
    
    public void setBackgroundColor(Color color) {
        _backgroundColor = color;
    }
    
    @Override
    public void draw(Graphics g) {
        int height = g.getFontMetrics(_headerFont).getHeight() + 10 + 3 * 
                (g.getFontMetrics(_contentFont).getHeight() + 10) + _width - 50;
        
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
        _labelCreator.setPosition(position);
        _labelCreator.draw(g);
        
        position.y += g.getFontMetrics(_contentFont).getHeight() + 8;
        _labelPlayerText.setPosition(position);
        _labelPlayerText.draw(g);
        
        position.x += g.getFontMetrics(_contentFont).stringWidth("Player count :") + 10;
        _labelPlayerCount.setPosition(position);
        _labelPlayerCount.draw(g);
        
        position.x = _position.x + 30;
        position.y += g.getFontMetrics(_contentFont).getHeight() + 8;
        _labelOpenSlotsText.setPosition(position);
        _labelOpenSlotsText.draw(g);
        
        position.x += g.getFontMetrics(_contentFont).stringWidth("Open slot count :") + 10;
        _labelOpenSlotsCount.setPosition(position);
        _labelOpenSlotsCount.draw(g);
    }
}
