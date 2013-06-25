package aceofspades.game;

import aceofspades.GameException;
import aceofspades.Main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JOptionPane;

public class GameData {
    private File _folder;
    private BufferedImage _icon;
    private TreeMap<String, BufferedImage> _imgResList = new TreeMap<>();
    
    private int _gameID;
    private String _name;
    private String _author;
    private int _minPlayerCount;
    private int _maxPlayerCount;
    
    private ArrayList<AIStrategy> _AIStrategies;
    
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
            if (strGameID == null) {
                throw new NullPointerException("'gameID' not found");
            }
            _gameID = Integer.parseInt(strGameID);
            
            _name = prop.getProperty("name");
            if (_name == null) {
                throw new NullPointerException("'name' not found");
            }
            
            _author = prop.getProperty("author");
            if (_author == null) {
                throw new NullPointerException("'author' not found");
            }
            
            String strMinPlayerCount = prop.getProperty("minPlayerCount");
            if (strMinPlayerCount == null) {
                throw new NullPointerException("'minPlayerCount' not found");
            }
            _minPlayerCount = Integer.parseInt(strMinPlayerCount);
            
            String strMaxPlayerCount = prop.getProperty("maxPlayerCount");
            if (strMaxPlayerCount == null) {
                throw new NullPointerException("'maxPlayerCount' not found");
            }
            _maxPlayerCount = Integer.parseInt(strMaxPlayerCount);
            
        } catch (IOException | NullPointerException | NumberFormatException ex) {
            throw new GameException("The 'gamedata.prop' file in " + _folder.getName() + " is corrupt : " + ex.getMessage());
        }
        
        try {
            _icon = ImageIO.read(new File(folder, "icon.jpg"));
        } catch (IOException ex) {
            _icon = Main.getImageResource("gameIcon.jpg");
        }
        
        _imgResList = new TreeMap<>();
        File imgfolder = new File(folder, "img");
        if (imgfolder.exists()) {
            File[] fileList = imgfolder.listFiles();
            if (fileList != null) {
                for (File imgFile : fileList) {
                    try {
                        BufferedImage img = ImageIO.read(imgFile);
                        _imgResList.put(imgFile.getName(), img);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Warning the object '" +
                            imgFile.getName() + "' located within the '" + 
                            folder.getName() + "' Game Data folder is not a "
                            + "valid Image", "Read error", JOptionPane.WARNING_MESSAGE);
                    }            
                }
            }
        }
        
        _AIStrategies = new ArrayList<>();
        File aifolder = new File(folder, "ai");
        if (aifolder.exists()) {
            File[] fileList = aifolder.listFiles();
            if (fileList != null) {
                for (File aiFile : fileList) {
                    try {
                        _AIStrategies.add(new AIStrategy(aiFile));
                    } catch (NullPointerException | NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Warning the object '" +
                            aiFile.getName() + "' located within the '" + 
                            folder.getName() + "' Game Data folder is not a "
                            + "valid AI File", "Read error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }
    
    public int getGameID() {
        return _gameID;
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
    
    public Invocable getEngine() throws IOException, ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        engine.eval(new FileReader(new File(_folder, "scripts.js")));
        return (Invocable)engine;
    }
    
    public ArrayList<AIStrategy> getAIStrategies() {
        return _AIStrategies;
    }
    
    public BufferedImage getIcon() {
        return _icon;
    }
    
    public BufferedImage getImageResource(String name) {
        return _imgResList.get(name);
    }
    
    @Override
    public String toString() {
        return getName();
    }
    
}
