package aceofspades;

public class Card {

    protected String value;
    protected String suit;
    protected boolean visible;
    protected VisCard visual;
    protected CardSet cardSet;
    protected int position;

    public Card(String _value, String _suit, int x, int y, CardSet cardset, int position, boolean visible) {
        this.value = _value;
        this.suit = _suit;
        this.visual = new VisCard(x, y, _suit, _value, visible);
        this.cardSet = cardset;
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

    public VisCard getVisCard() {
        return visual;
    }

    public void setVisible(boolean _visible) {
        this.visible = _visible;
        visual.w = _visible;
    }

    public void moveTo(CardSet _cardSet, int _position) {
        Tester tester = new Tester(cardSet, position, _cardSet, _position, new Application());
        Application.lsGame.runScriptFunction(cardSet.getCardSetClass() + "Remove", tester);
        Tester tester2 = new Tester(cardSet, position, _cardSet, _position, new Application());
        Application.lsGame.runScriptFunction(_cardSet.getCardSetClass() + "Add", tester2);

        if (tester.getB() && tester2.getB()) {
            this.cardSet.removeCard(position);
            cardSet = _cardSet;
            position = _position;
            this.cardSet.addCard(position, this);
            Application.lsGame.runScriptFunction("afterMove", tester);
        }
    }

    class Tester {

        boolean b;
        CardSet oldCardset;
        int oldPosition;
        CardSet newCardset;
        int newPosition;
        Application app;

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
        }

        public CardSet getOldCardSet() {
            return oldCardset;
        }

        public CardSet getNewCardSet() {
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
