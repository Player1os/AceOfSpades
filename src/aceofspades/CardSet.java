package aceofspades;

import java.util.ArrayList;

/**
 * 
 * @author Player1os <player1os at gmail.com>
 */
public class CardSet {
    
    ArrayList<Card> Cards;
    
    public void addCard(int _pos, Card _card) {
        this.Cards.add(_pos, _card);
    }
    
    public void removeCard(int _pos) {
        this.Cards.remove(_pos);
    }
    
    public Card getCard(int _pos) {
        return this.Cards.get(_pos);
    }
    
    public int getCardCount() {
        return Cards.size();
    }
    
    public void shuffle() {
        
    }
    
    /*public VisCard getVisCardSet() {
        
    }*/
} 
