package aceofspades.game;

import aceofspades.Main;
import aceofspades.components.DDeck;
import java.awt.image.BufferedImage;
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
    
    private int _activePlayerID;
    
    public GameManager(GameData gameData, ArrayList<Player> players) {
        _gameData = gameData;
        _engine = gameData.getEngine();
        _players = players;
        _decks = new TreeMap<>();
        
        _cardIDCounter = 0;
        _deckIDCounter = 0;
        
        _activePlayerID = 0;
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

    /**
     * UI Functions
     */
    
    public Player getActivePlayer() {
        return _players.get(_activePlayerID);
    }
    
    public void startGame() throws ScriptException, NoSuchMethodException {
        _engine.invokeFunction("gameInit", this);
    }
    
    public boolean moveCard(Card card, Deck destDeck, int deckPos) throws ScriptException, NoSuchMethodException {
        boolean canRemove = (Boolean)_engine.invokeFunction("canRemove", this, card, destDeck, deckPos);
        boolean canAdd = (Boolean)_engine.invokeFunction("canAdd", this, card, destDeck, deckPos);
        
        if (canRemove && canAdd) {
            card.getDeck().removeCard(card.getDeckPosition());
            destDeck.addCard(deckPos, card);
            card.addToDeck(destDeck, deckPos);
            
            _engine.invokeFunction("afterMove", this);
        }
        
        return canRemove && canAdd;
    }
    
    public boolean canEndTurn() throws ScriptException, NoSuchMethodException {
        return (Boolean)_engine.invokeFunction("canEndTurn", this);
    }
    
    
}
