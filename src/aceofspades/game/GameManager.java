package aceofspades.game;

import aceofspades.game.Card;
import aceofspades.game.Deck;
import aceofspades.framestates.FSGame;
import aceofspades.game.AIStrategy;
import aceofspades.game.Player;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class GameManager {
    
    private ScriptEngine _engine;
    
    public ArrayList<Deck> getDecks(Player owner, String type) {
        return null;
    }
    
    
    public void startGame(int playerCount) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        _engine = manager.getEngineByName("JavaScript");
        _engine.eval(new FileReader(new File(_folder, "scripts.js")));
        
        Invocable inv = (Invocable) _engine;  
        inv.invokeFunction("gameInit", playerCount);
    }
    
    public boolean canEndTurn(Player p) throws Exception {
        Invocable inv = (Invocable) _engine;
        return (Boolean)inv.invokeFunction("canEndTurn", p.getID());
    }
    
    public boolean canMoveCard(Card card, Player p) throws Exception {
        Invocable inv = (Invocable) _engine;
        return (Boolean)inv.invokeFunction(card.getDeck().toString() + "Remove", p.getID());
    }
    
    public ArrayList<Boolean> availableCardPositions(Card card, Deck deck, Player p) throws Exception {
        ArrayList<Boolean> result = new ArrayList<>();
        
        Invocable inv = (Invocable) _engine;
        for (int i = 0; i < deck.getCardCount() + 1; i++) {
            result.add((Boolean)inv.invokeFunction(card.getDeck().toString() + "Add", i, p.getID()));
        }
        
        return result;
    }
    
    public boolean hasWon(Player p) throws Exception {
        Invocable inv = (Invocable) _engine;
        return (Boolean)inv.invokeFunction("hasWon", p.getID());
    }
    
}
