package aceofspades.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class GameManager {
    
    private String _name;
    private int _maxPlayerCount;
    
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

            String name = prop.getProperty("name");
            String maxPlayerCount = prop.getProperty("maxPlayerCount");
            if (name == null) {
                throw new Exception();
            }
            if (maxPlayerCount == null) {
                throw new Exception();
            }

            game = new GameManager(name);
            game.setPlayerMaxCount(Integer.valueOf(maxPlayerCount));
        } catch (Exception ex) {
            game = null;
        } finally {
            return game;
        }
    }
    
    private GameManager(String name) {
        _name = name;
    }
       
    public String getName() {
        return _name;
    }
    
    private void setPlayerMaxCount(int maxPlayerCount) {
        _maxPlayerCount = maxPlayerCount;
    }
    
    public int getMaxPlayerCount() {
        return _maxPlayerCount;
    }
    
    @Override
    public String toString() {
        return getName();        
    }
    
}
