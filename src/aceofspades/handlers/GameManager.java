package aceofspades.handlers;

import aceofspades.game.AIStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class GameManager {
    
    private int _id;
    private String _name;
    private int _maxPlayerCount;
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

            game = new GameManager(Integer.valueOf(id), name);
            game.setMaxPlayerCount(Integer.valueOf(maxPlayerCount));
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
    
    public int getMaxPlayerCount() {
        return _maxPlayerCount;
    }
    
    public ArrayList<AIStrategy> getAIStrategies() {
        return _AIStrategies;
    }
    
    public void startGame() {
        
    }
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(getName());
        b.append(", MaxPlayerCount : ").append(getMaxPlayerCount());
        return b.toString();
    }
    
}
