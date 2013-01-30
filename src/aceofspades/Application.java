package aceofspades;

import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Player1os <player1os at gmail.com>
 */
public class Application {
    private static ArrayList<CardSet> CardSets;
//    private static ArrayList<Card> Cards;
    
    public static LoadScript lsGame;
    private static boolean bWin;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        //Cards = new ArrayList<Card>();
        CardSets = new ArrayList<>();
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
    public static void createCard(String _value, String _suit, int x, int y, int cardset, int position,boolean visible) {
        Card c = new Card(_value, _suit, x, y, CardSets.get(cardset), position, visible);
        CardSets.get(cardset).addCard(position, c);
    }
    
    
    /**
     * 
     * @param x
     * @param y
     * @param name
     * @return 
     */
    public static int createCardSet(int x, int y, String name, int r, int g, int b) {
        float[] hsv = new float[3];
        Color.RGBtoHSB(r,g,b,hsv);
        CardSets.add(new CardSet(x, y, name, Color.getHSBColor(hsv[0], hsv[1], hsv[2])));
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
