package aceofspades;

/**
 * 
 * @author Player1os
 */
public class Card {
    protected String value;
    protected String suit;
    
    public Card(String _value, String _suit) {
        this.value = _value;
        this.suit = _suit;   
    }
        
    public String getValue() {
        return this.value; 
    }
    
    public String getSuit() {
        return this.suit;
    }
}
