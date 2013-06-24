package aceofspades.game;

import aceofspades.components.DDeck;
import java.awt.*;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.script.Invocable;
import javax.script.ScriptException;

public class GameManager {

    private int _cardIDCounter;
    private int _deckIDCounter;
    
    private Invocable _engine;
    private ArrayList<Player> _players;
    private ArrayList<Card> _cards;
    private TreeMap<Integer, Deck> _decks;
    
    private int _activePlayerID;
    
    public GameManager(GameData gameData, ArrayList<Player> players) {
        _engine = gameData.getEngine();
        _players = players;
        _decks = new TreeMap<>();
        
        _cardIDCounter = 0;
        _deckIDCounter = 0;
        
        _activePlayerID = 0;
    }
    
    public Player getActivePlayer() {
        return _players.get(_activePlayerID);
    }
    
    /**
     * JavaScript functions
     */
    
    public Card getCard(int cardID) {
        return _cards.get(cardID);
    }
    
    public Card createCard(String value, String suit) {
        Card c = new Card(_cardIDCounter, value, suit, _players.size());
        _cards.add(c);
        _cardIDCounter++;
        return c;
    }
    
    public Deck getDeck(int deckID) {
        return 
    }
    
    public ArrayList<Deck> getDecks(String type, Integer ownerPlayerID) {
        ArrayList<Deck> decks = new ArrayList<>();
        
        if (type != null) {
            
        }
        
        if (ownerPlayerID != null) {
            
        }
        
        return decks;
    }
    
    public Deck createDeck(String type) {
        Deck d = new Deck(_deckIDCounter, type, _players.size());
        _deckIDCounter++;
        return d;
    }
    
    public Deck mergeDecks(ArrayList<Deck> decks, String type) {
        Deck d = new Deck(_deckIDCounter, type, _players.size());
        
        for (Deck deck : decks) {
            
        }
        
        return d;
    }
    
    public void deleteDeck(String s) {

    }
    
    public void startGame() throws ScriptException, NoSuchMethodException {
        _engine.invokeFunction("gameInit", this);
    }

    /**
     * UI Functions
     */
    
    public ArrayList<DDeck> getDDecks() {
        ArrayList<DDeck> decks = new ArrayList<>();
        
        for (int i = 0; i < _deckIDCounter; i++) {
            if (_decks.containsKey(i)) {
                
            }
        }
        
        return decks;
    }
    
    public void moveCard(int cardID, int destinationDeckID, int deckPosition) {

    }
    
    public boolean canEndTurn() {
        return false;        
    }
    
    
}
