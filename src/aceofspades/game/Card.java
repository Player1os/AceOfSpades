package aceofspades.game;

import aceofspades.components.DCard;

public class Card {

    protected String value;
    protected String suit;
    protected boolean visible;
    protected DCard visual;
    protected Deck _deck;
    protected int position;

    public Card(String _value, String _suit, int x, int y, Deck cardset, int position, boolean visible) {
        this.value = _value;
        this.suit = _suit;
        this.visual = new DCard(x, y, _suit, _value, visible);
        this._deck = cardset;
        this.position = position;
        this.visible = visible;
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

    public DCard getVisCard() {
        return visual;
    }

    public void setVisible(boolean _visible) {
        this.visible = _visible;
        visual.w = _visible;
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
    
    public Deck getDeck() {
        return _deck;
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
