package aceofspades.game;

import aceofspades.components.DCard;
import java.util.ArrayList;

public class Card {

    protected int _cardID;
    protected String _value;
    protected String _suit;
    
    protected int _deckPosition;
    protected Deck _deck;
    protected ArrayList<Boolean> _visibility;

    public Card(int cardID, String value, String suit, int playerCount) {
        _cardID = cardID;
        _value = value;
        _suit = suit;
        
        _visibility = new ArrayList<>();
        _visibility.ensureCapacity(playerCount);
        for (int i = 0; i < playerCount; i++) {
            _visibility.add(false);
        }
    }
    
    public int getCardID() {
        return _cardID;
    }
    
    public String getValue() {
        return _value;
    }
    
    public int getNumValue(boolean isABig) {
        switch (_value) {
            case "A":
                if (isABig) {
                    return 14;
                } else {
                    return 1;
                }
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            default:
                return Integer.parseInt(_value);
        }
    }

    public String getSuit() {
        return _suit;
    }
    
    public boolean isVisible(int playerID) {
        return _visibility.get(playerID);
    }

    public void setVisible(int playerID) {
        _visibility.set(playerID, true);
    }
    
    public void unsetVisible(int playerID) {
        _visibility.set(playerID, false);
    }
    
    public Deck getDeck() {
        return _deck;
    }
    
    public int getDeckPosition() {
        return _deckPosition;
    }
    
    public void addToDeck(Deck deck, int deckPosition) {
        _deck = deck;
        _deckPosition = deckPosition;
    }
    
    public DCard getDCard() {
        return new DCard(this);
    }
    
}
