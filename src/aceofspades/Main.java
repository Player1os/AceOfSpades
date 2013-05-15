package aceofspades;

import aceofspades.handlers.GameManager;
import aceofspades.handlers.ServerHandler;
import aceofspades.handlers.Session;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;
import javax.imageio.ImageIO;

public class Main {

    private static Properties _prop = null;
    private static ServerHandler _serverHandler = null;
    private static Session _activeSession = null;
    
    private static TreeMap<String, BufferedImage> _imgResList = new TreeMap<>();
    private static ArrayList<GameManager> _gameList = new ArrayList<>();
    private static MainFrame frame;

    public static void main(String[] args) {
        try {
            _prop = new Properties();
            _prop.load(new FileInputStream("config.prop"));
            loadImageResources();
            loadGameFiles();
            initServerHandler();
            frame = MainFrame.getInstance();
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }
    
    private static void initServerHandler() {
        _serverHandler = new ServerHandler();
    }
    
    private static void loadGameFiles() {
        File folder = new File("gamefiles");
        File[] folderList = folder.listFiles();
        
        for (File gameFolder : folderList) {
            GameManager game;            
            
            game = GameManager.createGameManager(gameFolder);
            if (game != null) {
                _gameList.add(game);
            }
        }
        
    }
    
    private static void loadImageResources() {
        File folder = new File("res");
        File[] fileList = folder.listFiles();

        for (File imgFile : fileList) {
            BufferedImage img;            
            try {
                img = ImageIO.read(imgFile);
                _imgResList.put(imgFile.getName(), img);
            } catch (IOException ex) {}
            
        }
        
    }    
    
    public static Properties getProperties() {
        return _prop;
    }
    
    
    public static void setActiveSession(Session s) {
        _activeSession = s;
    }
    
    public static Session getActiveSession() {
        return _activeSession;
    }
    
    public static BufferedImage getImageResource(String name) {
        return _imgResList.get(name);
    }

    public static ArrayList<GameManager> getGameManagers() {
        return _gameList;
    }
    
}
