package aceofspades;

import aceofspades.framestates.Game;
import aceofspades.handlers.Player;
import aceofspades.handlers.Session;
import aceofspades.handlers.LocalSession;
import aceofspades.handlers.GameManager;
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
    private static Session _activeSession = null;
    
    private static TreeMap<String, BufferedImage> _imgResList = new TreeMap<>();
    private static ArrayList<GameManager> _gameList = new ArrayList<>();
    private static MainFrame frame;

    public static void main(String[] args) {
        _prop = new Properties();
        try {
            _prop.load(new FileInputStream("config.prop"));
        } catch (IOException ex) {
            throw new RuntimeException();
        }
        
        loadImageResources();
        loadGameFiles();
        
        LocalSession s = new LocalSession();
        setActiveSession(s);
        GameManager g = _gameList.get(0);
        s.setGameManager(g);
        Player p = new Player();
        s.addPlayer(p);
        
        frame = MainFrame.getInstance();
        frame.setContentManager(
                new Game(frame, 
                    frame.getContentPane().getWidth(), 
                    frame.getContentPane().getHeight())
                );
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
