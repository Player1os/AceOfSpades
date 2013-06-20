package aceofspades;

import aceofspades.game.GameData;
import aceofspades.game.GameManager;
import aceofspades.game.SessionManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Main {

    private static Properties _prop = null;
    private static TreeMap<String, BufferedImage> _imgResList = new TreeMap<>();
    
    private static ArrayList<GameData> _gameDataList = null;
    private static SessionManager _sessionManager = null;
    private static GameManager _gameManager = null;    
    
    private static MainFrame frame = null;

    public static void main(String[] args) {
        try {
            loadProperties();            
            loadImageResources();
            
            frame = MainFrame.getInstance();
        } catch (GameException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), 
                    "Fatal error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void loadProperties() throws GameException {
        _prop = new Properties();
        try (FileInputStream in = new FileInputStream("config.prop")) {
            _prop.load(in);
        } catch (IOException ex) {
            throw new GameException("The 'config.prop' file is corrupt or does not exist" + ex.getMessage());
        }
    }
    
    public static void writeProperties() {
        try (PrintStream out = new PrintStream("config.prop")) {
            _prop.store(out, null);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame,"An error occured while saving"
                    + " the settings, applied changes will be reveresed upon "
                    + "exiting the application.", "Write error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static String getProperty(String key) {
        return _prop.getProperty(key);
    }
    
    public static void setProperty(String key, String value) {
        _prop.setProperty(key, value);
    }
    
    private static void loadImageResources() throws GameException {
        File folder = new File("res", "img");
        File[] fileList = folder.listFiles();
        if (fileList == null) throw new GameException("The 'res/img' directory is corrupt or does not exist.");

        for (File imgFile : fileList) {
            try {
                BufferedImage img = ImageIO.read(imgFile);
                _imgResList.put(imgFile.getName(), img);
            } catch (IOException ex) {}            
        }
    }
    
    public static BufferedImage getImageResource(String name) {
        return _imgResList.get(name);
    }
    
    public static void loadGameDataList() throws GameException {
        _gameDataList = new ArrayList<>();
        
        File folder = new File("gamefiles");
        File[] folderList = folder.listFiles();
        if (folderList == null) throw new GameException("The 'gamefiles' directory is corrupt or does not exist.");
        
        for (File gameFolder : folderList) {
            try {
                GameData gamedata = new GameData(gameFolder);
                _gameDataList.add(gamedata);
            } catch (GameException | NullPointerException ex) {}
        }
    }
    
    public static ArrayList<GameData> getGameDataList() {
        return _gameDataList;
    }
    
    public static void setSessionManager(SessionManager sessionManager) {
        _sessionManager = sessionManager;
    }
    
    public static SessionManager getSessionManager() {
        return _sessionManager;
    }
    
    public static void setGameManager(GameManager gameManager) {
        _gameManager = gameManager;
    }
    
    public static GameManager getGameManager() {
        return _gameManager;
    }
    
}
