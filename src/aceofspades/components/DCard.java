package aceofspades.components;

import aceofspades.Main;
import aceofspades.game.Card;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DCard extends DComponent{

    private Card _card;    
    private Rectangle _bounds;
    private BufferedImage _backImg;
    private int _playerID;
    private boolean bumpUp;

    public DCard(Card card) {
        _card = card;
        _backImg = Main.getImageResource("cardBack.jpg");
        bumpUp = false;
    }
    
    public void setPosition(Point position) {
        _bounds.x = position.x;
        _bounds.y = position.y;
    }
    
    public void setDimensions(Dimension dimension) {
        _bounds.width = dimension.width;
        _bounds.height = dimension.height;
    }
    
    public void setPlayerID(int playerID) {
        _playerID = playerID;
    }

    @Override
    public void draw(Graphics g) {
        if (_card.isVisible(_playerID)) {
            Rectangle bounds = new Rectangle(_bounds);
            if (bumpUp) {
                bounds.y += 5;
            }
            
            g.setColor(Color.white);
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            g.setColor(Color.black);
            g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
            
            String suite = _card.getSuit();
            String value = _card.getValue();
            
            switch (suite) {
                case "hearts":
                    g.setColor(Color.RED);
                    g.drawString("♥", bounds.x + 4, bounds.y + 20);
                    g.drawString(value, bounds.x + 4, bounds.y + 40);
                    break;
                case "diamonds":
                    g.setColor(Color.RED);
                    g.drawString("♦", bounds.x + 4, bounds.y + 20);
                    g.drawString(value, bounds.x + 4, bounds.y + 40);
                    break;
                case "spades":
                    g.setColor(Color.BLACK);
                    g.drawString("♠", bounds.x + 4, bounds.y + 20);
                    g.drawString(value, bounds.x + 4, bounds.y + 40);
                    break;
                case "clubs":
                    g.setColor(Color.BLACK);
                    g.drawString("♣", bounds.x + 4, bounds.y + 20);
                    g.drawString(value, bounds.x + 4, bounds.y + 40);
                    break;
            }
            
        } else {
            g.drawImage(_backImg, _bounds.x, _bounds.y, _bounds.width, _bounds.height, null);
        }
    }
    
    public boolean isInBounds(Point position) {
        return _bounds.contains(position);
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        bumpUp = _bounds.contains(e.getPoint());
    }
    
}
