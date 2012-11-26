package aceofspades;

/**
 * 
 * @author Player1os <player1os at gmail.com>
 */
public class Card {
    protected String value;
    protected String suit;
        
    protected boolean visible = true;
    protected VisCard visual;
    
    protected CardSet cardSet;
    protected int position;
    
    public Card(String _value, String _suit, int x, int y, CardSet cardset, int position) {
        this.value = _value;
        this.suit = _suit;   
        this.visual = new VisCard(x, y, _suit, _value, true);
        this.cardSet = cardset;
        this.position = position;
    }
        
    public String getValue() {
        return this.value; 
    }
    
    public String getSuit() {
        return this.suit;
    }
    
    public Boolean getVisible() {
        return this.visible; 
    }
    
    public VisCard getVisCard() {
        return visual;
    }
    
    public void setVisible(Boolean _visible) {
        this.visible = _visible; 
    }
    
    public void moveTo(CardSet _cardSet, int _position) {
        /*call canRemove of Cardset (cardSet, position)
        call canAdd of Cardset (newCardSet, newPosition)
        
        if both are true then
        */ 
        
        this.cardSet.removeCard(position);
        cardSet = _cardSet;
        position = _position;
        this.cardSet.addCard(position, this);
    }
}
