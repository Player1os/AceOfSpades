package aceofspades;

import java.util.ArrayList;

/**
 * 
 * @author Player1os <player1os at gmail.com>
 */
public class CardSet {
    
    ArrayList<Card> Cards;
    VisCardSet visual;
    String setClass;
    
    /**
     * 
     * @param x
     * @param y
     * @param s 
     */
    public CardSet (int x, int y, String s) {
        visual = new VisCardSet(x, y, s);
        setClass = s;
        Cards = new ArrayList<Card>();
    }
    
    public void addCard(int _pos, Card _card) {
        this.Cards.add(_pos, _card);        
        for (int i=_pos; i < Cards.size(); i++) {
            this.Cards.get(i).position = i;
        }
    }
    
    public void removeCard(int _pos) {
        this.Cards.remove(_pos);
        for (int i=_pos; i < Cards.size(); i++) {
            this.Cards.get(i).position = i;
        }
    }
    
    public Card getCard(int _pos) {
        return this.Cards.get(_pos);
    }
    
    public int getCardCount() {
        return Cards.size();
    }
    
    public void shuffle() {
        
    }
    
    public VisCardSet getVisCardSet() {
        return visual;
    }
}
