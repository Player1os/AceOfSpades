package aceofspades.components;

import aceofspades.game.Card;
import aceofspades.game.Deck;
import aceofspades.game.GameManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DDeckZoom extends DComponent {
    
    private static Dimension cardDimensions = new Dimension(50, 80);
    private static int _margin = 20;
    
    private GameManager _gameManager;
    private Deck _deck;
    private Rectangle _bounds;
    private Color _backgroundColor;
    private ArrayList<DCard> _cards;
    private DCard _selectedDCard;
    private DComponentDrawer _dCompDraw;
    private DDeckZoomAction _action;
    private Font _font;

    public DDeckZoom() {
        _bounds = new Rectangle();
        _cards = null;
    }
    
    public void setFont(Font font) {
        _font = font;
    }
    
    public void setGameManager(GameManager gameManager) {
        _gameManager = gameManager;
    }
    
    public void setDCompDraw(DComponentDrawer dCompDraw) {
        _dCompDraw = dCompDraw;
    }
    
    public Deck getDeck() {
        return _deck;
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
    
    public boolean isInBounds(Point position) {
        return _bounds.contains(position);
    }
    
    public int getDeckPosition(Point position) {
        if (!isInBounds(position) || (_cards == null)) {
            return -1;
        }
        int deckPosition = (position.x - _bounds.x) / _margin;
        
        if (deckPosition > _cards.size()) {
            return _cards.size();
        } else if (deckPosition >= 0) {
            return deckPosition;
        } else {
            return -1;
        }
    }
    
    public void loadDeck(Deck deck) {
        _selectedDCard = null;
        _deck = deck;            

        if (deck != null) {
            _cards = new ArrayList<>();

            Point cardPosition = new Point(_margin + _bounds.x, _bounds.y + 
                    (_bounds.height - cardDimensions.height) / 2);

            for (Card card : deck.getCards()) {
                DCard dCard = card.getDCard();
                dCard.setPlayerID(_gameManager.getActivePlayer().getPlayerID());
                dCard.setPosition(cardPosition);
                dCard.setDimensions(cardDimensions);
                dCard.setFont(_font);
                _cards.add(dCard);

                cardPosition.x += _margin;
            }
        } else {
            _cards = null;
        }
    }
    
    public void setAction(DDeckZoomAction action) {
        _action = action;
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
        if (_action != null) {
            if ((!isInBounds(e.getPoint())) || (_cards == null)) {
                return;
            }
            for (int i = _cards.size() - 1; i >= 0; i--) {
                if (_cards.get(i).isInBounds(e.getPoint())) {
                    _selectedDCard = _cards.get(i);
                    _cards.remove(i);
                    _action.setSelectedCard(_selectedDCard);
                    _dCompDraw.setComponent(_selectedDCard);
                    return;
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (_action != null) {
            if (_selectedDCard != null) {
                _selectedDCard.setPosition(new Point(e.getX() - cardDimensions.width
                        / 2, e.getY() - cardDimensions.height / 2));
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (_action != null) {
            if (_selectedDCard != null) {
                _action.setMouseEvent(e);
                _action.run();
                _action.setSelectedCard(null);
                _dCompDraw.setComponent(null);
            }
        }
    }
    
}
