package aceofspades.components;

import aceofspades.game.Card;
import aceofspades.game.Deck;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class DDeckZoom extends DComponent {
    
    private Rectangle _bounds;
    private Color _backgroundColor;
    private ArrayList<DCard> _cards;

    public DDeckZoom() {
        _bounds = new Rectangle();
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
    
    public void loadDeck(Deck deck) {
        _cards = new ArrayList<>();
        
        for (Card card : deck.getCards()) {
            card.get
        }
    }
    
    @Override
    public void draw(Graphics g) {
        
    }

}
