package aceofspades.components;

import aceofspades.framestates.FrameState;
import aceofspades.game.Card;
import aceofspades.game.Deck;
import aceofspades.game.GameManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DDeckZoom extends DComponent {
    
    private static Dimension cardDimensions = new Dimension(50, 80);
    
    private FrameState _frameState;
    private Rectangle _bounds;
    private Color _backgroundColor;
    private ArrayList<DCard> _cards;
    private DCard _selectedDCard;

    public DDeckZoom(FrameState frameState) {
        _frameState = frameState;
        _bounds = new Rectangle();
        _cards = null;
        _selectedDCard = null;
    }
    
    public void setPosition(Point position) {
        _bounds.x = position.x;
        _bounds.y = position.y;
    }
    
    public void setDimensions(Dimension dimension) {
        _bounds.height = dimension.height;
        _bounds.width = dimension.width;
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        _backgroundColor = backgroundColor;
    }
    
    public void loadDeck(Deck deck, GameManager gameManager) {
        _cards = new ArrayList<>();
        
        int margin = 10;
        Point cardPosition = new Point(margin + _bounds.x, _bounds.y + 
                (_bounds.height - cardDimensions.height) / 2);
        
        for (Card card : deck.getCards()) {
            DCard dCard = card.getDCard();
            dCard.setPlayerID(gameManager.getActivePlayer().getPlayerID());
            dCard.setPosition(cardPosition);
            dCard.setDimensions(cardDimensions);
            
            cardPosition.x += margin;
        }
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(_backgroundColor);
        g.fillRect(_bounds.x, _bounds.y, _bounds.width, _bounds.height);
        
        if (_cards != null) {
            for (DCard dCard : _cards) {
                dCard.draw(g);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = _cards.size() - 1; i >= 0; i--) {
            if (_cards.get(i).isInBounds(e.getPoint())) {
                _cards.remove(i);
                _selectedDCard = _cards.get(i);
                _frameState.addFirstComponent(_selectedDCard);
                break;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (_selectedDCard != null) {
            _selectedDCard.setPosition(new Point(e.getX() + cardDimensions.width
                    / 2, e.getY() + cardDimensions.height));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    
}
