package aceofspades.handlers;

import aceofspades.CardSet;
import aceofspades.Deck;
import aceofspades.game.AIStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class GameManager {
    
    private int _id;
    private String _name;
    private int _maxPlayerCount;
    private int _minPlayerCount;
    private ArrayList<AIStrategy> _AIStrategies;
    
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
    
    public void startGame() {
        
    }
    
    public boolean canEndTurn() {
        return false;
    }
    
    public boolean canMoveCardset(CardSet cardSet) {
        return false;
    }
    
    public ArrayList<Integer> availableCardsetPositions(CardSet cardSet, Deck deck) {
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(getName());
        b.append(", MinPlayerCount : ").append(getMinPlayerCount());
        b.append(", MaxPlayerCount : ").append(getMaxPlayerCount());
        return b.toString();
    }
    
}
