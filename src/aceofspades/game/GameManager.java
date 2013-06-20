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
    
    private int _id;
    private String _name;
    private int _maxPlayerCount;
    private int _minPlayerCount;
    private ArrayList<AIStrategy> _AIStrategies;
    private ScriptEngine _engine;
    private File _folder;
    
    private ArrayList<Deck> 
    
    public static GameManager createGameManager(File folder) {
        GameManager game = null;
        try {
            if (folder == null) {
                throw new Exception();
            }
            if (!folder.isDirectory()) {
                throw new Exception();
            }

            Properties prop = new Properties();
            prop.load(new FileInputStream(new File(folder, "gamedata.prop")));

            String id = getProperty(prop, "id");
            String name = getProperty(prop, "name");
            String maxPlayerCount = getProperty(prop, "maxPlayerCount");
            String minPlayerCount = getProperty(prop, "minPlayerCount");
            
            game = new GameManager(Integer.valueOf(id), name);
            game.setMaxPlayerCount(Integer.valueOf(maxPlayerCount));
            game.setMinPlayerCount(Integer.valueOf(minPlayerCount));
            game.setFolder(folder);
            loadAIStrategies(game, new File(folder, "AI"));
        } catch (Exception ex) {
            game = null;
        } finally {
            return game;
        }
    }
    
    private static String getProperty(Properties prop, String propString) throws Exception {
        String result = prop.getProperty(propString);
        
        if (result == null) {
            throw new Exception();
        }
        
        return result;
    }
    
    private static void loadAIStrategies(GameManager game, File folder) {
        game._AIStrategies = new ArrayList<>();
        
        try {
            if (folder == null) {
                throw new Exception();
            }
            if (!folder.isDirectory()) {
                throw new Exception();
            }
            
            File[] folderList = folder.listFiles();
        
            for (File AIFile : folderList) {
                game._AIStrategies.add(new AIStrategy(AIFile.getName()));
            }
        } catch (Exception ex) {}
    }
    
    private GameManager(int id, String name) {
        this.setName(name);
        this.setGameID(id);
    }   
    
    private void setFolder(File f) {
        _folder = f;
    }
    
    private void setGameID(int id) {
        _id = id;
    }
       
    public int getGameID() {
        return _id;
    }
    
    private void setName(String name) {
        _name = name;
    }
       
    public String getName() {
        return _name;
    }
    
    private void setMaxPlayerCount(int maxPlayerCount) {
        _maxPlayerCount = maxPlayerCount;
    }
    
    private void setMinPlayerCount(int minPlayerCount) {
        _minPlayerCount = minPlayerCount;
    }
    
    public int getMaxPlayerCount() {
        return _maxPlayerCount;
    }
    
    public int getMinPlayerCount() {
        return _minPlayerCount;
    }
    
    public ArrayList<AIStrategy> getAIStrategies() {
        return _AIStrategies;
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
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(getName());
        b.append(", MinPlayerCount : ").append(getMinPlayerCount());
        b.append(", MaxPlayerCount : ").append(getMaxPlayerCount());
        return b.toString();
    }

    public void updateFSGame(FSGame fsGame, Player p) {
        fsGame.addComponent(null);
    }
    
}
