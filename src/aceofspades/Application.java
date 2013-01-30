package aceofspades;

import java.util.ArrayList;

/**
 *
 * @author Player1os <player1os at gmail.com>
 */
public class Application {

    private static ArrayList<CardSet> CardSets;
    private static ArrayList<Card> Cards;
    
    public static LoadScript lsGame;
    private static boolean bWin;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        Cards = new ArrayList<Card>();
        CardSets = new ArrayList<CardSet>();
        bWin = false;
        
        aceofspades.frames.Frame frame = new aceofspades.frames.Frame();
    }    
    
    /**
     * 
     * @param _value
     * @param _suit
     * @param x
     * @param y
     * @param cardset
     * @param position 
     */
    public static void createCard(String _value, String _suit, int x, int y, int cardset, int position) {
        Card c = new Card(_value, _suit, x, y, CardSets.get(cardset), position);        
        CardSets.get(cardset).addCard(position, c);
    }
    
    
    /**
     * 
     * @param x
     * @param y
     * @param name
     * @return 
     */
    public static int createCardSet(int x, int y, String name) {
        CardSets.add(new CardSet(x, y, name));
        return CardSets.size() - 1;
    }
    
    /**
     * 
     * @param id 
     */
    public static void removeCardSet(int id) {
        CardSets.remove(id);
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public static CardSet getCardSet(int id) {
        return CardSets.get(id);
    }

    /**
     * 
     * @return 
     */
    public static int getCardSetSize() {
        return CardSets.size();
    }
    
    /**
     * 
     * @param b 
     */
    public static void setWin(boolean b) {
        bWin = b;
    }
    
    /**
     * 
     * @return 
     */
    public static boolean getWin() {
        return bWin;
    }
}
