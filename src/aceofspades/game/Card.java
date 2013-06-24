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

    public DCard getDCard() {
        return new DCard(this);
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

    public void moveTo(Deck _cardSet, int _position) {
        /*Tester tester = new Tester(cardSet, position, _cardSet, _position, new Application());
        Application.lsGame.runScriptFunction(cardSet.getCardSetClass() + "Remove", tester);
        Tester tester2 = new Tester(cardSet, position, _cardSet, _position, new Application());
        Application.lsGame.runScriptFunction(_cardSet.getCardSetClass() + "Add", tester2);*/

        /*if (tester.getB() && tester2.getB()) {
            this.cardSet.removeCard(position);
            cardSet = _cardSet;
            position = _position;
            this.cardSet.addCard(position, this);
            Application.lsGame.runScriptFunction("afterMove", tester);
        }*/
    }

    

    class Tester {

        boolean b;
        Deck oldCardset;
        int oldPosition;
        Deck newCardset;
        int newPosition;
        /*Application app;

        public Tester(CardSet _oldCardset, int _oldPosition, CardSet _newCardset, int _newPosition, Application _app) {
            oldCardset = _oldCardset;
            oldPosition = _oldPosition;
            newCardset = _newCardset;
            newPosition = _newPosition;
            app = _app;
            b = true;
        }

        public Application getApp() {
            return app;
        }*/

        public Deck getOldCardSet() {
            return oldCardset;
        }

        public Deck getNewCardSet() {
            return newCardset;
        }

        public int getOldPosition() {
            return oldPosition;
        }

        public int getNewPosition() {
            return newPosition;
        }

        public void setNewPosition(int x) {
            newPosition = x;
        }

        public void setB(boolean _b) {
            b = _b;
        }

        public boolean getB() {
            return b;
        }
    }
}
