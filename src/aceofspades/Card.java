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
        call canAdd of Cardset (_CardSet, _Position)
        
        if both are true then*/
        
        Tester tester = new Tester(cardSet, position, _cardSet, _position, new Application());
        Application.lsGame.runScriptFunction(cardSet.getCardSetClass() + "Remove", tester);
        Application.lsGame.runScriptFunction(_cardSet.getCardSetClass() + "Add", tester);
        
        if (tester.getB()) {                
            this.cardSet.removeCard(position);
            cardSet = _cardSet;
            position = _position;
            this.cardSet.addCard(position, this);
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
                
        
        public void setB(boolean _b) {
            b = _b;
        }
        
        public boolean getB() {
            return b;
        }
    }
}
