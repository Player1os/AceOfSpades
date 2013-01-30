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
    
    /**
     * 
     * @param _value
     * @param _suit
     * @param x
     * @param y
     * @param cardset
     * @param position 
     */
    public Card(String _value, String _suit, int x, int y, CardSet cardset, int position) {
        this.value = _value;
        this.suit = _suit;   
        this.visual = new VisCard(x, y, _suit, _value, true);
        this.cardSet = cardset;
        this.position = position;
    }
    
    /**
     * 
     * @return 
     */
    public String getValue() {
        return this.value; 
    }
    
    /**
     * 
     * @return 
     */
    public String getSuit() {
        return this.suit;
    }
    
    /**
     * 
     * @return 
     */
    public Boolean getVisible() {
        return this.visible; 
    }
    
    /**
     * 
     * @return 
     */
    public VisCard getVisCard() {
        return visual;
    }
    
    /**
     * 
     * @param _visible 
     */
    public void setVisible(Boolean _visible) {
        this.visible = _visible; 
    }
    
    /**
     * 
     * @param _cardSet
     * @param _position 
     */
    public void moveTo(CardSet _cardSet, int _position) {
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
    
    /**
     * 
     */
    class Tester {
        boolean b;
        
        CardSet oldCardset;
        int oldPosition;
        CardSet newCardset;
        int newPosition;
        
        Application app;
        
        /**
         * 
         * @param _oldCardset
         * @param _oldPosition
         * @param _newCardset
         * @param _newPosition
         * @param _app 
         */
        public Tester(CardSet _oldCardset, int _oldPosition, CardSet _newCardset, int _newPosition, Application _app) {
            oldCardset = _oldCardset;
            oldPosition = _oldPosition;
            newCardset = _newCardset;
            newPosition = _newPosition;
            app = _app;
            b = true;
        }
        
        /**
         * 
         * @return 
         */
        public Application getApp() {
            return app;
        }
        
        /**
         * 
         * @return 
         */
        public CardSet getOldCardSet() {
            return oldCardset;
        }
        
        /**
         * 
         * @return 
         */
        public CardSet getNewCardSet() {
            return newCardset;
        }
        
        /**
         * 
         * @return 
         */
        public int getOldPosition() {
            return oldPosition;
        }
        
        /**
         * 
         * @return 
         */
        public int getNewPosition() {
            return newPosition;
        }
                
        /**
         * 
         * @param _b 
         */
        public void setB(boolean _b) {
            b = _b;
        }
        
        /**
         * 
         * @return 
         */
        public boolean getB() {
            return b;
        }
    }
}
