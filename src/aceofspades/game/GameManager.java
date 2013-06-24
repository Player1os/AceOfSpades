package aceofspades.game;

import javax.script.Invocable;
import javax.script.ScriptException;
import java.awt.*;
import java.util.ArrayList;

public class GameManager {

    private Invocable _engine;
    private ArrayList<Player> _players;
    private ArrayList<Deck> deckList;
    
    public GameManager(GameData gameData, ArrayList<Player> players) throws ScriptException, NoSuchMethodException {
        _engine = gameData.getEngine();
        _players = players;
        deckList = new ArrayList<>();
        _engine.invokeFunction("gameInit", this);
    }

    public void addDeck(int x, int y, String s, Color c, String o) {
        Deck tmp = new Deck(x, y, s, c);
        tmp.setOwner(o);
        deckList.add(tmp);
    }

    public void addDeck(int x, int y, String s, Color c, String o, Deck[] z) {
        Deck tmp = new Deck(x, y, s, c);
        for (Deck e : z) {
            for (Card r : e.Cards) {
                tmp.addCard(0, r);
            }
        }
        tmp.setOwner(o);
        deckList.add(tmp);
    }

    public void deleteDeck(String s) {

    }

    public void addCard(String _value, String _suit, int x, int y, Deck cardset, int position) {

    }

    public ArrayList<Deck> getDecks(Player owner, String type) {
        ArrayList<Deck> res = new ArrayList<>();
        for (Deck tmp : deckList) {
            if (tmp._owner.equals(owner) || owner == null) {
                if (tmp._class.equals(type) || type == null) {
                    res.add(tmp);
                }
            }
        }
        return res;
    }

    public Deck getDeck(String type) {
        for (Deck tmp : deckList) {
            if (tmp._class.equals(type)) {
                return tmp;
            }
        }
        return null;
    }

    public void moveCardjs(Deck zdecku, Deck dodecku, int zpos, int dopos) {

    }
}
