package aceofspades;

import java.awt.*;
import java.util.ArrayList;

public class Application {

    private static ArrayList<CardSet> CardSets;
    public static LoadScript lsGame;
    private static boolean bWin;

    public static void main(String[] args) {
        CardSets = new ArrayList<>();
        bWin = false;

        aceofspades.frames.Frame frame = new aceofspades.frames.Frame();
    }

    public static void createCard(String _value, String _suit, int x, int y, int cardset, int position, boolean visible) {
        Card c = new Card(_value, _suit, x, y, CardSets.get(cardset), position, visible);
        CardSets.get(cardset).addCard(position, c);
    }

    public static int createCardSet(int x, int y, String name, int r, int g, int b) {
        float[] hsv = new float[3];
        Color.RGBtoHSB(r, g, b, hsv);
        CardSets.add(new CardSet(x, y, name, Color.getHSBColor(hsv[0], hsv[1], hsv[2])));
        return CardSets.size() - 1;
    }

    public static void removeCardSet(int id) {
        CardSets.remove(id);
    }

    public static CardSet getCardSet(int id) {
        return CardSets.get(id);
    }

    public static int getCardSetSize() {
        return CardSets.size();
    }

    public static void setWin(boolean b) {
        bWin = b;
    }

    public static boolean getWin() {
        return bWin;
    }
}
