package aceofspades.game;

import aceofspades.GameException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GameData {
    private File _folder;
    
    private int _gameID;
    private String _name;
    private String _author;
    private int _minPlayerCount;
    private int _maxPlayerCount;
    
    public GameData(File folder) throws GameException, NullPointerException {
        if (folder == null) {
            throw new NullPointerException();
        }
        if (!folder.isDirectory()) {
            throw new GameException("The GameData path is not a directory");
        }
        _folder = folder;        
        
        try {
            Properties prop = new Properties();
            try (FileInputStream in = new FileInputStream(new File(folder, "gamedata.prop"))) {
                prop.load(in);
            }
            
            String strGameID = prop.getProperty("gameID");
            if (strGameID == null) throw new NullPointerException("'gameID' not found");
            _gameID = Integer.parseInt(strGameID);
            
            _name = prop.getProperty("name");
            if (_name == null) throw new NullPointerException("'name' not found");
            
            _author = prop.getProperty("author");
            if (_author == null) throw new NullPointerException("'author' not found");
            
            String strMinPlayerCount = prop.getProperty("minPlayerCount");
            if (strMinPlayerCount == null) throw new NullPointerException("'minPlayerCount' not found");
            _minPlayerCount = Integer.parseInt(strMinPlayerCount);
            
            String strMaxPlayerCount = prop.getProperty("maxPlayerCount");
            if (strMaxPlayerCount == null) throw new NullPointerException("'maxPlayerCount' not found");
            _maxPlayerCount = Integer.parseInt(strMaxPlayerCount);
            
        } catch (IOException | NullPointerException | NumberFormatException ex) {
            throw new GameException("The 'gamedata.prop' file in " + _folder.getName() + " is corrupt : " + ex.getMessage());
        }
        
    }
    
    public String getName() {
        return _name;
    }
    
    public String getAuthor() {
        return _author;
    }
    
    public int getMinPlayerCount() {
        return _minPlayerCount;
    }
    
    public int getMaxPlayerCount() {
        return _maxPlayerCount;
    }
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(getName());
        b.append(", Author : ").append(getAuthor());
        b.append(", MinPlayerCount : ").append(getMinPlayerCount());
        b.append(", MaxPlayerCount : ").append(getMaxPlayerCount());
        return b.toString();
    }
    
}
