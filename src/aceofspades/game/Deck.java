package aceofspades.game;

import aceofspades.game.Card;
import aceofspades.components.DDeck;
import java.awt.*;
import java.util.ArrayList;

public class Deck {

    ArrayList<Card> Cards;
    DDeck visual;
    String _class;

    public Deck(int x, int y, String s, Color c) {
        visual = new DDeck(x, y, s, c);
        _class = s;
        Cards = new ArrayList<>();
    }

    public void addCard(int _pos, Card _card) {
        this.Cards.add(_pos, _card);
        for (int i = _pos; i < Cards.size(); i++) {
            this.Cards.get(i).position = i;
        }
    }

    public void removeCard(int _pos) {
        this.Cards.remove(_pos);
        for (int i = _pos; i < Cards.size(); i++) {
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

    public DDeck getVisCardSet() {
        return visual;
    }
    
    @Override
    public String toString() {
        return _class;
    }
    
}
