package aceofspades.game;

import aceofspades.Main;
import aceofspades.components.DDeck;
import aceofspades.framestates.FrameState;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.script.Invocable;
import javax.script.ScriptException;

public class GameManager {

    private int _cardIDCounter;
    private int _deckIDCounter;
    private GameData _gameData;
    private Invocable _engine;
    private ArrayList<Player> _players;
    private ArrayList<Card> _cards;
    private TreeMap<Integer, Deck> _decks;
    
    private TreeMap<String, Integer> _vars;
    private ArrayList<TreeMap<String, Integer>> _playerVars;
    
    private FrameState _frameState;
    private int _activePlayerID;
    
    public GameManager(GameData gameData, ArrayList<Player> players) 
            throws IOException, ScriptException {
        _gameData = gameData;
        _engine = gameData.getEngine();
        _players = players;
        _decks = new TreeMap<>();
        _cards= new ArrayList<>();
        
        _cardIDCounter = 0;
        _deckIDCounter = 0;
        
        _activePlayerID = 0;
        
        _vars = new TreeMap<>();
        _playerVars = new ArrayList<>();
    }
    
    /**
     * JavaScript functions
     */
    
    public void setFrameState(FrameState frameState) {
        _frameState = frameState;
    }
    
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
        return _decks.get(deckID);
    }
    
    public ArrayList<Deck> getDecks(String type, Integer ownerPlayerID) {
        ArrayList<Deck> decks = new ArrayList<>(_decks.values());        
        
        if (type != null) {
            ArrayList<Deck> filterDecks = new ArrayList<>();
            for (Deck deck : decks) {
                if (type.equals(deck.getType())) {
                    filterDecks.add(deck);
                }
            }
            decks = filterDecks;
        }
        
        if (ownerPlayerID != null) {
            ArrayList<Deck> filterDecks = new ArrayList<>();
            for (Deck deck : decks) {
                if (deck.isOwner(ownerPlayerID)) {
                    filterDecks.add(deck);
                }
            }
            decks = filterDecks;
        }
        
        return decks;
    }
    
    public Deck createDeck(String type) {
        Deck deck = new Deck(_deckIDCounter, type, _players.size());
        _decks.put(_deckIDCounter, deck);
        
        BufferedImage img = _gameData.getImageResource(type + "Deck.jpg");
        if (img == null) {
            img = Main.getImageResource("cardBack.jpg");
        }        
        deck.getDDeck().setImage(img);
        
        _deckIDCounter++;
        return deck;
    }
    
    public void mergeDecks(ArrayList<Deck> decks, Deck destDeck) {
        if (decks.contains(destDeck)) {
            decks.remove(destDeck);
        }
        
        for (Deck deck : decks) {
            destDeck.addCards(destDeck.getCardCount(), deck.getCards());
            deck.removeAllCards();
            deleteDeck(deck);
        }
    }
    
    public boolean deleteDeck(Deck deck) {
        if (deck.getCardCount() == 0) {
            _decks.remove(deck.getDeckID());
            return true;
        }        
        return false;
    }
    
    public int getVar(String key) {
        return _vars.get(key);
    }
    
    public void setVar(String key, int value) {
        _vars.put(key, value);
    }
    
    public int getPlayerVar(int playerID, String key) {
        return _playerVars.get(playerID).get(key);
    }
    
    public void setPlayerVar(int playerID, String key, int value) {
        _playerVars.get(playerID).put(key, value);
    }
    
    public int getInt(int d) {
        return d;
    }
    
    public int getInt(double d) {
        return new Double(d).intValue();
    }
    
    public int getWidth() {
        return _frameState.getWidth();
    }
    
    public int getHeight() {
        return _frameState.getHeight() - 100;
    }
    
    /**
     * UI Functions
     */
    
    public ArrayList<DDeck> getDDecks(Player activePlayer) {
        ArrayList<Deck> decks = getDecks(null, activePlayer.getPlayerID());
        ArrayList<DDeck> dDecks = new ArrayList<>();
        
        for (Deck deck : decks) {
            DDeck dDeck = deck.getDDeck();
            dDeck.setDimensions(new Dimension(50, 80));
            dDeck.setImage(Main.getImageResource("cardBack.jpg"));
            dDecks.add(dDeck);
        }
        
        return dDecks;
    }
    
    public Player getActivePlayer() {
        return _players.get(_activePlayerID);
    }
    
    public ArrayList<Player> getPlayers() {
        return _players;
    }            
    
    public int getPlayerCount() {
        return _players.size();
    }    
    
    public void startGame() throws ScriptException, NoSuchMethodException {
        _engine.invokeFunction("gameInit", this);
    }
    
    public boolean moveCard(Card card, Deck destDeck, int deckPos) throws ScriptException, NoSuchMethodException {
        int canRemove = (Integer)_engine.invokeFunction("canRemove", 
                this, card, card.getDeck(), deckPos);
        System.out.println(canRemove);
        int canAdd = (Integer)_engine.invokeFunction("canAdd", 
                this, card, destDeck, deckPos);
        System.out.println(canAdd);
        
        if ((canRemove >= 0) && (canAdd >= 0)) {
            uncheckedMoveCard(card, destDeck, canAdd);            
            _engine.invokeFunction("afterMove", this);
        }
        
        return (canRemove >= 0) && (canAdd >= 0);
    }
    
    public void uncheckedMoveCard(Card card, Deck destDeck, int deckPos) {
        card.getDeck().removeCard(card.getDeckPosition());
        destDeck.addCard(deckPos, card);
        card.addToDeck(destDeck, deckPos);        
    }
    
    public boolean canEndTurn() throws ScriptException, NoSuchMethodException {
        return (Boolean)_engine.invokeFunction("canEndTurn", this);
    }
        
}
